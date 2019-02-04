package com.example.bod.demoproject;

import java.util.HashMap;

public interface INetStrategy {
    void doRequest(HashMap<String, String> hashMap, ICallback iCallback);
}
