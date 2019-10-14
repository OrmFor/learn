package com.design.learn.springdemo.springcyclic;

import org.springframework.stereotype.Component;

@Component
public class TestC {

    private TestA testA;

    TestC(TestA testA){
        this.testA = testA;
    }

    public TestA getTestA() {
        return testA;
    }

    public void setTestA(TestA testA) {
        this.testA = testA;
    }
}
