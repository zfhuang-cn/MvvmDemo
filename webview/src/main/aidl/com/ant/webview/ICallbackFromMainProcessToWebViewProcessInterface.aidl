// ICallbackFromMainProcessToWebViewProcessInterface.aidl
package com.ant.webview;

interface ICallbackFromMainProcessToWebViewProcessInterface {

    void onResult(String callbackName, String response);
}