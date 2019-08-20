package com.design.learn.thread.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicMarkableReference;

import static com.design.learn.thread.threadpool.ThreadSizeConstant.MAX_SIZE;
import static com.design.learn.thread.threadpool.ThreadSizeConstant.THREAD_LOOP_SIZE;

/**
 * 默认线程数量 Integer.MAX_VALUE,
 *
 * ThreadPoolExecutor  5个参数的意义  ：
 *                               int corePoolSize, 核心线程数
 *                               int maximumPoolSize, 最大线程数
 *                               long keepAliveTime, 空闲线程最大可存活时间
 *                               TimeUnit unit, 单位
 *                               BlockingQueue<Runnable> workQueue 阻塞队列
 *                               ThreadFactory 线程工厂
 *                               RejectedExecutionHandler handler 任务拒绝处理器，用于降级处理
 *
 */
public class NewCachedThreadPoolDemo {

    public static void main(String[] args) {
        BlockingQueue queue = new ArrayBlockingQueue<String>(5);

        ExecutorService executor = new ThreadPoolExecutor(MAX_SIZE, MAX_SIZE,
                0L, TimeUnit.MILLISECONDS, queue
        );


        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < THREAD_LOOP_SIZE; i++) {
            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
        executorService.shutdown();

    }
}
