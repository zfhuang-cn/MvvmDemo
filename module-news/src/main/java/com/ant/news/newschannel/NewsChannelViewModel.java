package com.ant.news.newschannel;

import androidx.lifecycle.SavedStateHandle;

import com.ant.core.model.PagingResult;
import com.ant.core.model.BaseViewModel;
import com.ant.news.api.ChannelBean;

import java.util.ArrayList;
import java.util.List;

public class NewsChannelViewModel extends BaseViewModel<ChannelModel, List<ChannelBean>> {

    public NewsChannelViewModel(SavedStateHandle savedStateHandle) {
        dataList.setValue(new ArrayList<>());
        model = new ChannelModel(this);
        model.refresh();
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