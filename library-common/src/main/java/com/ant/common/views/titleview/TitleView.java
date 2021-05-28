package com.ant.common.views.titleview;

import android.content.Context;
import android.view.View;

import com.ant.common.R;
import com.ant.common.databinding.TitleViewBinding;
import com.ant.core.mvvm.customview.BaseCustomView;

public class TitleView extends BaseCustomView<TitleViewBinding, TitleViewViewModel> {

    public TitleView(Context context) {
        super(context);
    }

    @Override
    protected int viewLayoutId() {
        return R.layout.title_view;
    }

    @Override
    protected void onRootClick(View v) {

    }

    @Override
    protected void setDataToView(TitleViewViewModel viewModel) {
        dataBinding.setViewModel(viewModel);
    }
}