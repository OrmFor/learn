/**
 *  在resource下又配置文件，当发生oom时可以保存dump
 *
 *  线程dump 步骤
 *    windows 下 jps  -v  找到 PID
 *    Linux 下 ps -ef|grep java 找到 PID
 *    jstack PID >> XXX.dump
 *
 *  堆内存dump 步骤
 *     找到PID一样
 *
 *     jmap -dump:format=b,file=xxx.hprof PID
 *
 *
 *
 *
 *
 */
package com.design.learn.oom;