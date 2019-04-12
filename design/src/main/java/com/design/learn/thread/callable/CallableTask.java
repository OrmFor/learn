package com.design.learn.thread.callable;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<Integer> {
    private String name;

    public CallableTask(String name) {
        this.name = name;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        Thread.sleep(3000);
        for (int i = 0; i < 100; i++) {
            sum += i;
        }

        return sum;
    }
}
