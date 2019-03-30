package com.coroutine.bod.onnewintent;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface HelloService extends IProvider {
    String getInput(String name);
}
