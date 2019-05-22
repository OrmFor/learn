package com.design.learn.springbean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MySpringBeanPostProcessor
 * @author: daniel.zhao
 * @date: 2018年10月26日 上午10:40:21
 */
@Component
public class MySpringBeanPostProcessor implements BeanPostProcessor {

    private static final Logger logger = LogManager.getLogger(MySpringBeanPostProcessor.class);

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MySpringBean) {
            logger.info("BeanPostProcessor-postProcessAfterInitialization......");
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MySpringBean) {
            logger.info("BeanPostProcessor-postProcessBeforeInitialization......");
        }
        return bean;
    }

}
