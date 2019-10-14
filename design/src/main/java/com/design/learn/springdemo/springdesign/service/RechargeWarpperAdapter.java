package com.design.learn.springdemo.springdesign.service;

import java.text.MessageFormat;

/**
* @Author wwy
* @Description 核心业务适配器  里面可以添加抽象出来的模板业务
* @Date 9:06 2019/4/5
* @Param
* @return
**/
public abstract class RechargeWarpperAdapter implements RechargeService {

    //
    protected abstract void handleTradeDetailBeforeCreate();

    //初始化生成订单详情
    protected void initOrder(String source, String outTradeNo) {
        handleTradeDetailBeforeCreate();
        System.out.println(MessageFormat.format("initOrderStart,orderNo={0},source={1}",outTradeNo,source));
        System.out.println(".....业务处理中....");
        System.out.println("initOrderEnd");
        handleTradeDetailAfterCreate();

    }


    protected abstract void handleTradeDetailAfterCreate();


}
