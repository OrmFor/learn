/**
 * Spring bean 生命周期
 *
 * 初始化：
 * 2019-05-22 13:59:58.225 [main] INFO  com.design.learn.springbean.MySpringBean 25 - new MySpringBean......
 * 2019-05-22 13:59:58.228 [main] INFO  com.design.learn.springbean.MySpringBean 30 - BeanNameAware-setBeanName......
 * 2019-05-22 13:59:58.228 [main] INFO  com.design.learn.springbean.MySpringBean 57 - BeanClassLoaderAware-setBeanClassLoader......
 * 2019-05-22 13:59:58.228 [main] INFO  com.design.learn.springbean.MySpringBean 35 - BeanFactoryAware-setBeanFactory......
 * 2019-05-22 13:59:58.228 [main] INFO  com.design.learn.springbean.MySpringBean 40 - ApplicationContextAware-setApplicationContext......
 * 2019-05-22 13:59:58.228 [main] INFO  com.design.learn.springbean.MySpringBeanPostProcessor 30 - BeanPostProcessor-postProcessBeforeInitialization......
 * 2019-05-22 13:59:58.229 [main] INFO  com.design.learn.springbean.MySpringBean 46 - InitializingBean-afterPropertiesSet......
 * 2019-05-22 13:59:58.229 [main] INFO  com.design.learn.springbean.MySpringBean 52 - init-method......
 * 2019-05-22 13:59:58.229 [main] INFO  com.design.learn.springbean.MySpringBeanPostProcessor 22 - BeanPostProcessor-postProcessAfterInitialization......
 *
 *
 *  1.调用BeanNameAware接口的setBeanName
 *  2.调用BeanClassLoaderAware接口的setBeanClassLoader
 *  3.调用BeanFactoryAware接口的setBeanFactory
 *  4.调用ApplicationContextAware接口的setApplicationContext
 *  5.调用BeanPostProcessor接口的postProcessBeforeInitialization
 *  6.调用InitializingBean接口的afterPropertiesSet
 *  7.调用inti 方法
 *  8.调用BeanPostProcessor接口的postProcessAfterInitialization
 *
 *
 *  实例化bean对象(通过构造方法或者工厂方法)
 *  设置对象属性(setter等)（依赖注入）
 *  如果Bean实现了BeanNameAware接口，工厂调用Bean的setBeanName()方法传递Bean的ID。（和下面的一条均属于检查Aware接口）
 *  如果Bean实现了BeanFactoryAware接口，工厂调用setBeanFactory()方法传入工厂自身
 *  如果Bean实现了ApplicationContextAware接口,将调用setApplicationContext()方法传入上下文本身
 *  将Bean实例传递给Bean的前置处理器的postProcessBeforeInitialization(Object bean, String beanname)方法
 *  调用Bean的初始化方法
 *  将Bean实例传递给Bean的后置处理器的postProcessAfterInitialization(Object bean, String beanname)方法
 *  使用Bean
 *  容器关闭之前，调用Bean的销毁方法
 *
 *
 *  循环注入问题
 *
 *  createBeanInstance 创建bean
 *  populateBean 填充属性
 *
 *  3级缓存
 *  singletonObjects
 *  earlySingletonObjects
 *  singletonFactories
 *
 *  A初始化，发现有个属性是class B，就去找对应B实例，首先从一级缓存singletonObjects中找，未找到，再去earlySingletonObjects找，
 *  也找不到，最后会再sigletonFactories，此时bean是个原始对象，所有属性都为空，但是有引用，
 *
 *
 */
package com.design.learn.springdemo.springbean;