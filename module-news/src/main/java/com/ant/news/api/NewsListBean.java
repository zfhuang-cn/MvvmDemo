package com.ant.news.api;

import com.ant.common.beans.JuheBaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsListBean extends JuheBaseResponse {

    public class NewsBean {
        private String uniquekey;
        @Expose
        public String title;
        @Expose
        public String date;
        @Expose
        public String category;
        @SerializedName("author_name")
        @Expose
        public String authorNme;
        @Expose
        public String url;
        @SerializedName("thumbnail_pic_s")
        @Expose
        public String thumbnailPic;
        public String thumbnail_pic_s02;
        public String thumbnail_pic_s03;
        public String is_content;
    }

    public ResultBean result;

    public class ResultBean {
        public String stat;
        public List<NewsBean> data;
        public String page;
        public String pageSize;
    }
}