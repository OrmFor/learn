package com.design.learn.wx.bean.base;

import java.io.Serializable;
import java.util.Map;

public abstract class WxpayResponse implements Serializable {

    private static final long   serialVersionUID = 5014379068811962022L;

    private String              code;

    private String              msg;

    private String              subCode;

    private String              subMsg;

    private String              body;
    private Map<String, String> params;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}


