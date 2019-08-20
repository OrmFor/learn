package com.design.learn.basejava.singleton;


import java.io.Serializable;

/**
* @Author wwy
* @Description 懒汉式单例
 *             双层校验的时候需要加上colatile 否则会出问题
* @Date 16:47 2019/7/12
* @Param
* @return
**/
public class SingletonDemo implements Serializable {

    private static volatile SingletonDemo demo = null;

    private SingletonDemo(){

    }

    public static SingletonDemo getInstance(){
        if(demo == null){
            synchronized (SingletonDemo.class){
                if(demo == null){
                    demo = new SingletonDemo();

                }
            }
        }
        return demo;
    }
}
