package com.hllwrld.webview.webviewprocess

import android.content.Context
import android.text.TextUtils
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.google.gson.Gson
import com.hllwrld.webview.bean.JsParam
import com.hllwrld.webview.webviewprocess.settings.WebViewDefaultSettings
import com.hllwrld.webview.webviewprocess.webviewclient.HllWrldWebViewClient
import com.hllwrld.webview.webviewprocess.webviewclient.WebViewCallBack

class BaseWebView(context: Context) : WebView(context) {

    init {

        WebViewProcessCommandsDispatcher.initAidlConnection()
        addJavascriptInterface(this, "hllwrld")
        WebViewDefaultSettings.getInstance().setSettings(this)

    }

    @JavascriptInterface
    fun takeNativeAction(jsParam: String) {
        if (!TextUtils.isEmpty(jsParam)) {
            val jsParamObject= Gson().fromJson(jsParam, JsParam::class.java)
            if (jsParamObject!=null) {
                WebViewProcessCommandsDispatcher.executeCommand(jsParamObject.name, Gson().toJson((jsParamObject.param)), this)
            }
        }
    }

    fun registerWebViewCallBack(webViewCallBack: WebViewCallBack) {
        webViewClient = HllWrldWebViewClient(webViewCallBack)
        webChromeClient = WebChromeClient()
    }

    fun handleCallback(callbackName:String?, response:String?) {
        if (!TextUtils.isEmpty(callbackName) && !TextUtils.isEmpty(response)) {
            post {
                val jscode = "javascript:xiangxuejs.callback('$callbackName',$response)"
                evaluateJavascript(jscode, null)
            }
        }

    }
}