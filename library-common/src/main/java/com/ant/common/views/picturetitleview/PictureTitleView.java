package com.ant.common.views.picturetitleview;

import android.content.Context;
import android.view.View;

import com.ant.common.R;
import com.ant.common.databinding.PictureTitleViewBinding;
import com.ant.core.mvvm.customview.BaseCustomView;

public class PictureTitleView extends BaseCustomView<PictureTitleViewBinding,
        PictureTitleViewViewModel> {

    public PictureTitleView(Context context) {
        super(context);
    }

    @Override
    protected int viewLayoutId() {
        return R.layout.picture_title_view;
    }

    @Override
    protected void onRootClick(View v) {

    }

    @Override
    protected void setDataToView(PictureTitleViewViewModel viewModel) {
        dataBinding.setViewModel(viewModel);
    }
}