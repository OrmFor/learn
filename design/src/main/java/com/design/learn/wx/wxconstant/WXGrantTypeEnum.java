package com.design.learn.wx.wxconstant;

public enum WXGrantTypeEnum {
    CLIENT_CREDENTIAL("client_credential", "access_token获取");

    private String key;
    private String decription;


    WXGrantTypeEnum(String key, String decription) {
        this.key = key;
        this.decription = decription;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}
