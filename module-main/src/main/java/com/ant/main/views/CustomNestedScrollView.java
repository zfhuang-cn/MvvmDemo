package com.ant.main.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.ant.main.utils.FlingHelper;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @author: zfhuang
 * @date: 2021/6/3
 */
public class CustomNestedScrollView extends NestedScrollView implements NestedScrollView.OnScrollChangeListener {

    /**
     * 用于判断NestedScrollView是否fling
     */
    private boolean isStartFling;
    /**
     * 记录当前滑动的y轴加速度
     */
    private int velocityY;
    FlingHelper mFlingHelper;
    private int totalDy;

    public CustomNestedScrollView(@NonNull Context context) {
        super(context);
        init();
    }

    public CustomNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs,
                                  int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnScrollChangeListener(this);
        mFlingHelper = new FlingHelper(getContext());
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed,
                                  int type) {
        if (getChildAt(0) instanceof ViewGroup) {
            int headerViewHeight = ((ViewGroup) getChildAt(0)).getChildAt(0).getMeasuredHeight();
            //向上滑动，若当前banner view可见，需要将banner view滑动至不可见
            boolean isNeedToHideTop = dy > 0 && getScrollY() < headerViewHeight;
            if (isNeedToHideTop) {
                scrollBy(0, dy);
                consumed[1] = dx;
            }
        }
    }

    @Override
    public void fling(int velocityY) {
        super.fling(velocityY);
        this.velocityY = velocityY;
        if (velocityY > 0) {
            isStartFling = true;
            totalDy = 0;
        }
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX,
                               int oldScrollY) {
        totalDy += scrollY - oldScrollY;
        if (scrollY == (((ViewGroup) getChildAt(0)).getChildAt(0).getMeasuredHeight())) {
            if (velocityY != 0) {
                double splineFlingDistance = mFlingHelper.getSplineFlingDistance(velocityY);
                if (splineFlingDistance > totalDy) {
                    ViewPager2 viewPager2 = getChildView(this, ViewPager2.class);
                    if (viewPager2 != null) {
                        RecyclerView childRecyclerView = getChildView(((ViewGroup)viewPager2.getChildAt(0)).getChildAt(viewPager2.getCurrentItem()),RecyclerView.class);
                        childRecyclerView.fling(0, mFlingHelper.getVelocityByDistance(splineFlingDistance- (double) totalDy));
                    }
                }
            }
            totalDy = 0;
            velocityY = 0;
        }
    }

    private <T> T getChildView(View view, Class<T> targetClass) {
        if (view != null && view.getClass() == targetClass) {
            return (T) view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childView = viewGroup.getChildAt(i);
                if (childView instanceof ViewGroup) {
                    T result = getChildView(childView, targetClass);
                    if (result != null) {
                        return result;
                    }
                } else if (childView.getClass() == targetClass) {
                    return (T) childView;
                }
            }
        }
        return null;
    }
}