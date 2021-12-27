package com.hllwrld.common

import android.content.Context

interface WebViewService {

    fun startWebView(context: Context, url:String, title:String="webView", isShowAction:Boolean=false)
}