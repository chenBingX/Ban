package com.coorchice.ban.model.web.activity

import android.content.Context
import android.content.Intent
import android.net.http.SslError
import android.os.Build
import android.webkit.SslErrorHandler
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.coorchice.ban.R
import com.coorchice.ban.model.base.BaseActivity
import com.coorchice.ban.utils.logi
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.layout_header_bar.*

class WebActivity : BaseActivity() {

    companion object {
        val KEY_URL = "key_url"
        val KEY_TITLE = "key_title"

        fun getIntent(context: Context, url: String?, title: String?): Intent {
            var intent = Intent(context, WebActivity::class.java)
            intent.putExtra(KEY_URL, url)
            intent.putExtra(KEY_TITLE, title)
            return intent
        }
    }

    override fun getContentViewResId(): Int {
        return R.layout.activity_web
    }

    override fun initData() {
    }

    override fun initView() {
        tv_title.text = intent.getStringExtra(KEY_TITLE)
        initWebView()
    }

    private fun initWebView() {
        val client = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                web_view.loadUrl(url)
                return true
            }

            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }
        }
        web_view.setWebViewClient(client)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            web_view.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        web_view.settings.javaScriptEnabled = true
        web_view.settings.blockNetworkImage = false
        web_view.settings.setAppCacheEnabled(true)
        web_view.settings.databaseEnabled = true
        web_view.settings.domStorageEnabled = true
        web_view.settings.cacheMode = WebSettings.LOAD_DEFAULT
        val url = intent.getStringExtra(KEY_URL)
        web_view.loadUrl(url)
        logi("URL -> " + url)
    }

    override fun addListener() {
        btn_back.setOnClickListener {
            finish()
        }
    }
}
