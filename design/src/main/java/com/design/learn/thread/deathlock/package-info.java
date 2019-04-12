/**
* @Author wwy
* @Description  死锁  4个条件：
 *                    1.互斥条件
 *                    2.请求和保持条件
 *                    3.不可剥夺条件
 *                    4.环路等待条件
 *                    破坏其中一个就可以破坏死锁
 *
 *               可使用jvisualvm 检测死锁  或者在tomcat启动参数中增加
 *                 -XX:+HeapDumpOnOutOfMemoryError
 *                 -XX:HeapDumpPath=F:\apache-tomcat-7.0\apache-tomcat-7.0.84\bin\logs\heapdump.hprof
 *
* @Date 10:52 2019/4/12
**/
package com.design.learn.thread.deathlock;