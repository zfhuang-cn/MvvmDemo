package com.ant.news.newslist;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.ant.common.views.picturetitleview.PictureTitleView;
import com.ant.common.views.picturetitleview.PictureTitleViewViewModel;
import com.ant.common.views.titleview.TitleView;
import com.ant.common.views.titleview.TitleViewViewModel;
import com.ant.core.recyclerview.BaseAdapter;
import com.ant.core.recyclerview.BaseViewHolder;

public class NewsListAdapter extends BaseAdapter {
    private final int VIEW_TYPE_PICTURE_TITLE = 1;
    private final int VIEW_TYPE_TITLE = 2;

    @Override
    public int getItemViewType(int position) {
        if (mItems.get(position) instanceof PictureTitleViewViewModel) {
            return VIEW_TYPE_PICTURE_TITLE;
        } else if (mItems.get(position) instanceof TitleViewViewModel) {
            return VIEW_TYPE_TITLE;
        }
        return -1;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_PICTURE_TITLE) {
            return new BaseViewHolder(new PictureTitleView(parent.getContext()));
        } else if (viewType == VIEW_TYPE_TITLE) {
            return new BaseViewHolder(new TitleView(parent.getContext()));
        }
        return null;
    }
}