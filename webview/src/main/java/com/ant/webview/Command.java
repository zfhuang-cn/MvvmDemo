package com.ant.webview;

import java.util.Map;

public interface Command {
    String name();

    void execute(Map parameters, ICallbackFromMainProcessToWebViewProcessInterface callback);
}
