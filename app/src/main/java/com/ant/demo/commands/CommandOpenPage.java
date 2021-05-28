package com.ant.demo.commands;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;

import com.ant.core.BaseApplication;
import com.ant.webview.Command;
import com.ant.webview.ICallbackFromMainProcessToWebViewProcessInterface;
//import com.google.auto.service.AutoService;

import java.util.Map;

//@AutoService({Command.class})
public class CommandOpenPage implements Command {
    @Override
    public String name() {
        return "openPage";
    }

    @Override
    public void execute(Map parameters, ICallbackFromMainProcessToWebViewProcessInterface callback) {
        String targetClass = String.valueOf(parameters.get("target_class"));
        if (!TextUtils.isEmpty(targetClass)) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.sApplication, targetClass));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.sApplication.startActivity(intent);
        }
    }
}