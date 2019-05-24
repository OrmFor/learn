package com.design.learn.thread.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 创建定长100 定时3秒的周期性线程池
 */
public class NewScheduledThreadPoolDemo {
    public static void main(String[] args) {

        ScheduledExecutorService executorService3 = Executors.newScheduledThreadPool(100);

        executorService3.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 1 seconds, and excute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);

    }
}
