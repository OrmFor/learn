package com.design.learn.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLIBProxy {



    public static Object createProxy(final Object targetObj, final CustomAspect customAspect) {
        Enhancer enhancer = new Enhancer();
        // 设置需要代理的父类
        enhancer.setSuperclass(targetObj.getClass());
        // 设置回调
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
                    throws Throwable {
                // 执行切面方法
                customAspect.startAspect();
                // 具体逻辑代码执行,返回值为方法执行结果
                Object result = methodProxy.invokeSuper(proxy, args);
                // 执行切面方法
                customAspect.endAspect();
                // 返回方法执行结果
                return result;
            }
        });
        // 3.4 创建代理
        return enhancer.create();
    }
}
