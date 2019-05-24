package com.design.learn.thread.aba;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicMarkableReferenceTest {

    static AtomicMarkableReference<Integer> atomicMarkableReference = new AtomicMarkableReference<Integer>(0,true);
    public static void main(String[] args) throws InterruptedException {


        final int  reference = atomicMarkableReference.getReference();


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(reference + "-" + reference + "-"
                        + atomicMarkableReference.compareAndSet(reference, reference + 10, true, false));
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Integer reference = atomicMarkableReference.getReference();
                System.out.println(reference + "-" + reference + "-"
                        + atomicMarkableReference.compareAndSet(reference, reference + 10, true, false));
            }
        });
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        System.out.println(atomicMarkableReference.getReference());
        System.out.println(atomicMarkableReference.isMarked());
    }
}
