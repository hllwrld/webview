package com.hllwrld.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

const val WEBVIEW_URL = "url"
const val WEBVIEW_TITLE = "title"
const val WEBVIEW_IS_SHOW_ACTIONBAR = "is_show_actionbar"

class WebViewActivity : AppCompatActivity() {

    lateinit var mUrl:String
    lateinit var mTitlte:String
    var mIsShowActionBar:Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        mUrl = intent?.extras?.getString(WEBVIEW_URL)?:"www.baidu.com"
        mTitlte = intent?.extras?.getString(WEBVIEW_TITLE)?:"WebView"
        mIsShowActionBar = intent?.extras?.getBoolean(WEBVIEW_IS_SHOW_ACTIONBAR)?: false
        val webView = findViewById<WebView>(R.id.webview_id)
        webView.loadUrl(mUrl)
    }
}