package com.hllwrld.webview

import android.content.Context
import android.content.Intent
import com.hllwrld.common.WebViewService
import com.google.auto.service.AutoService


@AutoService(WebViewService::class)
class WebViewServiceImpl : WebViewService{

    override fun startWebView(context: Context, url: String) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra(WEBVIEW_URL, url)
        context.startActivity(intent)
    }
}