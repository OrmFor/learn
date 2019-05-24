package com.design.learn.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建单个线程的线程池
 */
public class NewSingleThreadExecutorDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName());
        });

        executorService.shutdown();
    }
}
