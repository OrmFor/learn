package com.design.learn.wx.warpper;

import com.alibaba.fastjson.JSONObject;
import com.design.learn.wx.bean.base.WxpayRequest;
import com.design.learn.wx.bean.base.WxpayResponse;
import com.design.learn.wx.bean.wxrequest.WxpayAccessTokenRequest;
import com.design.learn.wx.bean.wxrespone.WxpayAccessTokenResponse;
import com.design.learn.wx.bean.wxrespone.WxpayJsTicketResponse;
import com.design.learn.wx.config.WXPayConfig;
import com.design.learn.wx.wxconstant.WXPayConstants;
import com.design.learn.wx.wxconstant.WXURL;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

public abstract class WXClientAdapter extends WXPayConfig implements WXClient {

   /* @Resource
    private RestTemplate restTemplate;*/

    @Override
    public <T extends WxpayResponse> T getAccessToken(WxpayRequest<T> request){
        WxpayAccessTokenRequest accessTokenRequest = (WxpayAccessTokenRequest) request;
        String result = new RestTemplate().
                getForObject(MessageFormat.format(WXURL.BASE_ACCESS_TOKEN,
                        WXPayConstants.APP_ID, WXPayConstants.SECRET), String.class);
        JSONObject json = JSONObject.parseObject(result);
        System.out.println(result);
        try {
            WxpayAccessTokenResponse wxpayAccessTokenResponse = new WxpayAccessTokenResponse();
            wxpayAccessTokenResponse.setBody(json.getString("access_token"));
            return (T) wxpayAccessTokenResponse;
        } catch (Exception e) {
           e.printStackTrace();
        }

        return null;
    }

    @Override
    public <T extends WxpayResponse> T getJsTicket(WxpayRequest<T> request) {
        return (T) new WxpayJsTicketResponse();
    }
}
