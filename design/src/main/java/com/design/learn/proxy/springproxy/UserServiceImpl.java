package com.design.learn.proxy.springproxy;

public class UserServiceImpl implements UserService {
    @Override
    public void test1() {
        System.out.println("--执行test1--");
        test2();
    }

    @Override
    public void test2() {
        System.out.println("--执行test2--");

    }
}
