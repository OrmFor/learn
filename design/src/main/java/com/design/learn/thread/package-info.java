/**
* @Author wwy
* @Description ★★★★★强制规范：使用线程时务必要给它附上线程名，方便线上排查
 *
 *             当线程池调用该方法时,线程池的状态则立刻变成SHUTDOWN状态。
 *             此时，则不能再往线程池中添加任何任务，否则将会抛出RejectedExecutionException异常。
 *             但是，此时线程池不会立刻退出，直到添加到线程池中的任务都已经处理完成，才会退出。
 *
 *
 *
 *             注意：阿里强制规范 需要自定义 ThreadPool
 *             原因： newFixedThreadPool 和 newSignleThreadExecutor 允许创建Integer.MAX_VALUE长度的阻塞队列
 *                   newCachedThreadPool 和 newScheduledThreadPool 允许创建Integer.MAX_VALUE个线程
 *                   所以前者任务堆积会导致OOM
 *                   后者线程创建过多会导致OOM
**/
package com.design.learn.thread;