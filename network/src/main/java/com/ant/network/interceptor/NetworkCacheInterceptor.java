package com.ant.network.interceptor;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Interceptor;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @author: zfhuang
 * @date: 2021/6/11
 */
public class NetworkCacheInterceptor implements Interceptor {

    private static final int DEF_CACHE_MAX_AGE = 10;// 默认在线缓存10秒 单位:秒

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .header("User-Agent", "ant/1.0;" + System.getProperty("http.agent"))
                .build();

        Response response = chain.proceed(request);
        // 给get请求增加缓存
        if (response.isSuccessful() && request.method().equalsIgnoreCase("get")) {
            String cacheControl = request.cacheControl().toString();
            if (TextUtils.isEmpty(cacheControl)) {
                // 如果请求接口中未设置cacheControl，则统一设置
                return response.newBuilder()
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .header("Cache-Control", "public, max-age=" + DEF_CACHE_MAX_AGE)
                        .build();
            }
        }
        return response;
    }
}