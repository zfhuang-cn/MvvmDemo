package com.ant.common.config;

import androidx.annotation.Nullable;

import com.ant.common.IModuleInit;
import com.ant.core.BaseApplication;

/**
 * 应用模块: common
 * <p>
 * 类描述: 作为组件生命周期初始化的配置类,通过反射机制,动态调用每个组件初始化逻辑
 * <p>
 *
 * @author
 * @date 2021/5/28
 */
public class ModuleLifecycleConfig {
    private ModuleLifecycleConfig() {
    }

    private static class SingleHolder
    {
        private static ModuleLifecycleConfig instance =
                new ModuleLifecycleConfig();
    }

    public static ModuleLifecycleConfig getInstance()
    {
        return SingleHolder.instance;
    }

    /** 优先初始化 */
    public void initModuleAhead(@Nullable BaseApplication application)
    {
        for (String moduleName : ModuleLifecycleReflex.initModuleNames)
        {
            try
            {
                Class<?> clazz = Class.forName(moduleName);
                IModuleInit init = (IModuleInit)clazz.newInstance();
                init.onInitAhead(application);
            }
            catch (ClassNotFoundException | InstantiationException
                    | IllegalAccessException e)
            {
                e.printStackTrace();
            }

        }

    }

    /** 后初始化 */
    public void initModuleLow(@Nullable BaseApplication application)
    {
        for (String moduleName : ModuleLifecycleReflex.initModuleNames)
        {
            try
            {
                Class<?> clazz = Class.forName(moduleName);
                IModuleInit init = (IModuleInit)clazz.newInstance();
                // 调用初始化方法
                init.onInitLow(application);
            }
            catch (ClassNotFoundException | InstantiationException
                    | IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
    }
}