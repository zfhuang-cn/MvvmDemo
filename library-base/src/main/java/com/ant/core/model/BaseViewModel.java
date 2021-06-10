package com.ant.core.model;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ant.core.mvvm.ViewStatus;

public abstract class BaseViewModel<MODEL extends BaseModel, DATA> extends ViewModel implements LifecycleObserver, IBaseModelListener<DATA> {

    protected MODEL model;

    public MutableLiveData<DATA> dataList = new MutableLiveData<>();
    public MutableLiveData<ViewStatus> viewStatusLiveData = new MutableLiveData<>();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();

    @Override
    protected void onCleared() {
        super.onCleared();
        model.cancel();
    }
}