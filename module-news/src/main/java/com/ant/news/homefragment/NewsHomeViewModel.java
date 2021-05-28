package com.ant.news.homefragment;

import androidx.lifecycle.SavedStateHandle;

import com.ant.core.model.PagingResult;
import com.ant.core.mvvm.BaseViewModel;
import com.ant.news.api.ChannelBean;

import java.util.ArrayList;
import java.util.List;

public class NewsHomeViewModel extends BaseViewModel<ChannelsModel, List<ChannelBean>> {

    public NewsHomeViewModel(SavedStateHandle savedStateHandle) {
        dataList.setValue(new ArrayList<>());
        model = new ChannelsModel(this);
        model.load();
    }

    @Override
    public void onLoadFinish(List<ChannelBean> data, PagingResult... pagingResults) {
        dataList.getValue().clear();
        dataList.getValue().addAll(data);
        dataList.postValue(dataList.getValue());
    }

    @Override
    public void onLoadFail(String prompt, PagingResult... pagingResults) {

    }
}