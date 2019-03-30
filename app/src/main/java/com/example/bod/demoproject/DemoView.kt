package com.example.bod.demoproject

import android.content.Context
import android.util.AttributeSet
import android.view.View

class DemoView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {
    fun test() = "1"

    fun addResult(str:String): String {
        return "占位符的使用：$str"
    }
}
