package com.design.learn.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.design.learn.thread.threadpool.ThreadSizeConstant.MAX_SIZE;
import static com.design.learn.thread.threadpool.ThreadSizeConstant.THREAD_LOOP_SIZE;

/**
 * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 */
public class NewFixedThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_SIZE);
        for(int i = 0 ; i < THREAD_LOOP_SIZE ; i++) {
            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println(111);
        executorService.shutdown();


    }


  
}