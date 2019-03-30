package com.example.bod.demoproject

import android.view.View

class A {

    @JvmOverloads
    fun javaCallKotlin(a: Int, b: Int, c: String = "ccc"): String = (a + b).toString() + "$c"

    internal fun test() {
        val view = View(null)
        NetManager.instance.doRealNetRequest(null, object : ICallback {
            override fun success(suc: String) {

            }

            override fun onFail(code: Int, errMsg: String) {

            }
        })
    }

    @JvmOverloads
    internal fun a(a: Int, b: Int, c: String = "defaultStringC") {

    }
}

