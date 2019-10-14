package com.design.learn.springdemo.aopdemo;

/**
* @Author wwy
* @Description  此类是业务中的核心类，需要用aop拦截
* @Date 17:14 2019/9/1
* @Param
* @return
**/
public class TestBean {

    private String testStr = "testStr";

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }


    public void test(){
        System.out.println("test");
    }
}
