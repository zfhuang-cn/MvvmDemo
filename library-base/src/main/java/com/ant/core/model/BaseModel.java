package com.ant.core.model;

import android.text.TextUtils;

import com.ant.core.utils.GenericUtil;
import com.blankj.utilcode.util.GsonUtils;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 应用模块: base
 * <p>
 * 类描述: 基类抽象model
 * <p>
 */
public abstract class BaseModel<NETWORK_DATA, RESULT_DATA> implements NetworkDataTransformer<NETWORK_DATA>  {

    private CompositeDisposable compositeDisposable;
    private IBaseModelListener<RESULT_DATA> iBaseModelListener;
    private final int INIT_PAGE_NUMBER;
    private boolean mIsPaging;
    private boolean mIsLoading;
    private String mCachedKey;
    private String mPredefinedData;
    protected int pageNumber = 1;

    public BaseModel(boolean isPaging, IBaseModelListener<RESULT_DATA> iBaseModelListener,
                     String cachedKey, String predefinedData, int... initPageNumber) {
        this.mIsPaging = isPaging;
        this.iBaseModelListener = iBaseModelListener;
        this.mCachedKey = cachedKey;
        this.mPredefinedData = predefinedData;
        if (initPageNumber != null && initPageNumber.length == 1) {
            INIT_PAGE_NUMBER = initPageNumber[0];
        } else {
            INIT_PAGE_NUMBER = -1;
        }
        compositeDisposable = new CompositeDisposable();
    }

    protected boolean isNeedToUpdate(long cachedTime) {
        //超过1分钟需要更新缓存
        return System.currentTimeMillis() - cachedTime > 1000 * 60;
    }

    public void refresh() {
        if (!mIsLoading) {
            mIsLoading = true;
            if (mIsPaging) {
                pageNumber = INIT_PAGE_NUMBER;
            }
            if (mCachedKey != null) {
                CacheData cacheData = CacheData.getCacheData(mCachedKey);
                if (cacheData != null) {
                    NETWORK_DATA savedData = GsonUtils.fromJson(cacheData.getNetworkDataString(),
                            GenericUtil.getGenericType(this));
                    if (savedData != null) {
                        onDataTransform(savedData, true);
                    }
                    if (isNeedToUpdate(cacheData.updateTimeInMillis)) {
                        return;
                    }
                } else if (!TextUtils.isEmpty(mPredefinedData)) {
                    NETWORK_DATA savedData = GsonUtils.fromJson(mPredefinedData,
                            GenericUtil.getGenericType(this));
                    if (savedData != null) {
                        onDataTransform(savedData, true);
                    }
                }
            }
            load();
        }
    }

    public void loadNextPage() {
        if (!mIsLoading) {
            mIsLoading = true;
            load();
        }
    }

    protected boolean isRefresh() {
        return pageNumber == INIT_PAGE_NUMBER;
    }

    /**
     * 加载网络数据
     */
    protected abstract void load();

    protected void notifyResultToListener(NETWORK_DATA networkData, RESULT_DATA resultData,
                                          boolean isFromCache) {
        if (iBaseModelListener != null) {
            if (mIsPaging) {
                boolean isEmpty;
                if (resultData instanceof List) {
                    isEmpty = ((List) resultData).isEmpty();
                } else {
                    isEmpty = resultData == null;
                }
                iBaseModelListener.onLoadFinish(resultData, new PagingResult(isEmpty, isRefresh(), !isEmpty));

                if (!isEmpty) {
                    pageNumber++;
                }
            } else if (isFromCache || !CacheData.isSameAsCached(mCachedKey, resultData)) {
                iBaseModelListener.onLoadFinish(resultData);
            }
            //save resultData to preference
            if (!isFromCache) {
                if (mIsPaging) {
                    if (mCachedKey != null && pageNumber == INIT_PAGE_NUMBER) {
                        CacheData.saveDataToCache(mCachedKey, networkData, resultData);
                    }
                } else {
                    if (mCachedKey != null) {
                        CacheData.saveDataToCache(mCachedKey, networkData, resultData);
                    }
                }
            }
        }
        mIsLoading = false;
    }

    protected void notifyFailureToListener(String errorMessage) {
        if (iBaseModelListener != null) {
            if (mIsPaging) {
                iBaseModelListener.onLoadFail(errorMessage, new PagingResult(true, isRefresh(),false));
            } else {
                iBaseModelListener.onLoadFail(errorMessage);
            }
        }
        mIsLoading = false;
    }

    @Override
    public void onFailure(Throwable e) {
        notifyFailureToListener(e.getMessage());
    }

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