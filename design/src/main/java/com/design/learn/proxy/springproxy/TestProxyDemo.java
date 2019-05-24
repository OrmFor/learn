package com.design.learn.proxy.springproxy;

public class TestProxyDemo {

    public static void main(String[] args) {
        //Jdk动态代理只能代理到调用的那个接口方法，不能代理到里面嵌套的方法
        UserService userService = new UserServiceImpl();
        DynamicJDKProxy dynamicJDKProxy = new DynamicJDKProxy(userService);
        userService = (UserService) dynamicJDKProxy.getProxy();
        userService.test1();
      //  userService.test2();

        UserService userService1 = new UserServiceImpl();

        DynamicCGLIBProxy dynamicCGLIBProxy = new DynamicCGLIBProxy(userService1);
        userService1 = (UserService) dynamicCGLIBProxy.getCGLIBProxy();
        userService1.test1();
    }
}
