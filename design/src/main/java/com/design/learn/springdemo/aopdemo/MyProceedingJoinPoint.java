package com.design.learn.springdemo.aopdemo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.weaver.reflect.JoinPointMatchImpl;
import org.aspectj.weaver.tools.PointcutParameter;
import org.springframework.aop.ProxyMethodInvocation;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;

public class MyProceedingJoinPoint extends MethodInvocationProceedingJoinPoint {


    public MyProceedingJoinPoint(ProxyMethodInvocation methodInvocation) {
        super(methodInvocation);
    }

    @Override
    public Object proceed() throws Throwable {
        System.out.println("ttttttttttttt");
        return   super.proceed();
    }

}
