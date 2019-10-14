package com.design.learn.ddos;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TestMongoDb extends Thread {


    public void run() {
        HttpClient client = new DefaultHttpClient();
        while (true) {
            try {

                HttpPost post = new HttpPost("http://127.0.0.1:8080/api/1.0/testInsert");
                post.setHeader("content-type","application/json");
                post.setHeader("User-Agent",
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36"
                );

                //body = {"lat": 20.30748500000004, "lon": 25.271139, "raidus": 50000}
                JSONObject paramJson = new JSONObject();
                //	"visited":"王波",
                //	"visitedCode":"wb124",
                //	"visitor":"老吴",
                //	"visitorCode":"laowu123",
                //	"type":"潜在客户"
                paramJson.put("visited","王波"+Thread.currentThread().getName());
                paramJson.put("visitedCode","wb124"+Thread.currentThread().getName());
                paramJson.put("visitor","老吴"+Thread.currentThread().getName());
                paramJson.put("visitorCode","laowu123"+Thread.currentThread().getName());
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
