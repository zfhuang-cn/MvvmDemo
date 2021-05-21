package com.ant.network.environment;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class EnvironmentActivity {
    private static final String NETWORK_ENVIRONMENT_PREF_KEY = "environment";

    public static boolean isOfficialEnvironment(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean(NETWORK_ENVIRONMENT_PREF_KEY, true);
    }
}