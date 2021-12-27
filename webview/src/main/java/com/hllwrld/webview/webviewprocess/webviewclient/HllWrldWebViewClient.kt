package com.hllwrld.webview.webviewprocess.webviewclient

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient

class HllWrldWebViewClient(val mWebViewCallBack: WebViewCallBack): WebViewClient() {

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        mWebViewCallBack?.pageStared(url!!)

    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        mWebViewCallBack?.pageFinish(url!!)
    }


    override fun onReceivedError(
        view: WebView?,
        errorCode: Int,
        description: String?,
        failingUrl: String?
    ) {
        super.onReceivedError(view, errorCode, description, failingUrl)
        mWebViewCallBack?.onError()
    }
}