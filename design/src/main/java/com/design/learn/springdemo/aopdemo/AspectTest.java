package com.design.learn.springdemo.aopdemo;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AspectTest {

    @Pointcut("execution(* *.test(..))")
    public void test(){

    }


    @Before("test()")
    public void beforeTest(){
        System.out.println("beforeTest");
    }


    @After("test()")
    public void afterTest(){
        System.out.println("afterTest");
    }


    @Around("test()*")
    public Object arountTest(ProceedingJoinPoint proceedingJoinPoint){

        System.out.println("before1");
        Object o = null;

        try{
            o = proceedingJoinPoint.proceed();
        }catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println("after1");
        return o;

    }
}
