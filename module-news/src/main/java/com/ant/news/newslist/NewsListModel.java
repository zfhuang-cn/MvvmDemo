package com.ant.news.newslist;

import android.text.TextUtils;

import com.ant.common.JuheNetworkApi;
import com.ant.common.views.picturetitleview.PictureTitleViewViewModel;
import com.ant.common.views.titleview.TitleViewViewModel;
import com.ant.core.model.BaseModel;
import com.ant.core.model.IBaseModelListener;
import com.ant.core.model.PagingResult;
import com.ant.core.mvvm.customview.BaseCustomViewModel;
import com.ant.network.BaseObservable;
import com.ant.news.api.NewsApiInterface;
import com.ant.news.api.NewsListBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class NewsListModel extends BaseModel<List<BaseCustomViewModel>> {

    private String mChannelCode = "";
    private boolean isRefresh;
    private int page;

    public NewsListModel(String channelCode,
                         IBaseModelListener<List<BaseCustomViewModel>> iBaseModelListener) {
        super(iBaseModelListener);
        this.mChannelCode = channelCode;
    }

    public void refresh() {
        isRefresh = true;
        load();
    }

    public void loadNextPage() {
        isRefresh = false;
        load();
    }

    @Override
    protected void notifyCacheData(List<BaseCustomViewModel> baseCustomViewModels) {

    }

    protected void load() {
        addDisposable(JuheNetworkApi.getInstance().getService(NewsApiInterface.class)
                .getNewsList(mChannelCode, isRefresh ? 1 : page)
                .compose(JuheNetworkApi.applySchedulers(new BaseObservable<NewsListBean>() {

                    @Override
                    public void onSuccess(NewsListBean data) {
                        page = isRefresh ? 2 : page + 1;
                        BaseCustomViewModel viewModel;
                        ArrayList<BaseCustomViewModel> viewModels = new ArrayList<>();
                        for (NewsListBean.NewsBean source : data.result.data) {
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
                        iBaseModelListener.onLoadFinish(viewModels,
                                new PagingResult(viewModels.isEmpty(), isRefresh,
                                        viewModels.isEmpty()));
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        iBaseModelListener.onLoadFail(e.getMessage(),
                                new PagingResult(true, isRefresh, false));
                    }
                }))
                .subscribe());
    }
}