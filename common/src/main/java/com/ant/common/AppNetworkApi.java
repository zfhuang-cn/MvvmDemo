package com.ant.common;

import com.ant.network.NetworkApi;

public class AppNetworkApi extends NetworkApi {
    private static volatile AppNetworkApi sInstance;

    public static AppNetworkApi getInstance() {
        if (sInstance == null) {
            synchronized (AppNetworkApi.class) {
                if (sInstance == null) {
                    sInstance = new AppNetworkApi();
                }
            }
        }
        return sInstance;
    }

    public static <T> T getService(Class<T> service) {
        return getInstance().getRetrofit(service).create(service);
    }

    @Override
    public String getFormal() {
        return null;
    }

    @Override
    public String getTest() {
        return null;
    }
}