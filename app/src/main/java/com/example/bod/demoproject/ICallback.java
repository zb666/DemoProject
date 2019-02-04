package com.example.bod.demoproject;

public interface ICallback {
    void success(String suc);
    void onFail(int code,String errMsg);
}
