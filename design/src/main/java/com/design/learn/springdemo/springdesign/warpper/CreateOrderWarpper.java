package com.design.learn.springdemo.springdesign.warpper;

import java.math.BigDecimal;

/**
* @Author wwy
* @Description 创建订单入口  对外提供接口包装类
* @Date 15:15 2019/4/4
* @Param
* @return
**/
public interface CreateOrderWarpper {

    String preOrderCreate(String type, BigDecimal money);

    boolean chcekIsOnLine();
}
