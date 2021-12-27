package com.hllwrld.webview.webviewprocess

import android.content.Context
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.hllwrld.webview.webviewprocess.webviewclient.WebViewCallBack
import com.hllwrld.webview.webviewprocess.webviewclient.HllWrldWebViewClient

class BaseWebView(context: Context) : WebView(context) {

    init {



    }

    fun init() {

    }

    fun registerWebViewCallBack(webViewCallBack: WebViewCallBack) {
        webViewClient = HllWrldWebViewClient(webViewCallBack)
        webChromeClient = WebChromeClient()
    }
}