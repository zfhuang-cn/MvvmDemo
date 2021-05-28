package com.ant.core.mvvm.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ant.core.mvvm.customview.BaseCustomViewModel;

import java.util.List;

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    protected List<BaseCustomViewModel> mItems;

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    public void setData(List items) {
        mItems = items;
        notifyDataSetChanged();
    }
}