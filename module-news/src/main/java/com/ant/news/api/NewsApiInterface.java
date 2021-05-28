package com.ant.news.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiInterface {
    @GET("toutiao/index?key=cbb040924067f8a24bfe1d808015470b")
    Observable<NewsListBean> getNewsList(@Query("type") String type,
                                                  @Query("page") int page);
}
