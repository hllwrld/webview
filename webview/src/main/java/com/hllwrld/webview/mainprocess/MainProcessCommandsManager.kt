package com.hllwrld.webview.mainprocess

import android.os.IBinder
import com.hllwrld.common.ICallbackFromMainprocessToWebViewProcessInterface
import com.hllwrld.common.IWebviewProcessToMainProcessInterface


class MainProcessCommandsManager :IWebviewProcessToMainProcessInterface.Stub() {


    override fun handleWebCommand(
        commandName: String?,
        jsonParams: String?,
        callback: ICallbackFromMainprocessToWebViewProcessInterface?
    ) {


    }
}
