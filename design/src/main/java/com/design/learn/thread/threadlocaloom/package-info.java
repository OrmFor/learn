/**
 * 出现原因：   ThreadLocal  底层实现  ThreadLocalMap extends WeakReference (弱引用)
 *             ThreadLocalMap使用ThreadLocal的弱引用作为key，
 *             如果一个ThreadLocal没有外部强引用来引用它，那么系统 GC 的时候，
 *             这个ThreadLocal势必会被回收，这样一来，ThreadLocalMap中就会出现key为null的Entry，
 *             就没有办法访问这些key为null的Entry的value，如果当前线程再迟迟不结束的话，
 *             这些key为null的Entry的value就会一直存在一条强引用链：
 *             Thread Ref -> Thread -> ThreaLocalMap -> Entry -> value永远无法回收，造成内存泄漏。
 *
 */
package com.design.learn.thread.threadlocaloom;