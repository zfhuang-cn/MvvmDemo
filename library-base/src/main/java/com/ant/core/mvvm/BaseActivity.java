package com.ant.core.mvvm;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ant.core.model.NormalViewModel;
import com.ant.core.utils.GenericUtil;

public abstract class BaseActivity<VIEW_MODEL extends ViewModel,
        DATA_BINDING extends ViewDataBinding> extends AppCompatActivity {

    protected DATA_BINDING viewDataBinding;
    protected VIEW_MODEL viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId());
        createViewModel(savedInstanceState);
        init();
    }

    protected void createViewModel(Bundle savedInstanceState) {
        Class modelClass = (Class) GenericUtil.getGenericType(this);
        if (modelClass == null) {
            //如果没有指定泛型参数，则默认使用NormalViewModel
            modelClass = NormalViewModel.class;
        }
        viewModel = (VIEW_MODEL) new ViewModelProvider(this,
                new SavedStateViewModelFactory(getApplication(), this, savedInstanceState)).get(modelClass);
    }

    protected abstract int layoutId();

    protected abstract void init();

}