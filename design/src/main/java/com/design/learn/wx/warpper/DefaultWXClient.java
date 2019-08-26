package com.design.learn.wx.warpper;

import com.design.learn.wx.domain.WXPayDomain;
import com.design.learn.wx.domain.WXPayDomainSimpleImpl;
import com.design.learn.wx.warpper.WXClientAdapter;
import com.design.learn.wx.wxconstant.WXPayConstants;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class DefaultWXClient extends WXClientAdapter {


    private byte[] certData;
    private static DefaultWXClient INSTANCE;

    private DefaultWXClient() throws Exception {
        /*String certPath = WXPayConstants.APICLIENT_CERT;
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();*/
    }

    public static DefaultWXClient getInstance() throws Exception {
        if (INSTANCE == null) {
            synchronized (DefaultWXClient.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DefaultWXClient();
                }
            }
        }
        return INSTANCE;
    }



    @Override
    public String getAppID() {
        return WXPayConstants.APP_ID;
    }

    @Override
    public String getMchID() {
        return WXPayConstants.MCH_ID;
    }

    @Override
    public String getKey() {
        return WXPayConstants.API_KEY;
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis;
        certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 2000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    @Override
    public WXPayDomain getWXPayDomain() {
        return WXPayDomainSimpleImpl.instance();
    }

    public String getPrimaryDomain() {
        return "api.mch.weixin.qq.com";
    }

    public String getAlternateDomain() {
        return "api2.mch.weixin.qq.com";
    }

    @Override
    public int getReportWorkerNum() {
        return 1;
    }

    @Override
    public int getReportBatchSize() {
        return 2;
    }





}
