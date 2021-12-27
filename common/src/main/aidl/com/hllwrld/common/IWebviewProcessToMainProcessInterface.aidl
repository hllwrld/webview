// IWebviewProcessToMainProcessInterface.aidl
package com.hllwrld.common;

// Declare any non-default types here with import statements
import com.hllwrld.common.ICallbackFromMainprocessToWebViewProcessInterface;

interface IWebviewProcessToMainProcessInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void handleWebCommand(String commandName, String jsonParams, in ICallbackFromMainprocessToWebViewProcessInterface callback);
}
