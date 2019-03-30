package com.example.bod.demoproject.service

import android.app.Service
import android.content.Intent
import android.nfc.Tag
import android.os.Binder
import android.os.IBinder
import android.util.Log

class JumpService : Service() {

    val TAG = "jumpservice"

    val name:String by lazy {
        "name"
    }

    val nameGet:String get() {
        return javaClass.simpleName
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "oncreate()"+nameGet)
    }

    val mBinder = DownmloadBinder()

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand()")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestory()")
        super.onDestroy()
    }

   open class DownmloadBinder : Binder() {
        fun download() {
            Log.d("jumpservice", "startdownload executed")
        }
        fun getProgress(): Int = 80
    }


}
