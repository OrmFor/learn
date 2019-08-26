package com.design.learn.ddos;

import com.design.learn.ddos.Client;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DdosTest {

    public static void main(String[] args) {
        //利用线程池创建1000个线程
      //  ExecutorService es = Executors.newFixedThreadPool(1);
        Client ddos = new Client();
        Thread thread = new Thread(ddos);
        thread.start();
        /*for (int i = 0; i < 1000; i++) {
            es.execute(thread);
        }*/
    }
}

