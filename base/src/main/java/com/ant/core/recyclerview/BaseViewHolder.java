package com.ant.core.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ant.core.customview.BaseCustomView;

public class BaseViewHolder extends RecyclerView.ViewHolder {
    public BaseViewHolder(@NonNull BaseCustomView itemView) {
        super(itemView);
    }
}