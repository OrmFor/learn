/**
 * spring aop动态代理的坑，有时候事物会出问题（在一个写方法里面for循环写入时，
 * 假设int i = 5时报错，需要回滚所有数据时，就需要把 proxy-targe-class 打开）
 */
package com.design.learn.proxy.springproxy;