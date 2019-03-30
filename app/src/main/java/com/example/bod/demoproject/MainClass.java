package com.example.bod.demoproject;

import android.content.Intent;

import kotlin.jvm.JvmOverloads;

public class MainClass {
    public static int a;

    void main(){
        A mA = new A();
        String javaCallKotlinResult = mA.javaCallKotlin(1, 2);
    }

}
