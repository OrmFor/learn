package com.design.learn.wx.warpper;

import com.design.learn.wx.bean.base.WxpayRequest;
import com.design.learn.wx.bean.base.WxpayResponse;

public interface WXClient {

    public <T extends WxpayResponse> T getAccessToken(WxpayRequest<T> request);


    public <T extends WxpayResponse> T getJsTicket(WxpayRequest<T> request);


   // public <T extends WxpayResponse> T getAccessToken(WxpayRequest<T> request);


  //  public <T extends WxpayResponse> T getJsTicket(WxpayRequest<T> request);

}
