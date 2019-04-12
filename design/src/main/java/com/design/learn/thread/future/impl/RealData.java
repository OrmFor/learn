package com.design.learn.thread.future.impl;

import com.design.learn.thread.future.service.Data;

/**
* @Author wwy
* @Description 此类用于模拟真实数据
* @Date 11:34 2019/4/4
* @Param
* @return
**/
public class RealData implements Data {

    private String result ;

    public RealData(String param){
        StringBuffer sb = new StringBuffer();
        for(int i = 0 ;i < 10 ; i++){
            sb.append(param);
        }

        //阻塞用于模拟处理业务数据
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
