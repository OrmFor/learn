package com.design.learn.thread.aba;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceTest {
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference(0, 0);

    public static void main(String[] args) throws InterruptedException {
        final int stamp = atomicStampedReference.getStamp();
        final Integer reference = atomicStampedReference.getReference();
        System.out.println(reference + "============" + stamp);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(reference + "-" + stamp + "-"
                        + atomicStampedReference.compareAndSet(reference, reference + 10, stamp, stamp + 1));
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Integer reference = atomicStampedReference.getReference();
                System.out.println(reference + "-" + stamp + "-"
                        + atomicStampedReference.compareAndSet(reference, reference + 10, stamp, stamp + 1));
            }
        });
        t1.start();
        t1.join();
        t2.start();
        t2.join();

        System.out.println(atomicStampedReference.getReference());
        System.out.println(atomicStampedReference.getStamp());
    }
}
