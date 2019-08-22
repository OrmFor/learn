/**
* @Author wwy
* @Description ★★★★★强制规范：使用线程时务必要给它附上线程名，方便线上排查
 *
 *             shutdown() :
 *             当线程池调用该方法时,线程池的状态则立刻变成SHUTDOWN状态。
 *             此时，则不能再往线程池中添加任何任务，否则将会抛出RejectedExecutionException异常。
 *             但是，此时线程池不会立刻退出，直到添加到线程池中的任务都已经处理完成，才会退出。
 *
 *             shutdownnow()
 *             阻止新来的任务提交，同时会中断当前正在运行的线程，即workers中的线程。
 *             另外它还将workQueue中的任务给移除，并将这些任务添加到列表中进行返回。
 *
 *
 *
 *             注意：阿里强制规范 需要自定义 ThreadPool
 *             原因： newFixedThreadPool 和 newSignleThreadExecutor 允许创建Integer.MAX_VALUE长度的阻塞队列
 *                   newCachedThreadPool 和 newScheduledThreadPool 允许创建Integer.MAX_VALUE个线程
 *                   所以前者任务堆积会导致OOM
 *                   后者线程创建过多会导致OOM
 *
 *
 * 线程池异常策略:
 * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
 * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
 * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
 * ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
**/
package com.design.learn.thread;