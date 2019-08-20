package com.design.learn.oom;
//-Xss10M -Xmx10M -XX:+PrintGC
public class StackOOM {

    int i = 0;

    public void test() {
       test();
    }

    public static void main(String[] args) {
        StackOOM s = new StackOOM();
        s.test();
    }
}
