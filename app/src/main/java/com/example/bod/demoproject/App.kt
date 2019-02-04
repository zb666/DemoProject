package com.example.bod.demoproject

import android.app.Application

class App : Application() {

    private var mA: A? = null

    override fun onCreate() {
        super.onCreate()
        NetManager.instance.initNetStrategy(OkHttpStrategy())
        mA = A()
    }

}
