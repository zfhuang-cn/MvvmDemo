package com.ant.core.mvp;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends IBaseView> implements LifecycleObserver {

    WeakReference<V> iView;

    public void attachView(V view) {
        this.iView = new WeakReference<>(view);
    }

    public void detachView() {
        if (iView != null) {
            iView.clear();
            iView = null;
        }
    }

    /**
     * 监听 onCreate
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(LifecycleOwner owner) {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart(LifecycleOwner owner) {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop(LifecycleOwner owner) {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner) {
        detachView();
    }
}