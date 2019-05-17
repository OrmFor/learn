package com.design.learn.wx.exception;

public class WxpayApiException extends Exception {


    private static final long serialVersionUID = -238091758285157331L;

    private String errCode;
    private String errMsg;

    public WxpayApiException() {
        super();
    }

    public WxpayApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public WxpayApiException(String message) {
        super(message);
    }

    public WxpayApiException(Throwable cause) {
        super(cause);
    }

    public WxpayApiException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

}