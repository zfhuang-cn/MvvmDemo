package com.ant.core.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseCustomView<DATA_BINDING extends ViewDataBinding,
        VIEW_MODEL extends BaseCustomViewModel> extends FrameLayout implements ICustomView<VIEW_MODEL>, View.OnClickListener {

    protected DATA_BINDING dataBinding;
    protected VIEW_MODEL viewModel;

    public BaseCustomView(Context context) {
        super(context);
        init();
    }

    public BaseCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BaseCustomView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater inflater =
                (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (viewLayoutId() != 0) {
            dataBinding = DataBindingUtil.inflate(inflater, viewLayoutId(), this, false);
            dataBinding.getRoot().setOnClickListener(v -> onRootClick(v));
            addView(dataBinding.getRoot());
        }
    }

    @Override
    public void setData(VIEW_MODEL data) {
        viewModel = data;
        setDataToView(viewModel);
        if (dataBinding != null) {
            dataBinding.executePendingBindings();
        }
    }

    @Override
    public void onClick(View v) {

    }

    protected abstract int viewLayoutId();

    protected abstract void onRootClick(View v);

    protected abstract void setDataToView(VIEW_MODEL viewModel);
}