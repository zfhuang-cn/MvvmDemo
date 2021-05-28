package com.ant.demo.commands;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.ant.core.BaseApplication;
import com.ant.webview.Command;
import com.ant.webview.ICallbackFromMainProcessToWebViewProcessInterface;
import com.google.auto.service.AutoService;

import java.util.Map;

//@AutoService({Command.class})
public class CommandShowToast implements Command {
    @Override
    public String name() {
        return "showToast";
    }

    @Override
    public void execute(Map parameters, ICallbackFromMainProcessToWebViewProcessInterface callback) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> Toast.makeText(BaseApplication.sApplication,
                String.valueOf(parameters.get("message")), Toast.LENGTH_LONG).show());
    }
}