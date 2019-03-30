package com.coroutine.bod.onnewintent;

import com.alibaba.android.arouter.facade.annotation.Autowired;

public class Test {
    @Autowired
    HelloServiceImpl helloService;

    void testServic(){
        helloService.getInput("hhh");

    }
}
