package com.ant.common.router;

/**
 * 应用模块: 组件化路由
 * <p>
 * 类描述: 用于组件化开发中,ARouter Fragment路径统一注册, 注册的路径请写好注释、标注业务功能
 * <p>
 *
 * @author zfhuang
 */
public class RouterFragmentPath {
    /** 首页组件 */
    public static class Home
    {
        private static final String HOME = "/home";

        /** 首页 */
        public static final String PAGER_HOME = HOME + "/Home";

    }

    public static class User
    {
        private static final String USER = "/user";

        /** 个人中心 */
        public static final String PAGER_USER = USER + "/User";
    }
}