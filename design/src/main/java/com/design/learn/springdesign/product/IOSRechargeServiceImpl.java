package com.design.learn.springdesign.product;

import com.design.learn.springdesign.service.RechargeWarpperAdapter;
import com.design.learn.springdesign.util.GeneratorOrderNo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;


/**
* @Author wwy
* @Description 具体实现业务
* @Date 9:06 2019/4/5
* @Param
* @return
**/
@Service
public class IOSRechargeServiceImpl extends RechargeWarpperAdapter {

    /**
    * @Author wwy
    * @Description 此处需要实现生产orderNo
    * @Date 15:25 2019/4/4
    * @Param []
    * @return java.lang.String
    **/
    @Override
    public String getOrderNo() {
        return GeneratorOrderNo.getRequestNo("IOS");
    }

    /**
    * @Author wwy
    * @Description 此处可以建立枚举类
    * @Date 15:24 2019/4/4
    * @Param []
    * @return java.lang.String
    **/
    @Override
    public String[] getProductType() {
        return new String[]{"IOS"};
    }

    @Override
    public String processOnRechargeAliPayPre(String type, BigDecimal money) {
        String orderNo = getOrderNo();
        initOrder(type,orderNo);
        System.out.println(MessageFormat.format("IOS充值{0}创建订单完成,订单号为{1}",money,orderNo));

        return "ok";
    }

    @Override
    protected void handleTradeDetailBeforeCreate() {
        System.out.print("IOS订单生成前操作");

    }

    @Override
    protected void handleTradeDetailAfterCreate() {
        System.out.print("IOS订单生成后操作");

    }
}
