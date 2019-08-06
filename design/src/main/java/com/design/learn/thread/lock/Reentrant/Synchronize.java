package com.design.learn.thread.lock.Reentrant;

public class Synchronize implements Runnable{

    public synchronized void get(){
        System.out.println(Thread.currentThread().getName());
        set();
    }

    public synchronized void set(){
        System.out.println(Thread.currentThread().getName());
    }



    public static void main(String[] args){
        Synchronize synchronize = new Synchronize();

        Thread t1 = new Thread(synchronize);
        t1.start();

        Thread t2 = new Thread(synchronize);
        t2.start();

        Thread t3 = new Thread(synchronize);
        t3.start();

        Thread t4 = new Thread(synchronize);
        t4.start();
    }

    @Override
    public void run() {
        get();
    }
}
