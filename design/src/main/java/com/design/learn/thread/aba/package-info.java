/**
 * aba 问题  ，一个线程 A-->B  另一个线程把B --> A ,其他线程出错
 *
 * 解决方案 AtomicMarkableReference  和 AtomicStampedReference (版本戳)
 */
package com.design.learn.thread.aba;