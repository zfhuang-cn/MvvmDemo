package com.ant.common.router;

/**
 * 应用模块: 组件化路由
 * <p>
 * 类描述: 用于在组件化开发中,利用ARouter 进行Activity跳转的统一路径注册, 注册时请写好注释、标注界面功能业务
 * <p>
 *
 * @author zfhuang
 */
public class RouterActivityPath {
    /**
     * main组件
     */
    public static class Main {
        private static final String MAIN = "/main";

        /**
         * 主页面
         */
        public static final String PAGER_MAIN = MAIN + "/Main";
    }

    /**
     * 浏览网页(web)组件
     */
    public static class Web {
        private static final String WEB = "/web";

        /* 视频播放界面 */
        public static final String PAGER_WEB = WEB + "/Web";

    }

    public static class User {
        private static final String USER = "/user";

        /**
         * 登录界面
         */
        public static final String PAGER_LOGIN = USER + "/Login";

        /**
         * 关注页面
         */
        public static final String PAGER_ATTENTION = USER + "/attention";
    }
}