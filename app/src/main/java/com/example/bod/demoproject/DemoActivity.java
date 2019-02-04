package com.example.bod.demoproject;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class DemoActivity extends AppCompatActivity {

    private static final Object sObject = new Object();
    int i = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        testContext();
        findViewById(R.id.tvWait).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                testWaitThread();
                testWait();
            }
        });

        findViewById(R.id.tvNotify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyWaitingThread();
            }
        });
    }

    private void notifyWaitingThread() {
        synchronized (sObject) {
            //synchronized 具有原子性，即使即使唤醒B,也要等A中的代码块走完之后才行。
            sObject.notifyAll();
        }
    }

    private void testWait() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                synchronized (sObject) {
                    Integer integer = Integer.valueOf(Thread.currentThread().getName());
                    if (integer == 1) {
                        SystemClock.sleep(1000);
                    }
                    if (integer == 2) {
                        SystemClock.sleep(6000);
                    }
                    Log.d("BobWait", Thread.currentThread().getName() + "获得了锁 开始打印");
                    try {
                        sObject.wait();
                        Log.d("BobWait", Thread.currentThread().getName() + " 开始恢复打印");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    testWaitThread();
                }
            }
        };
        Thread threadA = new Thread(runnable, "1");
        Thread threadB = new Thread(runnable, "2");
        threadA.start();
        threadB.start();
    }

    //释放收个获得锁的线程
    private void testWaitThread() {
        try {
            sObject.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    char c = 'a';
    int charActer = 0;

    public void testContext() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String mCurThreadName = Thread.currentThread().getName();
                Integer mCurThreadId = Integer.valueOf(mCurThreadName);
                synchronized (this) {
                    while (charActer < 26) {
                        //三个数据一次轮训
                        if (charActer % 3 == mCurThreadId - 1) {
                            //数据的轮训
                            notifyAll();
                            Log.d("bob", "threadId；" + mCurThreadName + "：" + (charActer++) + (c++));
//                            mCurThreadId++;
                            //资源输出完毕之后 让下一个线程得到cpu资源
                        } else {
                            try {
                                //锁的拥有者是当前的线程，所以wait或者notify的时候也必须是锁的拥有着，即为当前的线程
                                this.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        };

        Thread t1 = new Thread(runnable, "1");
        Thread t2 = new Thread(runnable, "2");
        Thread t3 = new Thread(runnable, "3");
        t1.start();
        t2.start();
        t3.start();
    }
}
