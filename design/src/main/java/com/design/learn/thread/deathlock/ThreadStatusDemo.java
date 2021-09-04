package com.design.learn.thread.deathlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class ThreadStatusDemo {

    public static void main(String[] args){
        new Thread(()->{
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"sleep_thread").start();

        new Thread(()->{
            while (true){
                synchronized (ThreadStatusDemo.class) {
                    try {
                        ThreadStatusDemo.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        },"wait_thread").start();


        new Thread(new BlockDemo(),"block1").start();
        new Thread(new BlockDemo(),"block2").start();
    }

    static class BlockDemo extends Thread{

        @Override
        public void run() {
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
