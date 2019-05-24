/**
 * Jdk动态代理只能代理到调用的那个接口方法，不能代理到里面嵌套的方法
 * spring aop动态代理的坑，嵌套事物会出问题（在一个写方法里面for循环写入时，
 * 假设int i = 5时报错，需要回滚所有数据时，就需要把 proxy-targe-class 打开）
 */
package com.design.learn.proxy.springproxy;