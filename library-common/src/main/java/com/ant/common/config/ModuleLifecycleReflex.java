package com.ant.common.config;

/**
 * 应用模块: common
 * <p>
 * 类描述: 组件生命周期初始化类的类目管理者,在这里注册需要初始化的组件,通过反射动态调用各个组件的初始化方法
 * <p>
 *
 * @author : zfhuang
 * @date : 2021/5/28
 */
public class ModuleLifecycleReflex {
    /** 基础库 */
    private static final String BaseInit = "com.ant.common.CommonModuleInit";

    /** main组件库 */
    private static final String MainInit =
            "com.ant.main.MainModuleInit";

    /**用户组件初始化*/
    private static final String UserInit = "com.ant.user.UserModuleInit";

    public static String[] initModuleNames = {BaseInit, UserInit};
}