/**
* @Author wwy
* @Description 可重入锁  synchronized  非公平锁
 *                      ReentrantLock （true） 是公平锁 ReentrantLock可以加入多个condition
 *
 *                      非公平锁的优点在于吞吐量比公平锁大。对于Synchronized而言，也是一种非公平锁

 *
 * Synchronized：非公平，悲观，独享，互斥，可重入的重量级锁
 * ReentrantLock：默认非公平但可实现公平的(构造器传true)，
 *                 悲观，独享，互斥，可重入，重量级锁。
 * ReentrantReadWriteLocK：默认非公平但可实现公平的(构造器传true)，
 *               悲观，写独享，读共享，读写，可重入，重量级锁。
 *
**/
package com.design.learn.thread.lock.Reentrant;