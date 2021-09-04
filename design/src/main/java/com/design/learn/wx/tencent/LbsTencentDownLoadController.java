package com.design.learn.wx.tencent;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.design.learn.wx.lbs.ExcelUtil;
import com.design.learn.wx.lbs.Json2Excel;
import com.design.learn.wx.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LbsTencentDownLoadController {

    private static final String KEY = "YW7BZ-HVDRP-WXNDV-VEFEO-PZEYE-S2FBD";
    private static final String SELLER_TABLE_ID = "5eaa3a69cc6ac60cabd2da58";

    private static final String SEARCH_NEARBY_URL = "https://apis.map.qq.com/place_cloud/data/list" +
            "?table_id=5d3581dc6ce89813ed0b2cbd&orderby=id&page_index=1&page_size=20&key=开发Key";
    public static void main(String[] args){
        try {

                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("key", KEY);
                paramMap.put("table_id", SELLER_TABLE_ID);
                paramMap.put("page_index", 1);
                paramMap.put("page_size", 105);
                HttpResponse result = null;
                result = HttpUtils.doGet("https://apis.map.qq.com",
                        "/place_cloud/data/list", null, paramMap);
               // System.out.println(EntityUtils.toString(result.getEntity()));
                JSONObject json = JSONObject.parseObject(EntityUtils.toString(result.getEntity()));
                JSONObject obj = json.getJSONObject("result");
                     JSONArray array = obj.getJSONArray("data");
                 JSONArray arrayNew = new JSONArray();
                for (int i = 0; i < array.size(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    System.out.println(jsonObject.toJSONString());
                    JSONObject jsonNew = new JSONObject();
                    Set<String> keys = jsonObject.keySet();
                    for (String s : keys) {
                        if (s.equals("x")) {
                            JSONObject x = jsonObject.getJSONObject("x");
                            jsonNew.put("adcode", x.getString("adcode"));
                            jsonNew.put("agent_seller_id", x.getString("agent_seller_id"));
                            jsonNew.put("citycode", x.getString("citycode"));
                            jsonNew.put("contacts_person", x.getString("contacts_person"));
                            jsonNew.put("contacts_phone", x.getString("contacts_phone"));
                            jsonNew.put("contacts_phone_2", x.getString("contacts_phone_2"));
                            jsonNew.put("contacts_tel", x.getString("contacts_tel"));
                            jsonNew.put("pcode", x.getString("pcode"));
                            jsonNew.put("seller_id", x.getString("seller_id"));
                            jsonNew.put("seller_name", x.getString("seller_name"));
                            jsonNew.put("shop_id", x.getString("shop_id"));
                        }
                        jsonObject.remove("x");
                    }
                    arrayNew.add(jsonNew);
                }

                Json2Excel.down(array);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
