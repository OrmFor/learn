package com.design.learn.java.util.test;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

    public static void main(String[] args){
        Test test1 = new Test();
        Test test2 = new Test();
        Thread t = new Thread(test1);
        Thread t1 = new Thread(test2);

        t.start();
        t1.start();

    }


}

class Test implements Runnable{

    private ConcurrentHashMap<String,Object> concurrentHashMap = new ConcurrentHashMap<String,Object>();

    @Override
    public void run() {

       for(int i = 0 ; i < 100 ; i++){
           Thread.currentThread().setName("第"+i+"个");
           concurrentHashMap.put(Thread.currentThread().getName(),i);
       }

        for (String key : concurrentHashMap.keySet()){
            Object value = concurrentHashMap.get(key);
            System.out.println("key="+key+"  value"+value);


        }
    }
}
