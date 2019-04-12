package com.design.learn.ddos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DdosTest {

    public static void main(String[] args) {
        //利用线程池创建1000个线程
        ExecutorService es = Executors.newFixedThreadPool(1000);
        DdosThread ddos = new DdosThread();
        Thread thread = new Thread(ddos);
        for (int i = 0; i < 10000; i++) {
            es.execute(thread);
        }
    }
}

