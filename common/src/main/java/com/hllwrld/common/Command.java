package com.hllwrld.common;

import java.util.Map;

public interface Command {
    String name();
    void execute(Map parameters, ICallbackFromMainprocessToWebViewProcessInterface callback);
}
