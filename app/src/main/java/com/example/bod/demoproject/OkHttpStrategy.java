package com.example.bod.demoproject;

import android.util.Log;

import java.util.HashMap;

public class OkHttpStrategy implements INetStrategy {
    @Override
    public void doRequest(HashMap<String, String> hashMap, ICallback iCallback) {
        //这里进行实际的请求
        Log.d("OkHttpStrategy","OkHttpStrategy");
        //这里请求完毕  然后执行回调的操作
        iCallback.success("请求成功");
    }


}
