package com.ant.webview;

import com.ant.webview.ICallbackFromMainProcessToWebViewProcessInterface;

interface IWebviewProcessToMainProcessInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     void handleWebCommand(String commandName,String jsonParams,in ICallbackFromMainProcessToWebViewProcessInterface callback);
}