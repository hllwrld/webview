package com.hllwrld.webview

import android.content.Context
import android.content.Intent
import com.hllwrld.common.WebViewService
import com.google.auto.service.AutoService
import com.hllwrld.webview.webviewprocess.WEBVIEW_ACTIVITY_IS_SHOW_ACTIONBAR
import com.hllwrld.webview.webviewprocess.WEBVIEW_ACTIVITY_TITLE
import com.hllwrld.webview.webviewprocess.WEBVIEW_ACTIVITY_URL
import com.hllwrld.webview.webviewprocess.WebViewActivity


@AutoService(WebViewService::class)
class HllwrldWebViewServiceImpl : WebViewService{

    override fun startWebView(context: Context, url: String, title:String, isShowActionBar:Boolean) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra(WEBVIEW_ACTIVITY_URL, url)
        intent.putExtra(WEBVIEW_ACTIVITY_TITLE, isShowActionBar)
        intent.putExtra(WEBVIEW_ACTIVITY_IS_SHOW_ACTIONBAR, title)
        context.startActivity(intent)
    }
}