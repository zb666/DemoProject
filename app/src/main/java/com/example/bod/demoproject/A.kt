package com.example.bod.demoproject

import android.view.View

class A {
    internal fun test() {
        val view = View(null)
        NetManager.instance.doRealNetRequest(null, object : ICallback {
            override fun success(suc: String) {

            }

            override fun onFail(code: Int, errMsg: String) {

            }
        })
    }
}
