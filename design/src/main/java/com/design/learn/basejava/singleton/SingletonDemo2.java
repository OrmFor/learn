package com.design.learn.basejava.singleton;

/**
* @Author wwy
* @Description 饿汉式单例  加载时就能
* @Date 16:50 2019/7/12
* @Param
* @return
**/
public class SingletonDemo2 {
    private  static SingletonDemo2 singletonDemo2 = new SingletonDemo2();

    private SingletonDemo2(){

    }

    public static SingletonDemo2 getInstance(){
        return singletonDemo2;
    }
}
