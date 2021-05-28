package com.ant.core.utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.ant.core.BaseApplication;

public class ToastUtil {
    private static Toast mToast;

    public static void show(String msg) {
        try {
            if (!TextUtils.isEmpty(msg)) {
                if (mToast != null) {
                    mToast.cancel();
                }
                mToast = Toast.makeText(BaseApplication.sApplication, msg, Toast.LENGTH_LONG);
                mToast.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}