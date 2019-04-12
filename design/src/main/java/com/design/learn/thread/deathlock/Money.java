package com.design.learn.thread.deathlock;

public class Money implements Runnable {
    Object goods;
    Object money;

    public Money(Object goods, Object money) {
        this.goods = goods;
        this.money = money;
    }


    public void testMoney() {
        synchronized (money) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            synchronized (goods) {

            }
        }
        System.out.println("一手交货");
    }


    @Override
    public void run() {
        while (true)
            testMoney();
    }
}
