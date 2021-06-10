package com.ant.main;

import com.ant.common.IModuleInit;
import com.ant.common.JuheNetworkApi;
import com.ant.core.BaseApplication;
import com.ant.core.loadsir.EmptyCallback;
import com.ant.core.loadsir.ErrorCallback;
import com.ant.core.loadsir.LoadingCallback;
import com.ant.core.loadsir.TimeoutCallback;
import com.blankj.utilcode.util.Utils;
import com.kingja.loadsir.core.LoadSir;
import com.orhanobut.logger.Logger;

/**
 * 应用模块: main
 * <p>
 * 类描述: main组件的业务初始化
 * <p>
 *
 * @author: zfhuang
 * @date: 2021/5/28
 */
public class MainModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(BaseApplication application) {
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();
        Utils.init(application);
        JuheNetworkApi.init();
        Logger.i("main组件初始化完成 -- onInitAhead");
        return false;
    }

    @Override
    public boolean onInitLow(BaseApplication application) {
        return false;
    }
}