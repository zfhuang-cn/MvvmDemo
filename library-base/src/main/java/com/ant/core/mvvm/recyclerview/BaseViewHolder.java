package com.ant.core.mvvm.recyclerview;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ant.core.mvvm.customview.BaseCustomViewModel;
import com.ant.core.mvvm.customview.ICustomView;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    ICustomView view;

    public BaseViewHolder(@NonNull ICustomView view) {
        super((View) view);
        this.view = view;
    }

    public void bind(@NonNull BaseCustomViewModel viewModel) {
        view.setData(viewModel);
    }
}