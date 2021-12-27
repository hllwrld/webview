// ICallbackFromMainprocessToWebViewProcessInterface.aidl
package com.hllwrld.common;

interface ICallbackFromMainprocessToWebViewProcessInterface {
    void onResult(String kotlinToJavescriptCallBackName, String response);
}
