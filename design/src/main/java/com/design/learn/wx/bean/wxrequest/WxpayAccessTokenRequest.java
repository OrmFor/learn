package com.design.learn.wx.bean.wxrequest;

import com.design.learn.wx.bean.base.WxpayRequest;

public class WxpayAccessTokenRequest extends WxpayRequest {
    
    private String grantType = "client_credential";


    public String getGrantType() {
        return grantType;
    }

    public WxpayAccessTokenRequest setGrantType(String grantType) {
        this.grantType = grantType;
        return this;
    }
}
