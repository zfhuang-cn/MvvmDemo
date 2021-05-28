package com.ant.common.autoservice;

import android.content.Context;

import androidx.fragment.app.Fragment;

interface IWebViewService {

    void startWebViewActivity(Context context, String url, String title, boolean isShowAction);

    Fragment getWebViewFragment(String url, boolean canNativeRefresh);

    void startDemoHtml(Context context);
}
