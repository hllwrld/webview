package com.hllwrld.webview.webviewprocess

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.hllwrld.base.BaseApplication
import com.hllwrld.common.ICallbackFromMainprocessToWebViewProcessInterface
import com.hllwrld.common.IWebviewProcessToMainProcessInterface
import com.hllwrld.webview.mainprocess.MainProcessCommandService

object WebViewProcessCommandsDispatcher : ServiceConnection{
    private var iWebviewProcessToMainProcessInterface:IWebviewProcessToMainProcessInterface? = null

    fun initAidlConnection() {
        val intent = Intent(BaseApplication.sApplication, MainProcessCommandService::class.java)
        BaseApplication.sApplication?.bindService(intent, this, Context.BIND_AUTO_CREATE)
    }

    override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
        iWebviewProcessToMainProcessInterface = IWebviewProcessToMainProcessInterface.Stub.asInterface(p1)
    }

    override fun onServiceDisconnected(p0: ComponentName?) {
        iWebviewProcessToMainProcessInterface = null
        initAidlConnection()
    }

    fun executeCommand(commandName: String, parameters: String, baseWebView: BaseWebView) {
        iWebviewProcessToMainProcessInterface?.handleWebCommand(
            commandName,
            parameters,
            object : ICallbackFromMainprocessToWebViewProcessInterface.Stub() {
                override fun onResult(kotlinToJavescriptCallBackName: String?, response: String?) {
                    baseWebView.handleCallback(kotlinToJavescriptCallBackName, response)
                }


            }

        )

    }

}