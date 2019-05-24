/**
* @Author wwy
* @Description  用redis实现秒杀
 *              知识点 redis watch 监听商品   对应 unWatch
 *                    CyclicBarrier 类似发令枪（CountDownLatch）但比它好用,
 *                    在给定线程数之后，调用await(),等到所有线程到达后同时并发执行
 *
 *
 *                    CountDownLatch 的使用点是在主线程那边(线程先各自执行自己的逻辑，到主线程的时候await)，不能复用一次性的
 *                    CyclicBarrier 的使用点是在各个子线程中await(线程先互相等待 在run中await，然后在等到所有线程到达后，再执行剩下的逻辑)，能给复用
 *              doc为需求文档  redisseckill为代码实现
**/
package com.design.learn.seckill;