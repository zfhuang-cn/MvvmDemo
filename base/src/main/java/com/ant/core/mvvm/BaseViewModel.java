package com.ant.core.mvvm;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ant.core.model.BaseModel;
import com.ant.core.model.IBaseModelListener;

public abstract class BaseViewModel<M extends BaseModel, D> extends ViewModel implements LifecycleObserver, IBaseModelListener<D> {
    protected M model;

//    public MutableLiveData<ObservableList<D>> data;
    public MutableLiveData<String> errorMessage;

}