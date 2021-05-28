package com.ant.common;

import com.ant.network.NetworkApi;

public class JuheNetworkApi extends NetworkApi {
    private static volatile JuheNetworkApi sInstance;

    public static JuheNetworkApi getInstance() {
        if (sInstance == null) {
            synchronized (JuheNetworkApi.class) {
                if (sInstance == null) {
                    sInstance = new JuheNetworkApi();
                }
            }
        }
        return sInstance;
    }

    @Override
    public String getFormal() {
        return "http://v.juhe.cn";
    }

    @Override
    public String getTest() {
        return "http://v.juhe.cn";
    }
}