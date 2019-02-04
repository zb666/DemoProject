package com.example.bod.demoproject

import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient

import java.util.HashMap

class NetManager private constructor() {


    private var iNetStrategy: INetStrategy? = null

    fun getiNetStrategy(): INetStrategy? {
        return iNetStrategy
    }

    fun initNetStrategy(iNetStrategy: INetStrategy) {
        this.iNetStrategy = iNetStrategy
    }

    fun doRealNetRequest(hashMap: HashMap<String, String>?, iCallback: ICallback) {
        iNetStrategy!!.doRequest(hashMap, iCallback)
    }

    companion object {

        @get:JvmStatic
        val instance = NetManager()
    }

}
