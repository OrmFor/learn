package com.design.learn.thread.deathlock;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestDeathLock {

    @RequestMapping("/testDeathLock")
    public void testDeathLock(){
        Object goods = new Object();
        Object money = new Object();
        Goods g = new Goods(goods,money);
        Money m = new Money(goods,money);
        new Thread(g).start();
        new Thread(m).start();

    }
}
