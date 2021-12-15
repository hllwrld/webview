package com.hllwrld.webview

import android.content.Context
import android.content.Intent
import com.hllwrld.common.WebViewService
import com.google.auto.service.AutoService


@AutoService(WebViewService::class)
class WebViewServiceImpl : WebViewService{

    override fun startWebView(context: Context, url: String, title:String, isShowActionBar:Boolean) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra(WEBVIEW_URL, url)
        intent.putExtra(WEBVIEW_IS_SHOW_ACTIONBAR, isShowActionBar)
        intent.putExtra(WEBVIEW_TITLE, title)
        context.startActivity(intent)
    }
}