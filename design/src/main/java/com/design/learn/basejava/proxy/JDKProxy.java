package com.design.learn.basejava.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy {

    public static Object createProxy(final Object targetObj, final CustomAspect customAspect) {
        // 使用JDK的Proxy类为目标类创建代理对象
        return Proxy.newProxyInstance(
                // 目标类使用的类加载器
                targetObj.getClass().getClassLoader(),
                // 目标类实现的接口
                targetObj.getClass().getInterfaces(),
                // 执行处理器，代理我们的业务逻辑
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 执行切面方法
                        customAspect.startAspect();
                        // 具体逻辑代码执行,返回值为方法执行结果
                        Object result = method.invoke(targetObj, args);
                        // 执行切面方法
                        customAspect.endAspect();
                        // 返回方法执行结果
                        return result;
                    }
                });
    }
}
