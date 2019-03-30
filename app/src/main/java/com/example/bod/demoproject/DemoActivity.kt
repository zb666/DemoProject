package com.example.bod.demoproject

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView

import com.example.bod.demoproject.service.DemoService
import com.example.bod.demoproject.service.JumpService

import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask

class DemoActivity : AppCompatActivity() {
    internal var i = 0

    //通过connection建立起了连接
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val downmloadBinder = service as JumpService.DownmloadBinder
            val progress = downmloadBinder.getProgress()
            Log.d("DemoActivity", "获取到了来自于服务的进度: $progress")
            val textView = findViewById<TextView>(R.id.tvJumpService)
            textView.text = "获取到的进度是：$progress"
        }

        override fun onServiceDisconnected(name: ComponentName) {

        }
    }

    private var username: String = ""

    private var age: Int = 0

    init {
        username = "username"
        age = 23
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent()
        intent.setClass(this,MainActivity::class.java);
        startActivity(intent)

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("onNewIntent","OnNewIntent")
    }

    fun testFeature() {
        val callable = CallableDemo()
        val futureTask = FutureTask(callable)
        val executor = Executors.newFixedThreadPool(2)
        executor.execute(futureTask)
        try {
            val call = callable.call()
            Log.d("BobFeature", "得到call返回值的结果")
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            val futureResult = futureTask.get()
            Log.d("BobFeature", futureResult)
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }

    private fun notifyWaitingThread() {
        synchronized(sObject) {
            //synchronized 具有原子性，即使即使唤醒B,也要等A中的代码块走完之后才行。
        }
    }

    private fun testWait() {
        val runnable = Runnable {
            synchronized(sObject) {
                val integer = Integer.valueOf(Thread.currentThread().name)
                if (integer == 1) {
                    SystemClock.sleep(1000)
                }
                if (integer == 2) {
                    SystemClock.sleep(6000)
                }
                Log.d("BobWait", Thread.currentThread().name + "获得了锁 开始打印")
                try {
                    Log.d("BobWait", Thread.currentThread().name + " 开始恢复打印")
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                //                    testWaitThread();
            }
        }
        val threadA = Thread(runnable, "1")
        val threadB = Thread(runnable, "2")
        threadA.start()
        threadB.start()
    }

    //释放收个获得锁的线程
    private fun testWaitThread() {
        try {
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }

    fun testContext() {

        var a = 10
        val runnable = object : Runnable {
            override fun run() {
                val mCurThreadName = Thread.currentThread().name
                val mCurThreadId = Integer.valueOf(mCurThreadName)
                synchronized(this) {
                    when (a) {
                        0 -> print("start load")
                        2 -> print("222")

                    }
                }
            }
        }

        val t1 = Thread(runnable, "1")
        val t2 = Thread(runnable, "2")
        val t3 = Thread(runnable, "3")
        t1.start()
        t2.start()
        t3.start()
    }

    fun execute(x: Int, operation: Operation) = when (operation) {
        is Operation.Add -> x
        is Operation.Divide -> x
    }

    internal inner class CallableDemo : java.util.concurrent.Callable<String> {
        @Throws(Exception::class)
        override fun call(): String {
            return "得到返回值的结果"
        }
    }

    companion object {

        private val sObject = Any()
    }


    interface IBase {
        fun print()
    }

    //被委托者
    class BaseImpl(val x: Int) : IBase {
        override fun print() {
            Log.d("BobPrint", "我是被委托者")
        }
    }

    class Derived(b: IBase) : IBase by b


}
