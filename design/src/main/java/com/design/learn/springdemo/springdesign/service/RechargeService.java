package com.design.learn.springdemo.springdesign.service;

import java.math.BigDecimal;

/**
* @Author wwy
* @Description 业务组件接口
* @Date 9:05 2019/4/5
* @Param
* @return
**/
public interface RechargeService {

     String getOrderNo();

     String[] getProductType();

     String processOnRechargeAliPayPre(String type, BigDecimal money);

}
