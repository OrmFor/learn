package com.design.learn.thread.future;

import com.design.learn.thread.future.service.Data;

public class Test {
    public static void main(String[] args){
        Client client = new Client();
        long l = System.currentTimeMillis();
        System.out.println(l);
        Data data = client.request("test");
        System.out.println("end");

        /*try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        System.out.println(data.getResult());
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);
    }
}
