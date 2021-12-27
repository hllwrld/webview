package com.hllwrld.webview.webviewprocess

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.hllwrld.base.loadsir.ErrorCallback
import com.hllwrld.base.loadsir.LoadingCallback
import com.hllwrld.webview.R
import com.hllwrld.webview.databinding.FragmentWebviewBinding
import com.hllwrld.webview.webviewprocess.webviewclient.WebViewCallBack
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener


const val WEBVIEW_FRAGMENT_URL = WEBVIEW_ACTIVITY_URL
const val WEBVIEW_FRAGMENT_CAN_NATIVE_REFRESH = "can_native_refresh"


const val TAG = "WebViewFragment"
class WebViewFragment:Fragment(), OnRefreshListener, WebViewCallBack {

    private var mUrl:String? = null
    private var mCanNativeRefresh = true
    lateinit var mBinding:FragmentWebviewBinding
    lateinit var mLoadService: LoadService<*>
    var mIsError:Boolean = false


    companion object {
        fun newInstance(url: String?, canNativeRefresh:Boolean):WebViewFragment {
            val fragment = WebViewFragment()
            val bundle = Bundle()
            bundle.putString(WEBVIEW_FRAGMENT_URL, url)
            bundle.putBoolean(WEBVIEW_FRAGMENT_CAN_NATIVE_REFRESH, canNativeRefresh)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        bundle?.let {
            mUrl = it.getString(WEBVIEW_FRAGMENT_URL)
            mCanNativeRefresh = bundle.getBoolean(WEBVIEW_FRAGMENT_CAN_NATIVE_REFRESH)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false)
        mBinding.webview.registerWebViewCallBack(this)
        mBinding.webview?.loadUrl(mUrl!!)
        mLoadService = LoadSir.getDefault().register(mBinding.smartrefreshlayout) {
            mLoadService.showCallback(LoadingCallback::class.java)
            mBinding.webview.reload()
        }
        mBinding.smartrefreshlayout.setOnRefreshListener(this)
        mBinding.smartrefreshlayout.isEnableRefresh = mCanNativeRefresh
        mBinding.smartrefreshlayout.isEnableLoadMore = false
        return mLoadService.loadLayout
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mBinding.webview.reload()
    }

    override fun pageStared(url: String) {
        mLoadService?.showCallback(LoadingCallback::class.java)
    }

    override fun pageFinish(url: String) {
        Log.d(TAG, "pageFinish" )
        mBinding.smartrefreshlayout.isEnableRefresh = if(mIsError) true else mCanNativeRefresh
        mBinding.smartrefreshlayout.finishRefresh()
        mLoadService?.let {
            if (mIsError) {
                mLoadService.showCallback(ErrorCallback::class.java)
            } else {
                mLoadService.showSuccess()
            }
        }
    }

    override fun onError() {
        Log.d(TAG, "onError")
        mIsError = true
        mBinding.smartrefreshlayout.finishRefresh()
    }

    override fun updateTitle(title: String) {
        TODO("Not yet implemented")
    }
}