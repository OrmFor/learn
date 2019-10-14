package com.design.learn.springdemo.factorybeandemo;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.FactoryBean;

import java.math.BigDecimal;


/**
* @Author wwy
* @Description FactoryBean 接口的使用
 *             要实现3个方法   getObject   getObjectType  isSingleton 方法
* @Date 10:11 2019/9/1
* @Param
* @return
**/
public class SpringFactoryBeanDemo implements FactoryBean<Car> {

    private String carInfo;

    @Override
    public Car getObject() throws Exception {
        String[] infos = carInfo.split(",");
        Car car = new Car();
        car.setBrand(infos[0]);
        car.setMaxSpeed(Integer.valueOf(infos[1]));
        car.setPrice(new BigDecimal(infos[2]));
        System.out.println(car.toString());
        System.out.println("getObject===============");
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }
}



