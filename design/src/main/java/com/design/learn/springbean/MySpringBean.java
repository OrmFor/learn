package com.design.learn.springbean;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MySpringBean
 * @Description: my spring bean to test
 * @author: daniel.zhao
 * @date: 2018年10月26日 上午10:12:37
 */
//@Component("MySpringBean")
public class MySpringBean implements BeanNameAware, BeanFactoryAware,ApplicationContextAware, InitializingBean, BeanClassLoaderAware {

    private ApplicationContext applicationContext;

    private static final Logger logger = LogManager.getLogger(MySpringBean.class);

    public MySpringBean() {
        logger.info("new MySpringBean......");
    }

    @Override
    public void setBeanName(String name) {
        logger.info("BeanNameAware-setBeanName......");
    }

    @Override
    public void setBeanFactory(BeanFactory bf) throws BeansException {
        logger.info("BeanFactoryAware-setBeanFactory......");
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        logger.info("ApplicationContextAware-setApplicationContext......");
        this.applicationContext = context;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("InitializingBean-afterPropertiesSet......");
    }



    public void init() {
        logger.info("init-method......");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        logger.info("BeanClassLoaderAware-setBeanClassLoader......");
    }
}
