/**
* @Author wwy
* @Description Callable接口能够有返回值
 *             实现一个线程 1. new Thread(task).start()
 *                        2. 实现 Runnable (重写run(),无返回值)
 *                        3. 实现 Callable (重写call(),有返回值)
 *                        如果返回过慢可以使用接口Future.get(100,TimeUnit.MILLISECONDS)  //等待100毫秒
 *
**/
package com.design.learn.thread.callable;