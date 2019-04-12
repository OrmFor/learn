package com.design.learn.springdesign.warpper.impl;

import com.design.learn.springdesign.service.RechargeService;
import com.design.learn.springdesign.warpper.CreateOrderWarpper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.WeakHashMap;

/**
* @Author wwy
* @Description 具体实现包装类的功能（核心组件）
* @Date 9:05 2019/4/5
* @Param
* @return
**/
@Component
public class CreateOrderWarpperImpl implements CreateOrderWarpper, InitializingBean {

    @Autowired(required = false)
    private List<RechargeService> services;

    private WeakHashMap<String, RechargeService> map;


    /**
     * @return java.lang.String
     * @Author wwy
     * @Description 入口实现
     * @Date 15:34 2019/4/4
     * @Param [type, money]
     **/
    @Override
    public String preOrderCreate(String type, BigDecimal money) {
        RechargeService rechargeService = getService(type);
        if (rechargeService != null) {
          return  rechargeService.processOnRechargeAliPayPre(type, money);
        }
        throw new RuntimeException("recharge is null");
    }

    private RechargeService getService(String type) {
        if (map.containsKey(type)) {
            return map.get(type);
        }
        throw new RuntimeException(String.format("CreateOrderWarpperImpl ： 暂不支持的业务种类%s", type));
    }

    @Override
    public boolean chcekIsOnLine() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        map = new WeakHashMap<String, RechargeService>();
        if (CollectionUtils.isEmpty(services)) return;
        for (RechargeService value : services) {
            for (String key : value.getProductType()) {
                map.put(key, value);
            }
        }
    }


}
