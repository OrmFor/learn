/**
* @Author wwy
* @Description  用redis实现秒杀
 *              知识点 redis watch 监听商品   对应 unWatch
 *                    CyclicBarrier 类似发令枪（CountDownLatch）但比它好用,
 *                    在给定线程数之后，调用await(),等到所有线程到达后同时并发执行
 *
 *              doc为需求文档  redisseckill为代码实现
**/
package com.design.learn.seckill;