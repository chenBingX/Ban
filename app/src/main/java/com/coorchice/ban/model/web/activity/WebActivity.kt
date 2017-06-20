package com.coorchice.ban.model.web.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.coorchice.ban.R
import com.coorchice.ban.model.base.BaseActivity
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

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        val client = WebViewClient()
        web_view.setWebViewClient(client)
        web_view.settings.javaScriptEnabled = true
        web_view.settings.cacheMode = WebSettings.LOAD_DEFAULT
        web_view.settings.setAppCacheEnabled(true)
        web_view.settings.setSupportZoom(true)
        web_view.loadUrl(intent.getStringExtra(KEY_URL))
    }

    override fun addListener() {
        btn_back.setOnClickListener {
            finish()
        }
    }
}
