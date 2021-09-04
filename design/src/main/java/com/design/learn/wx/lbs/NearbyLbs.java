package com.design.learn.wx.lbs;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.design.learn.wx.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NearbyLbs {

    private static final String KEY = "7259bc4085b76bc0dac051e424d4954c";

    public static void main(String[] args) throws IOException {
        JSONArray arrayNew = new JSONArray();
        for (int i = 40 ; i<100 ; i++) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("key", KEY);
            paramMap.put("keywords", "餐饮服务|购物服务|生活服务");
            paramMap.put("city", "平江县");
            paramMap.put("location", "113.586984,28.704175");
            paramMap.put("offset", 20);
            paramMap.put("page", i);
            paramMap.put("output", "json");
            paramMap.put("radius",3000);
            paramMap.put("sortrule","distance");
            HttpResponse result = null;
            try {
                result = HttpUtils.doGet("https://restapi.amap.com",
                        "/v3/place/around", null, paramMap);
                //System.out.println(EntityUtils.toString(result.getEntity()));
                JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(result.getEntity()));
                System.out.println(jsonObject);
                Thread.sleep(3000);
                int count = jsonObject.getInteger("count");
                if(count == 0){
                    break;
                }
                JSONArray array = jsonObject.getJSONArray("pois");
                for(int j = 0 ; j < array.size() ; j++){
                    JSONObject jj = array.getJSONObject(j);
                    arrayNew.add(jj);
                }

                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;

        }

        Json2Excel.down(arrayNew);
    }

}
