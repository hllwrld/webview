package com.hllwrld.webview.webviewprocess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.hllwrld.webview.R
import com.hllwrld.webview.databinding.ActivityWebViewBinding

const val WEBVIEW_ACTIVITY_URL = "url"
const val WEBVIEW_ACTIVITY_TITLE = "title"
const val WEBVIEW_ACTIVITY_IS_SHOW_ACTIONBAR = "is_show_actionbar"

class WebViewActivity : AppCompatActivity() {

    lateinit var mUrl:String
    var mIsShowActionBar:Boolean = false
    lateinit var mBinding: ActivityWebViewBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this , R.layout.activity_web_view)

        mUrl = intent?.extras?.getString(WEBVIEW_ACTIVITY_URL)?:"www.baidu.com"
        mIsShowActionBar = intent?.extras?.getBoolean(WEBVIEW_ACTIVITY_IS_SHOW_ACTIONBAR)?:true

        if (!mIsShowActionBar) {
            mBinding.actionBar.visibility=  View.GONE
        }

        mBinding.title.text = intent?.extras?.getString(WEBVIEW_ACTIVITY_TITLE)?:"WebView"
        if (!mIsShowActionBar) {
            mBinding.actionBar.visibility = View.GONE
        }
        mBinding.back.setOnClickListener { this@WebViewActivity.finish() }
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val fragment: Fragment =
            WebViewFragment.newInstance(intent.getStringExtra(WEBVIEW_FRAGMENT_URL), canNativeRefresh =
            intent?.extras?.getBoolean(WEBVIEW_FRAGMENT_CAN_NATIVE_REFRESH) ?: true)
        transaction.replace(R.id.web_view_fragment, fragment).commit()
    }
}