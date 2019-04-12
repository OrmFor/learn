package com.design.learn.thread.future;

import com.design.learn.thread.future.impl.RealData;
import com.design.learn.thread.future.service.Data;
import com.design.learn.thread.future.impl.Future;

public class Client {

    public Data request(final String quaryStr) {
        final Future future = new Future();
        Thread thread = new Thread() {
            @Override
            public void run() {
                RealData realData = new RealData(quaryStr);
                future.setRealData(realData);
            }
        };
        thread.setName("Client--request--Thread");
        thread.start();
        return future;
    }
}
