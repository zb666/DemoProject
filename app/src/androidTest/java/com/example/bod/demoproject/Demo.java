package com.example.bod.demoproject;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class Demo {

    char c = 'a';
    int charActer = 0;

    @Test
    public void testContext() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //防止多线程资源抢占导致的输出无须问题
                synchronized (this) {
                    String mCurThreadName = Thread.currentThread().getName();
                    Integer mCurThreadId = Integer.valueOf(mCurThreadName);

                    while (charActer < 26) {
                        //三个数据一次轮训
                        if (charActer % 3 == mCurThreadId - 1) {
                            notify();
                            System.out.print("threadId；" + mCurThreadName + " 输出元素：" + (charActer++));
                            mCurThreadId++;
                            //资源输出完毕之后 让下一个线程得到cpu资源
                        } else {
                            try {
                                wait();
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
