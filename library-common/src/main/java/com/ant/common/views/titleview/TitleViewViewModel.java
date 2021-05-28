package com.ant.common.views.titleview;

import com.ant.core.mvvm.customview.BaseCustomViewModel;

public class TitleViewViewModel extends BaseCustomViewModel {
    public String title;
    public String date;
    public String authorName;

    public TitleViewViewModel(String title, String date, String authorName, String jumpUri) {
        this.title = title;
        this.date = date;
        this.authorName = authorName;
        this.jumpUri = jumpUri;
    }
}