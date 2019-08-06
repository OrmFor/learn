package com.design.learn.thread.lock.Reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class Reentrant implements Runnable{
    ReentrantLock lock = new ReentrantLock();

    public void get(){
        lock.lock();
        System.out.println(Thread.currentThread().getName());
        set();
        lock.unlock();
    }

    public void set(){
        lock.lock();
        System.out.println(Thread.currentThread().getName());
        lock.unlock();
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args){
       Reentrant t = new Reentrant();
        Thread t1 = new Thread(t);
        t1.start();
        Thread t2 = new Thread(t);
        t2.start();

        Thread t3 = new Thread(t);
        t3.start();

        Thread t4 = new Thread(t);
        t4.start();

    }
}
