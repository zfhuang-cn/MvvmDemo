package com.ant.demo;

import com.ant.common.config.ModuleLifecycleConfig;
import com.ant.core.BaseApplication;

public class AntApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        setDebug(BuildConfig.DEBUG);
        // 初始化需要初始化的组件
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
    }
}