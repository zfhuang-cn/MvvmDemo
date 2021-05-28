package com.ant.webview.webviewprocess;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class BaseWebView extends WebView {
    public BaseWebView(@NonNull Context context) {
        super(context);
        init();
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr,
                       int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        addJavascriptInterface(this, "antwebview");
        WebViewProcessCommandDispatcher.getInstance().initAidlConnection();
    }

    @JavascriptInterface
    public void takeNativeAction(final String jsParam) {
        if (!TextUtils.isEmpty(jsParam)) {
            try {
                JSONObject jsonObject = new JSONObject(jsParam);
                WebViewProcessCommandDispatcher.getInstance().executeCommand(jsonObject.optString("name"), jsonObject.optJSONObject("param").toString(), this);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleCallback(final String callbackName, final String response) {
        if (!TextUtils.isEmpty(callbackName) && !TextUtils.isEmpty(response)) {
            String jsCode = String.format("javascript:ant.callback('%s',%s)", callbackName, response);
            post(() -> evaluateJavascript(jsCode, null));
        }
    }
}