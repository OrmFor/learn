package com.design.learn.proxy.springproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class DynamicCGLIBProxy implements MethodInterceptor {

    private static final String METHOD_PREFIX = "test";

    private Object target;

    public DynamicCGLIBProxy(Object target) {
        this.target = target;
    }

    /**
     * intercept方法参数：
     * @param Object obj：表示要进行增强的对象
     * @param Method method：表示拦截的方法
     * @param Object[] args：基本数据类型需要传入其包装类型
     * @param MethodProxy proxy：表示对方法的代理
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if(method.getName().startsWith(METHOD_PREFIX)){
            System.out.println("==========代理===========");
        }
        return methodProxy.invokeSuper(obj,args);
    }

    public Object getCGLIBProxy(){
        Enhancer enhancer = new Enhancer();
        // 设置需要代理的父类
        enhancer.setSuperclass(this.target.getClass());
        // 设置回调
        enhancer.setCallback(this);
        // 3.4 创建代理
        return enhancer.create();
    }
}
