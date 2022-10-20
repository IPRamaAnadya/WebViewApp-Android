package com.recreation.webviewmanagecookies

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {


    private lateinit var webview: WebView
    private var progressBar: LinearLayout? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webview = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)

        CookieManager.getInstance().setAcceptThirdPartyCookies(webview, true)
        webview.settings.javaScriptEnabled = true
        webview.loadUrl(URL)
        webview.webViewClient = MyWebViewClient()
    }

    inner class MyWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url!!)
            Log.d("WEBVIEWCLIENT", "Load URL > $url")
            return true
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

            progressBar?.isVisible = true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            progressBar?.isVisible = false
        }
    }

    override fun onBackPressed() {
        if(webview.canGoBack()) webview.goBack()
        else super.onBackPressed()
    }


    companion object {
        private const val URL = "https://harimau.sumtestapp.xyz/sign_in"
    }

}

