package com.ant.core.observer;

import com.ant.core.model.NetworkDataTransformer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @author: zfhuang
 * @date: 2021/6/10
 */
public class BaseObserver<T> implements Observer<T> {
    NetworkDataTransformer<T> networkDataTransformer;

    public BaseObserver(NetworkDataTransformer<T> networkDataTransformer) {
        this.networkDataTransformer = networkDataTransformer;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        networkDataTransformer.onDataTransform(t, false);
    }

    @Override
    public void onError(Throwable e) {
        networkDataTransformer.onFailure(e);
    }

    @Override
    public void onComplete() {

    }
}