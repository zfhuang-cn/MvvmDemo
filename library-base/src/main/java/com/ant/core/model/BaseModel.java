package com.ant.core.model;

import com.ant.core.utils.GsonUtils;

import java.lang.reflect.Type;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 应用模块: model
 * <p>
 * 类描述: 基类抽象model
 * <p>
 */
public abstract class BaseModel<DATA> {

    protected CompositeDisposable compositeDisposable;
    protected IBaseModelListener<DATA> iBaseModelListener;

    public BaseModel(IBaseModelListener<DATA> iBaseModelListener) {
        this.iBaseModelListener = iBaseModelListener;
        compositeDisposable = new CompositeDisposable();
    }

    /**
     * 获取缓存数据并加载网络数据
     */
    public void getCacheDataAndLoad() {
        // 如果有apk内置数据,加载内置数据
        if (null != getApkCache()) {
            notifyCacheData((DATA) GsonUtils.fromLocalJson(getApkCache(), getDataClass()));
            if (isNeedToUpData()) {
                load();
            }
            return;
        }

        // 没有缓存数据,直接加载网络数据
        load();
    }

    /**
     * 该model的数据是否有apk预制的数据,如果有请返回
     */
    protected String getApkCache() {
        return null;
    }

    /**
     * 是否需要更新数据,默认每一次都需要更新
     *
     * @return true
     */
    protected boolean isNeedToUpData() {
        return true;
    }

    /**
     * 需要缓存的数据类型
     */
    protected Type getDataClass() {
        return null;
    }


    /**
     * 加载缓存数据
     */
    protected abstract void notifyCacheData(DATA data);

    /**
     * 加载网络数据
     */
    protected abstract void load();

    /**
     * 订阅对象管理
     */
    public void addDisposable(Disposable disposable) {
        if (null == disposable) {
            return;
        }
        if (null == compositeDisposable) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    /**
     * 取消所有订阅
     */
    public void cancel() {
        if (null != compositeDisposable && !compositeDisposable.isDisposed()) {
            compositeDisposable.isDisposed();
        }
    }
}