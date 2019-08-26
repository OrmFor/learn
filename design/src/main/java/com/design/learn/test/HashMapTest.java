package com.design.learn.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapTest {

    public static void main(String[] args){
        Test1 test1 = new Test1();
        Test1 test2 = new Test1();
        Thread t = new Thread(test1);
        Thread t1 = new Thread(test2);

        t.start();
        t1.start();

    }


}

class Test1 implements Runnable{

    private HashMap hashMap = new HashMap();

    @Override
    public void run() {

        for(int i = 0 ; i < 100 ; i++){
            Thread.currentThread().setName("第"+i+"个");
            hashMap.put(Thread.currentThread().getName(),i);
        }

        //
        Iterator iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry =(Map.Entry) iterator.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("key="+key+"  value"+value);
        }




        hashMap.forEach((kk,vv)->{
             System.out.println(kk+":"+vv);
        });
    }
}
