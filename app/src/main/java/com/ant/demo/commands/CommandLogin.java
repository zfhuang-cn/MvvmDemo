package com.ant.demo.commands;

import android.os.RemoteException;

import androidx.lifecycle.Observer;

import com.ant.webview.Command;
import com.ant.webview.ICallbackFromMainProcessToWebViewProcessInterface;
import com.google.auto.service.AutoService;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.Map;

@AutoService({Command.class})
public class CommandLogin implements Command, Observer<String> {

    private ICallbackFromMainProcessToWebViewProcessInterface callback;
    private String callbackNameFromNativeToJs;

    @Override
    public String name() {
        return "login";
    }

    @Override
    public void execute(Map parameters, ICallbackFromMainProcessToWebViewProcessInterface callback) {
        this.callback = callback;
        this.callbackNameFromNativeToJs = parameters.get("callbackName").toString();
        LiveEventBus.get("login", String.class).observeForever(this);
        login();
    }

    private void login() {
    }

    @Override
    public void onChanged(String res) {
        try {
            callback.onResult(callbackNameFromNativeToJs, res);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        LiveEventBus.get("login", String.class).removeObserver(this);
    }
}