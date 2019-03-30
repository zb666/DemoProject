package com.coroutine.bod.onnewintent;

import android.content.Context;

public class HelloServiceImpl implements HelloService {
    @Override
    public String getInput(String name) {
        return "Hello from Service";
    }

    @Override
    public void init(Context context) {

    }
}
