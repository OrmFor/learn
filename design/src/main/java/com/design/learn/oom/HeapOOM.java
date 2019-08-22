package com.design.learn.oom;

import java.util.ArrayList;

//-Xms10M -Xmx10M -Xmn10M -XX:+PrintGC -XX:+HeapDumpOnOutOfMemoryError 打印日志 -XX:+PrintGCDetails   -XX:+PrintGCDateStamps  -XX:+PrintGCTimeStamps
//----------使用串行GC---------------------------------//
// -XX:+UseSerialGC
//----------新生代和老年代(元空间Metaspace)都使用并行GC---------------------------------//
// -XX:+UseParallelGC
// -XX:+UseParallelOldGC
//----------新生代并行GC(Parallel GC) + 老年代(元空间Metaspace)CMS---------------------------------//
// -XX:+UseParNewGC
// -XX:+UseConcMarkSweepGC
//----------使用G1---------------------------------//
//-XX:+UseG1GC
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
