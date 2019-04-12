package com.design.learn.thread.deathlock;

public class Goods implements Runnable {
    Object goods;
    Object money;

    public Goods(Object goods, Object money) {
        this.goods = goods;
        this.money = money;
    }


    public void testGoods() {
        synchronized (goods) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            synchronized (money) {

            }
        }
        System.out.println("一手交钱");
    }


    @Override
    public void run() {
        while (true)
            testGoods();
    }
}
