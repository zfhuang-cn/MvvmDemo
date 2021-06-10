package com.ant.news.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChannelBean {
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("name")
    @Expose
    public String name;

    public ChannelBean(String code, String name) {
        this.code = code;
        this.name = name;
    }
}