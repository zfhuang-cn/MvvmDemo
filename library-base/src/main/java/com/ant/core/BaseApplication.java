package com.ant.core;

import android.app.Application;

public class BaseApplication extends Application {

    public static BaseApplication sApplication;

    private static boolean sDebug;


    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }


    protected void setDebug(boolean isDebug)
    {
        sDebug = isDebug;
    }

    public boolean isDebug() {
        return sDebug;
    }
}