package com.hllwrld.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.hllwrld.common.WebViewService
import com.hllwrld.common.WebViewServiceLoader


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.startWebView)
        btn.setOnClickListener {
            WebViewServiceLoader.loadService(WebViewService::class.java)?.
            startWebView(this, "www.baidu.com")
        }
    }
}