package com.ant.network;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObservable<T> implements Observer<T> {

    public abstract void onSuccess(T t);

    public abstract void onFailure(Throwable e);

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onFailure(e);
    }

    @Override
    public void onComplete() {

    }
}