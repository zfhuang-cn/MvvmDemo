package com.ant.network.environment;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.Utils;

public class EnvironmentActivity {
    private static final String NETWORK_ENVIRONMENT_PREF_KEY = "environment";

    public static boolean isOfficialEnvironment() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Utils.getApp());
        return prefs.getBoolean(NETWORK_ENVIRONMENT_PREF_KEY, true);
    }
}