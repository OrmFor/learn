package com.design.learn.thread.blockQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestBlockingQueue {

    public static void main(String[] args){
        BlockingQueue queue = new LinkedBlockingQueue();
        Thread provider = new Thread( new Provider(queue),"provider");
        Thread consumer = new Thread( new Consumer(queue),"consumer");
        provider.start();
        consumer.start();
    }
}
