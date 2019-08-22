package com.design.learn.oom;

import java.util.ArrayList;

//-Xms10M -Xmx10M -Xmn10M -XX:+PrintGC -XX:+HeapDumpOnOutOfMemoryError 打印日志
public class HeapOOM {

    public void newObject() {
        ArrayList arrayList = new ArrayList(2000);
        while (true) {
            arrayList.add(new HeapOOM());
        }
    }

    public static void main(String[] args) {

        HeapOOM t = new HeapOOM();
        t.newObject();

    }

}
