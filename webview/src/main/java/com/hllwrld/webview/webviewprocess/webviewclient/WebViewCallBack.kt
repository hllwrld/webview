package com.hllwrld.webview.webviewprocess.webviewclient

public interface WebViewCallBack {
    fun pageStared(url:String)
    fun pageFinish(url:String)
    fun onError()
    fun updateTitle(title:String)
}