package com.peaut.vegetables.qrhandle.activity

import android.annotation.SuppressLint
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
import com.peaut.vegetables.util.gone
import com.peaut.vegetables.util.show
import com.peaut.vegetables.view.BaseActivity
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : BaseActivity() {
    private lateinit var webSettings: WebSettings
    override fun getResId(): Int = R.layout.activity_web

    override fun initView(savedInstanceState: Bundle?) {
        setStatusBarWithBlack()
        setStatusBarHeight(myStatusBar)
        webSettings = webView.settings
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initData(savedInstanceState: Bundle?) {
        val url = intent.getStringExtra(Constant.WEB_URL)
        webView.loadUrl(url)
        webSettings.javaScriptEnabled = true
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webView.webViewClient = webViewClient
        webView.webChromeClient = webChromeClient
    }

    private val webChromeClient = object : WebChromeClient() {
        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            tv_title.text = title
        }

        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            progressBar.progress = newProgress
        }
    }

    private val webViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {

            aboveApi(Build.VERSION_CODES.O, true) {
                return false
            }
            view?.loadUrl(request?.url?.toString())
            return true
            /*val currentUrl = request?.url?.toString() ?: return super.shouldOverrideUrlLoading(view, request)
            if (currentUrl.startsWith("http") || currentUrl.startsWith("https")){
                aboveApi(Build.VERSION_CODES.O, true) {
                    return false
                }
                view?.loadUrl(currentUrl)
                return true
            }else {
                return try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(currentUrl))
                    startActivity(intent)
                    true
                }catch (e: Exception) {
                    false
                }
            }*/
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            progressBar.show()
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            progressBar.gone()
        }

        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            super.onReceivedSslError(view, handler, error)
            aboveApi(Build.VERSION_CODES.LOLLIPOP, true) {
                webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
            handler?.proceed()
        }

        override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
            super.onReceivedError(view, errorCode, description, failingUrl)
//                tv_title.text = "$errorCode $description"
            progressBar.gone()
        }
    }

    override fun initListener(savedInstanceState: Bundle?) {
        super.initListener(savedInstanceState)
        ib_back.setOnClickListener { onBackPressed() }
    }

    override fun initViewModel(): ViewModel? {
        return null
    }

    override fun onDestroy() {
        webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
        webView.clearCache(true)
        webView.clearHistory()
        (webView.parent as ViewGroup).removeView(webView)
        webView.destroy()
        super.onDestroy()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
                webView.goBack()
            } else {
                finish()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
