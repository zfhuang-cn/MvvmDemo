package com.ant.core.mvvm;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

public abstract class BaseActivity<DATA_BINDING extends ViewDataBinding,VIEW_MODEL extends ViewModel> extends AppCompatActivity {

    protected DATA_BINDING viewDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId());
        init();
    }

    protected abstract int layoutId();

    protected abstract void init();

}