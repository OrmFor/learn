package com.design.learn.thread.callable;

import java.util.concurrent.*;


/**
* @Author wwy
* @Description CallAble和Future 一起使用
* @Date 9:57 2019/8/9
* @Param
* @return
**/
public class TestCallable {

    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CallableTask task1 = new CallableTask("1");
        CallableTask task2 = new CallableTask("2");
        CallableTask task3 = new CallableTask("3");

        Future<Integer> reslut1 = executor.submit(task1);
        Future<Integer> reslut2 = executor.submit(task2);
        Future<Integer> reslut3 = executor.submit(task3);

        executor.shutdown();

        try {
            System.out.println(reslut1.get(100,TimeUnit.MILLISECONDS));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


        try {
            System.out.println(reslut2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        try {
            System.out.println(reslut3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
