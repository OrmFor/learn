package com.design.learn.ddos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MongoDbTest {

    public static void main(String[] args) {
        //利用线程池创建1000个线程
       ExecutorService es = Executors.newFixedThreadPool(500);
        TestMongoDb ddos = new TestMongoDb();
        Thread thread = new Thread(ddos);
        thread.start();
        for (int i = 0; i < 500; i++) {
            es.execute(thread);
        }

       // es.shutdown();
    }
}

