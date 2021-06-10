package com.ant.webview;

import com.ant.core.mvvm.BaseActivity;
import com.ant.core.model.NormalViewModel;
import com.ant.webview.databinding.ActivityWebviewBinding;

public class WebViewActivity extends BaseActivity<NormalViewModel,ActivityWebviewBinding> {
    @Override
    protected int layoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void init() {

    }
}