package com.design.learn.thread.blockQueue;

import java.text.MessageFormat;
import java.util.concurrent.BlockingQueue;

public class Provider implements Runnable {

    private final BlockingQueue<Integer> proQueue;


    public Provider(BlockingQueue<Integer> proQueue){
        this.proQueue = proQueue;
    }


    @Override
    public void run() {

        for(int i = 0 ; i < 10 ; i++){
            try {
                proQueue.put(i);
                System.out.println(MessageFormat.format("[当前执行线程{0}]:往队列中增加数字：{1}",Thread.currentThread().getName(),i));

                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
