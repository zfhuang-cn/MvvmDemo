package com.ant.news.newslist;

import androidx.lifecycle.SavedStateHandle;

import com.ant.core.customview.BaseCustomViewModel;
import com.ant.core.model.BaseViewModel;
import com.ant.core.model.PagingResult;
import com.ant.core.mvvm.ViewStatus;

import java.util.ArrayList;
import java.util.List;

import static com.ant.news.newslist.NewsListFragment.BUNDLE_KEY_PARAM_CHANNEL_CODE;

public class NewsListViewModel extends BaseViewModel<NewsListModel, List<BaseCustomViewModel>> {

    public NewsListViewModel(SavedStateHandle savedStateHandle) {
        dataList.setValue(new ArrayList<>());
        model = new NewsListModel(savedStateHandle.get(BUNDLE_KEY_PARAM_CHANNEL_CODE), this);
    }

    public void refresh() {
        model.refresh();
    }

    public void tryToLoadNextPage() {
        model.loadNextPage();
    }

    @Override
    public void onLoadFinish(List<BaseCustomViewModel> data, PagingResult... pagingResults) {
        if (pagingResults[0].isFirstPage) {
            dataList.getValue().clear();
        }
        if (pagingResults[0].isEmpty) {
            if (pagingResults[0].isFirstPage) {
                viewStatusLiveData.postValue(ViewStatus.EMPTY);
            } else {
                viewStatusLiveData.postValue(ViewStatus.NO_MORE_DATA);
            }
        } else {
            dataList.getValue().addAll(data);
            dataList.postValue(dataList.getValue());
            viewStatusLiveData.postValue(ViewStatus.SHOW_CONTENT);
        }
    }

    @Override
    public void onLoadFail(String prompt, PagingResult... pagingResults) {
        errorMessage.postValue(prompt);
        if (!pagingResults[0].isFirstPage) {
            viewStatusLiveData.postValue(ViewStatus.LOAD_MORE_FAILED);
        } else {
            viewStatusLiveData.postValue(ViewStatus.REFRESH_ERROR);
        }
    }
}