/**
* @Author wwy
* @Description  使用阻塞队列，使并发编程来的更加容易上手
 *              在使用BlockQueue的时候要用block特性的方法，不要使用queue的方法
 *
 *              例如：以下接口继承自queue
 *              add(E) : boolean
 *              offer() : boolean
 *              remove() : E
 *              poll() : E
 *              element() : E
 *              peek() : E
 *
 *              尽量使用继承自BlockQueue的方法(有阻塞功能)
 *              put(E)
 *              take() : E
 *              offer(E, long, TimeUnit) : boolean
 *              poll(long, TimeUnit) : E
 *              remainingCapacity()
 *              drainTo(Collection<? super E>) : int
 *              drainTo(Collection<? super E>, int) : int
 *
 *              使用正确例子：
 *              BlockingQueue blockqueue = new BlockingQueue();
 *              blockqueue.put(i);//该方法是往blockQueue里面添加数据
 *              blockqueue.take(i);//
 *              如果使用poll会出现错误，如下所示
 *              	[当前执行线程consumer]:从队列中取出数字：null
 * 					[当前执行线程consumer]:从队列中取出数字：0
 * 					[当前执行线程provider]:往队列中增加数字：0
 * 					[当前执行线程consumer]:从队列中取出数字：null
 * 					[当前执行线程consumer]:从队列中取出数字：null
 * 					[当前执行线程consumer]:从队列中取出数字：null
 * 					[当前执行线程consumer]:从队列中取出数字：null
 * 					[当前执行线程consumer]:从队列中取出数字：null
 * 					[当前执行线程consumer]:从队列中取出数字：null
 * 					[当前执行线程consumer]:从队列中取出数字：null
 * 					[当前执行线程consumer]:从队列中取出数字：null
 * 					[当前执行线程provider]:往队列中增加数字：1
 * 					[当前执行线程provider]:往队列中增加数字：2
 * 					[当前执行线程provider]:往队列中增加数字：3
 * 					[当前执行线程provider]:往队列中增加数字：4
 * 					[当前执行线程provider]:往队列中增加数字：5
 * 					[当前执行线程provider]:往队列中增加数字：6
 * 					[当前执行线程provider]:往队列中增加数字：7
 * 					[当前执行线程provider]:往队列中增加数字：8
 * 					[当前执行线程provider]:往队列中增加数字：9
 *
**/
package com.design.learn.thread.blockqueue;