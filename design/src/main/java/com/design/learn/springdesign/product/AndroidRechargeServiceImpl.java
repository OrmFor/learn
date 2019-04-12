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
public class AndroidRechargeServiceImpl extends RechargeWarpperAdapter {
    @Override
    public String getOrderNo() {
        return GeneratorOrderNo.getRequestNo("Android");
    }

    @Override
    public String[] getProductType() {
        return new String[]{"Android"};
    }

    @Override
    public String processOnRechargeAliPayPre(String type, BigDecimal money) {
        String orderNo = getOrderNo();
        initOrder(type,orderNo);
        System.out.println(MessageFormat.format("Android充值{0}创建订单完成,订单号为{1}",money,orderNo));

        return "ok";
    }
}
