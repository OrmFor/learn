package com.design.learn.proxy.springproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicJDKProxy implements InvocationHandler {
    private static final String METHOD_PREFIX = "test";

    private Object target;

    public DynamicJDKProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().startsWith(METHOD_PREFIX)){
            System.out.println("=========代理==========");
        }
        return method.invoke(target,args);
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(),this);
    }


}
