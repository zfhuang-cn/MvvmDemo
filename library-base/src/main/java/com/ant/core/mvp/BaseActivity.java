package com.ant.core.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<P extends BasePresenter, V extends IBaseView> extends AppCompatActivity {

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView((V) this);
        getLifecycle().addObserver(presenter);
    }

    protected abstract P createPresenter();
}