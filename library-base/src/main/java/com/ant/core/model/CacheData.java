package com.ant.core.model;

import android.text.TextUtils;

import com.blankj.utilcode.util.CacheDiskUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 应用模块:
 * <p>
 * 类描述: 缓存数据
 * <p>
 *
 * @author: zfhuang
 */
public class CacheData {

    @SerializedName("networkData")
    @Expose
    public Object networkData;

    @SerializedName("updateTimeInMillis")
    @Expose
    public long updateTimeInMillis;

    @SerializedName("resultData")
    @Expose
    public Object resultData;

    public static void saveDataToCache(String cachedKey, Object data, Object resultData) {
        if (data != null) {
            CacheData cacheData = new CacheData();
            cacheData.networkData = data;
            cacheData.resultData = resultData;
            cacheData.updateTimeInMillis = System.currentTimeMillis();
            CacheDiskUtils.getInstance().put(cachedKey, GsonUtils.toJson(cacheData));
        }
    }

    public static CacheData getCacheData(String cachedKey) {
        String cachedData = CacheDiskUtils.getInstance().getString(cachedKey);
        if (TextUtils.isEmpty(cachedData)) {
            return null;
        }
        return GsonUtils.fromJson(cachedData, CacheData.class);
    }

    public static boolean isSameAsCached(String cachedKey, Object resultData) {
        String jsonStr = SPStaticUtils.getString(cachedKey, "");
        if (TextUtils.isEmpty(jsonStr)) {
            return false;
        }
        CacheData cacheData = GsonUtils.fromJson(jsonStr, CacheData.class);
        if (cacheData == null || resultData == null) {
            return false;
        }
        return GsonUtils.toJson(cacheData.resultData).equals(GsonUtils.toJson(resultData));
    }

    public String getNetworkDataString() {
        return GsonUtils.toJson(networkData);
    }
}