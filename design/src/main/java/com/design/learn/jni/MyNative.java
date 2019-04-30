package com.design.learn.jni;

public class MyNative {

    public void testNidAdd( int i, int b) {
        add(i, b);
    }

    private native void add( int i, int b);//定义本地方法

    static {
        System.loadLibrary("MyNative");//静态加载MyNative.dll
    }

    public static void main(String[] args) {
        MyNative obj = new MyNative();
        obj.testNidAdd( 23, 24);//测试本地方法
        obj.testNidAdd( 34, 25);
    }

}
