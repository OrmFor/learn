package com.design.learn.wx.tencent;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.design.learn.wx.lbs.Json2Excel;
import com.design.learn.wx.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public class SpiderController {

    //自己的key
    private static final String KEY = "YI5BZ-CCSKJ-O77FI-FSKCJ-BUASQ-LYB3T";
    private static final String SELLER_TABLE_ID = "5ea11230b9e63e2b0059c63b";

    private static final String SEARCH_NEARBY_URL = "https://apis.map.qq.com/ws/place/v1/search";
    public static void main(String[] args){
        JSONArray arrayNew = new JSONArray();
        for(int i = 1 ; i < 20 ; i++) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("key", KEY);
            paramMap.put("location", "28.704175,113.586984");
            paramMap.put("boundary", "region(平江县,0)");
            paramMap.put("keyword", "美食餐饮,生活服务,");
            paramMap.put("radius", 3000);
            paramMap.put("page_size", 10);
            paramMap.put("page_index", i);
            paramMap.put("output", "json");
            paramMap.put("orderby", "_distance");
            HttpResponse result = null;
            try {
                result = HttpUtils.doGet("https://apis.map.qq.com",
                        "/ws/place/v1/search", null, paramMap);
                JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(result.getEntity()));
                Thread.sleep(3000);

                System.out.println(jsonObject);
                JSONArray array = jsonObject.getJSONArray("data");
                for(int j = 0 ; j < array.size() ; j++){
                    JSONObject jj = array.getJSONObject(j);
                    arrayNew.add(jj);
                }
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Json2Excel.down(arrayNew);
    }
}
