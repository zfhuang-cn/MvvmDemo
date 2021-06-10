package com.ant.news.newslist;

import android.text.TextUtils;

import com.ant.common.JuheNetworkApi;
import com.ant.common.beans.JuheBaseResponse;
import com.ant.common.views.picturetitleview.PictureTitleViewViewModel;
import com.ant.common.views.titleview.TitleViewViewModel;
import com.ant.core.customview.BaseCustomViewModel;
import com.ant.core.model.BaseModel;
import com.ant.core.model.IBaseModelListener;
import com.ant.core.observer.BaseObserver;
import com.ant.news.api.NewsApiInterface;
import com.ant.news.api.NewsListBean;

import java.util.ArrayList;
import java.util.List;

public class NewsListModel extends BaseModel<JuheBaseResponse<NewsListBean>,
        List<BaseCustomViewModel>> {

    private final String mChannelCode;

    public NewsListModel(String channelCode, IBaseModelListener<List<BaseCustomViewModel>> listener) {
        super(true, listener, "news_list_" + channelCode, null, 1);
        this.mChannelCode = channelCode;
    }

    @Override
    protected boolean isNeedToUpdate(long cachedTime) {
        //超过15分钟需要更新缓存
        return System.currentTimeMillis() - cachedTime > 1000 * 60 * 15;
    }

    protected void load() {
        addDisposable(JuheNetworkApi.getInstance().getService(NewsApiInterface.class)
                .getNewsList(mChannelCode, pageNumber)
                .compose(JuheNetworkApi.applySchedulers(new BaseObserver<>(this)))
                .subscribe());
    }

    @Override
    public void onDataTransform(JuheBaseResponse<NewsListBean> data, boolean isFromCache) {
        BaseCustomViewModel viewModel;
        ArrayList<BaseCustomViewModel> viewModels = new ArrayList<>();
        for (NewsListBean.NewsBean source : data.getResult().data) {
            if (TextUtils.isEmpty(source.thumbnailPic)) {
                viewModel = new TitleViewViewModel(source.title, source.date,
                        source.authorNme, source.url);
            } else {
                viewModel = new PictureTitleViewViewModel(source.title,
                        source.date, source.authorNme, source.thumbnailPic,
                        source.url);
            }
            viewModels.add(viewModel);
        }
        notifyResultToListener(data, viewModels, isFromCache);
    }
}