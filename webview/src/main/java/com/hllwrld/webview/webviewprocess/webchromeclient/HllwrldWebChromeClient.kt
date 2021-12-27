package com.hllwrld.webview.webviewprocess.webchromeclient

import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.hllwrld.webview.webviewprocess.webviewclient.WebViewCallBack

const val TAG = "HllwrldWebChromeClient"

class HllwrldWebChromeClient(val mWebViewCallBack: WebViewCallBack) :WebChromeClient()  {



    override fun onReceivedTitle(view: WebView?, title: String?) {
        super.onReceivedTitle(view, title)
        mWebViewCallBack?.updateTitle(title!!)
    }


    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        Log.d(TAG, consoleMessage!!.message())
        return super.onConsoleMessage(consoleMessage)
    }
}