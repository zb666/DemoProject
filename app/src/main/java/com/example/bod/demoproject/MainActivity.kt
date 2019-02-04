package com.example.bod.demoproject

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URI
import java.net.URL

class MainActivity : AppCompatActivity() {

    val okHttpStrategy by lazy { NetManager.instance }

    var okHttpStrate: NetManager? = null

    internal var c = 'a'
    internal var charActer = 0


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //延迟初始化
        okHttpStrate = NetManager.instance

        webview.loadUrl("https://www.jianshu.com/p/7dfb8797f893")

        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//                view!!.loadUrl("https://www.baidu.com/")
                val intent = Intent()
                intent.setClass(view!!.context,InterceptActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        webview.settings.javaScriptEnabled =true


    }


}
