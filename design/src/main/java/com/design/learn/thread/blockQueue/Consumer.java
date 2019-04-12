package com.design.learn.thread.blockQueue;

import java.text.MessageFormat;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

    private final BlockingQueue<Integer> consumerQueue;

    public Consumer(BlockingQueue<Integer> consumerQueue){
        this.consumerQueue = consumerQueue;
    }

    @Override
    public void run() {
        for(int i = 0 ; i < 10 ; i++ ){
            try {
                Integer take = consumerQueue.take();//阻塞至有值时取值
               /* Integer poll = consumerQueue.poll();//不会阻塞
                if(take == null){
                    continue;
                }*/
                System.out.println(MessageFormat.format("[当前执行线程{0}]:从队列中取出数字：{1}",Thread.currentThread().getName(),take));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
