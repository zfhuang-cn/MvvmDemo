package com.ant.core.model;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @author: zfhuang
 */
public interface NetworkDataTransformer<DATA> {
    void onDataTransform(DATA data, boolean isFromCache);

    void onFailure(Throwable e);
}
