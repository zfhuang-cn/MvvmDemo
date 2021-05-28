package com.ant.main.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * @author zfhuang
 */
public class CustomNoTouchViewPager extends ViewPager {
    public CustomNoTouchViewPager(@NonNull Context context) {
        super(context);
    }

    public CustomNoTouchViewPager(@NonNull Context context,
                                  @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}