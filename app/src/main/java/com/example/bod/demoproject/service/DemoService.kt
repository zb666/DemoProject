package com.example.bod.demoproject.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class DemoService : Service() {


    val TAG = "DemoService"

    val name:String by lazy {
        "DemoService"
    }

    override fun onBind(intent: Intent): IBinder? {
        return InnerBinder()
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "---onCreate()---"+name)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG, "---onStartCommand()---")

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.d(TAG, "---onUnbind()---")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.d(TAG, "---onDestroy()---")

        super.onDestroy()
    }

    internal inner class InnerBinder : Binder() {
        val progress: Int
            get() = 80
    }

    companion object {
        private val TAG = "DemoService"
    }
}
