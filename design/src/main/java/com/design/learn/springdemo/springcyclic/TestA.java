package com.design.learn.springdemo.springcyclic;


import org.springframework.stereotype.Component;


public class TestA {

    private TestB testB;

    TestA(TestB testB){
        this.testB = testB;
    }

    public TestB getTestB() {
        return testB;
    }

    public void setTestB(TestB testB) {
        this.testB = testB;
    }
}
