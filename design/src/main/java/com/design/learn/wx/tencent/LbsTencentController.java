package com.design.learn.wx.tencent;

import com.design.learn.wx.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public class LbsTencentController {

    private static final String KEY = "YI5BZ-CCSKJ-O77FI-FSKCJ-BUASQ-LYB3T";
    private static final String SELLER_TABLE_ID = "5ea11230b9e63e2b0059c63b";

    private static final String SEARCH_NEARBY_URL = "https://apis.map.qq.com/place_cloud/search/nearby";
    public static void main(String[] args){

//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("key",KEY);
//        paramMap.put("table_id",SELLER_TABLE_ID);
//        paramMap.put("location", "30.086079,120.490564");
//        paramMap.put("radius",5000);
//        paramMap.put("output","json");
//        paramMap.put("orderby","distance(30.05,120.65)");
//        paramMap.put("keyword","尽猎");
//        HttpResponse result = null;
//        try {
//            result = HttpUtils.doGet("https://apis.map.qq.com",
//                    "/place_cloud/search/nearby",null,paramMap);
//            System.out.println(EntityUtils.toString(result.getEntity()));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        String l = "https://kinlie-xhz.oss-cn-hangzhou.aliyuncs.com/other/29c2d3703da00d8155c3a98cc0eafc31.jpg";
        System.out.println(l.length());
    }
}
