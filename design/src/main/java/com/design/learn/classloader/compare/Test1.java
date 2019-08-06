package com.design.learn.classloader.compare;



/**
* @Author wwy
* @Description loadClass() 和 Class.forName区别
 *             前者不初始化
 *             后者调用的是forName0(className, true, ClassLoader.getClassLoader(caller), caller);默认初始化
* @Date 13:34 2019/7/8
* @Param
* @return
**/
public class Test1 {


    @org.junit.Test
    public void test() throws ClassNotFoundException, NoSuchMethodException {
        ClassLoader.getSystemClassLoader().loadClass("com.design.learn.classloader.compare.ClassLoaderTest");
        System.out.println("#########分割符(上面是Class.forName的加载过程，下面是ClassLoader的加载过程)##########");
        Class.forName("com.design.learn.classloader.compare.ClassLoaderTest");

    }


}
