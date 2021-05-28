package com.ant.core.mvvm;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.ant.core.model.BaseModel;
import com.ant.core.model.IBaseModelListener;

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