package com.peaut.vegetables.qrhandle.activity

import android.arch.lifecycle.ViewModel
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.ViewGroup
import android.webkit.*
import com.peaut.vegetables.R
import com.peaut.vegetables.common.Constant
import com.peaut.vegetables.util.aboveApi
import com.peaut.vegetables.util.toggleVisibility
import com.peaut.vegetables.view.BaseActivity
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : BaseActivity() {
    private lateinit var webSettings:WebSettings
    override fun getResId(): Int = R.layout.activity_web

    override fun initView(savedInstanceState: Bundle?) {
        setStatusBarWithBlack()
        setStatusBarHeight(myStatusBar)
        webSettings = webView.settings
    }

    override fun initData(savedInstanceState: Bundle?) {
        val url = intent.getStringExtra(Constant.WEB_URL)
        webView.loadUrl(url)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url?.toString())
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBar.toggleVisibility()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.toggleVisibility()
            }

            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                super.onReceivedSslError(view, handler, error)
                aboveApi(Build.VERSION_CODES.LOLLIPOP,true) {
                    webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                }
                handler?.proceed()
                progressBar.toggleVisibility()
            }

            override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                super.onReceivedError(view, errorCode, description, failingUrl)
                tv_title.text = "$errorCode $description"
                progressBar.toggleVisibility()
            }
        }

        webView.webChromeClient = object :WebChromeClient(){
            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                tv_title.text = title
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                progressBar.progress = newProgress
            }
        }
    }

    override fun initViewModel(): ViewModel? {
        return null
    }

    override fun onDestroy() {
        webView.loadDataWithBaseURL(null,"","text/html","utf-8",null)
        webView.clearHistory()
        (webView.parent as ViewGroup).removeView(webView)
        webView.destroy()
        super.onDestroy()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
