package com.design.learn.wx.lbs;

import com.design.learn.wx.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LbsController {

    private static final String KEY = "YI5BZ-CCSKJ-O77FI-FSKCJ-BUASQ-LYB3T";
    private static final String SELLER_TABLE_ID = "5ea11230b9e63e2b0059c63b";

    private static final String SEARCH_NEARBY_URL = "https://apis.map.qq.com/place_cloud/search/nearby";
    public static void main(String[] args){

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("key",KEY);
        paramMap.put("table_id",SELLER_TABLE_ID);
        paramMap.put("location","30.05,120.65");
        paramMap.put("radius",5000);
        paramMap.put("output","json");
        paramMap.put("orderby","distance(30.05,120.65)");
        HttpResponse result = null;
        try {
            result = HttpUtils.doGet("https://apis.map.qq.com",
                    "/place_cloud/search/nearby",null,paramMap);
            System.out.println(EntityUtils.toString(result.getEntity()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
