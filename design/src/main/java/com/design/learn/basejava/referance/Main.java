package com.design.learn.basejava.referance;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;


/**
 * -Xms40m -Xmx40m -Xmn20m -XX:+PrintGCDetails
 */
public class Main {

    // 引用测试类
    static class ReferenceTest {
        static final int _1M = 1024;
        // 引用队列，当某个引用所指向的对象被回收时这个引用本身会被添加到其对应的引用队列中
        // 其泛型为其中存放的引用要指向的对象类型
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();

        // 强引用测试
        void testStrongReference() {
            ArrayList<byte[]> strongReferences = new ArrayList<>();
            try {
                while (true) {
                    strongReferences.add(new byte[_1M]);
                }
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }

        // 软引用测试
        void testSoftReference() {
            ArrayList<SoftReference> softReferences = new ArrayList<>();
            try {
                while (true) {
                    softReferences.add(new SoftReference<>(new byte[_1M], referenceQueue));
                }
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }

        // 弱引用测试
        void testWeakReference() {
            ArrayList<WeakReference> weakReferences = new ArrayList<>();
            try {
                while (true) {
                    weakReferences.add(new WeakReference<>(new byte[_1M], referenceQueue));
                }
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }

        // 虚引用测试
        void testPhantomReference() {
            ArrayList<PhantomReference<byte[]>> phantomReferences = new ArrayList<>();
            try {
                while (true) {
                    phantomReferences.add(new PhantomReference<>(new byte[_1M], referenceQueue));
                }
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ReferenceTest test = new ReferenceTest();
        test.testPhantomReference();
    }
}
