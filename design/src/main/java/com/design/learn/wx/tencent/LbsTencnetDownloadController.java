package com.design.learn.wx.tencent;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.design.learn.wx.lbs.Json2Excel;
import com.design.learn.wx.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LbsTencnetDownloadController {


    //private static final String KEY = "YI5BZ-CCSKJ-O77FI-FSKCJ-BUASQ-LYB3T";
      private static final String PROD_KEY = "YW7BZ-HVDRP-WXNDV-VEFEO-PZEYE-S2FBD";

      private static final String PROD_SELLER_TABLE_ID = "5eaa3a69cc6ac60cabd2da58";
    //private static final String SELLER_TABLE_ID = "5ea11230b9e63e2b0059c63b";



    public static void main(String[] args){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("key",PROD_KEY);
        paramMap.put("table_id",PROD_SELLER_TABLE_ID);
        paramMap.put("page_size",200);
        HttpResponse result = null;
        try {
            result = HttpUtils.doGet("https://apis.map.qq.com",
                    "/place_cloud/data/list",null,paramMap);
            JSONObject json = JSONObject.parseObject(EntityUtils.toString(result.getEntity()));

            JSONObject obj = json.getJSONObject("result");
            JSONArray array = obj.getJSONArray("data");
            JSONArray arrayNew = new JSONArray();
            for(int i = 0; i< array.size() ; i++){
                JSONObject jsonObject = array.getJSONObject(i);
                JSONObject jsonNew = new JSONObject();
                Set<String> keys = jsonObject.keySet();
                for (String s : keys) {
                    if(s.equals("x")){
                        JSONObject x = jsonObject.getJSONObject("x");
                        jsonNew.put("old_id",x.getString("old_id"));
                        continue;
                    }

                }
                jsonNew.put("id",jsonObject.getString("id"));

                //arrayNew.add(jsonNew);
                System.out.println("update seller_address_info set geo_id = \'"+jsonObject.getString("id") +"\'"+
                      " where geo_id = \'"+ jsonNew.getString("old_id") +"\';");
            }
           // Json2Excel.down(arrayNew);

        } catch (Exception e) {
            e.printStackTrace();
        }
     }
}
