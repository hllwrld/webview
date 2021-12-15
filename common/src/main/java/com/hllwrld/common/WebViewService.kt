package com.hllwrld.common

import android.content.Context

interface WebViewService {

    fun startWebView(context: Context, url:String, title:String, isShowAction:Boolean)
}