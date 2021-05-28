package com.ant.webview.webviewprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.ant.core.BaseApplication;
import com.ant.webview.ICallbackFromMainProcessToWebViewProcessInterface;
import com.ant.webview.IWebviewProcessToMainProcessInterface;
import com.ant.webview.mainprocess.MainProcessCommandService;

public class WebViewProcessCommandDispatcher implements ServiceConnection {

    static WebViewProcessCommandDispatcher instance;

    private IWebviewProcessToMainProcessInterface iWebviewProcessToMainProcessInterface;

    public static WebViewProcessCommandDispatcher getInstance() {
        if (instance == null) {
            synchronized (WebViewProcessCommandDispatcher.class) {
                if (instance == null) {
                    instance = new WebViewProcessCommandDispatcher();
                }
            }
        }
        return instance;
    }

    public void initAidlConnection() {
        Intent intent = new Intent(BaseApplication.sApplication, MainProcessCommandService.class);
        BaseApplication.sApplication.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        iWebviewProcessToMainProcessInterface =
                IWebviewProcessToMainProcessInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        iWebviewProcessToMainProcessInterface = null;
        initAidlConnection();
    }

    @Override
    public void onBindingDied(ComponentName name) {
        iWebviewProcessToMainProcessInterface = null;
        initAidlConnection();
    }

    public void executeCommand(String commandName, String params, final BaseWebView baseWebView) {
        if (iWebviewProcessToMainProcessInterface != null) {
            try {
                iWebviewProcessToMainProcessInterface.handleWebCommand(commandName, params, new ICallbackFromMainProcessToWebViewProcessInterface.Stub() {
                    @Override
                    public void onResult(String callbackName, String response) throws RemoteException {
                        baseWebView.handleCallback(callbackName, response);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            initAidlConnection();
        }
    }
}