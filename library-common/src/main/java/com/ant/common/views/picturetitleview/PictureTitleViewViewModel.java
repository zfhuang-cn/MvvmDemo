package com.ant.common.views.picturetitleview;

import com.ant.core.mvvm.customview.BaseCustomViewModel;

public class PictureTitleViewViewModel extends BaseCustomViewModel {
    public String title;
    public String date;
    public String authorName;
    public String thumbnailPic;

    public PictureTitleViewViewModel(String title, String date, String authorName, String thumbnailPic,String jumpUri) {
        this.title = title;
        this.date = date;
        this.authorName = authorName;
        this.thumbnailPic = thumbnailPic;
        this.jumpUri = jumpUri;
    }
}