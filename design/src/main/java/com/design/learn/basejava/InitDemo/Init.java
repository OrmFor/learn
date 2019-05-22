package com.design.learn.basejava.InitDemo;

import java.text.MessageFormat;
import java.util.LinkedHashMap;

public class Init {
    public static void main(String[] args) {
        Son son = new Son();
       // LinkedHashMap
    }
}



class Parent{
    private static int i =1;
    private static int j ;

    private int k = initk();
    //静态代码块
    static {
        j=1;
        System.out.println("1.静态代码块j值赋值成功");
        System.out.println("1.静态代码块i值："+i);

    }

    int initk(){
        System.out.println("3.成员变量k赋值：k-->"+k);
        this.k = 2;

        System.out.println("3.成员变量k赋值成功：k-->"+k);
        return k;
    }

   // 构造函数
    public Parent(){
        System.out.println(MessageFormat.format("4.父类构造函数执行,i-->{0},j-->{1}",i,j));
        System.out.println(MessageFormat.format("4.父类构造函数执行，k-->{0}",k));
    }

}


class Son extends Parent{
    //静态变量
    private static int si = 1;
    private static int sj;
    //成员变量
    private int sk = initSk();
    private int sKk = initk();

    static {
        sj = 1;
        System.out.println(MessageFormat.format("2.子类静态代码块执行sj-->{0}",sj));
    }

    int initSk(){
        System.out.println("5.子类成员变量赋值：sk--->"+sk);
        this.sk=12;
        System.out.println("5.子类成员变量赋值：sk--->"+sk);

        return sk;
    }

    @Override
    int initk(){
        System.out.println("x.子类成员变量赋值：sKk--->"+sKk);
        this.sKk=12;
        System.out.println("x.子类成员变量赋值：sKk--->"+sKk);

        return sKk;
    }


    public Son(){
        System.out.println(MessageFormat.format("6.父类构造函数执行,si-->{0},sj-->{1},sk-->{2}",si,sj,sk));

    }

}
