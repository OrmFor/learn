package com.design.learn.springdemo.springcyclic;

import org.springframework.stereotype.Component;

@Component
public class TestB {

    private TestC testC;

    TestB(TestC testC){
        this.testC = testC;
    }

    public TestC getTestC() {
        return testC;
    }

    public void setTestC(TestC testC) {
        this.testC = testC;
    }
}
