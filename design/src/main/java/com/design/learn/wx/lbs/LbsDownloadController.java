package com.design.learn.wx.lbs;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.design.learn.wx.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LbsDownloadController {

    //测试key
   // private static final String KEY = "5c46d2f5fb04b703e6abd012c038288a";
    private static final String KEY = "5c46d2f5fb04b703e6abd012c038288a";

    //测试
   // private static final String SELLER_TABLE_ID = "5e1c7979eefc9076d2ac259f";
    private static final String SELLER_TABLE_ID = "5e3fedc6c172610ee1e2efbf";

    private static final String SEARCH_NEARBY_URL = "https://apis.map.qq.com/place_cloud/search/nearby";


    private static final String GET_DATA_LIST_URL = "https://yuntuapi.amap.com/datamanage/data/list";
    public static void main(String[] args){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("key",KEY);
        paramMap.put("tableid",SELLER_TABLE_ID);
        paramMap.put("limit",100);
        paramMap.put("page",1);

        HttpResponse result = null;
        try {
            result = HttpUtils.doGet("https://yuntuapi.amap.com",
                    "/datamanage/data/list",null,paramMap);
            JSONObject json = JSONObject.parseObject(EntityUtils.toString(result.getEntity()));
            System.out.println(json.toJSONString());
            JSONArray array = json.getJSONArray("datas");
            JSONArray arrayNew = new JSONArray();
            for(int i = 0; i< array.size() ; i++){
                JSONObject jsonObject = array.getJSONObject(i);
                System.out.println(jsonObject.toJSONString());
                JSONObject jsonNew = new JSONObject();
                Set<String> keys = jsonObject.keySet();
                for (String s : keys) {

                    if(s.startsWith("_")) {
                        if (s.equals("_name")) {
                            jsonNew.put("title", jsonObject.getString(s));

                        }else if (s.equals("_address")) {
                            jsonNew.put("address", jsonObject.getString(s));

                        }else if (s.equals("_location")) {
                            String location = jsonObject.getString(s);
                            String[] locations = location.split(",");
                            jsonNew.put("lng",locations[0]);
                            jsonNew.put("lat",locations[1]);
                        }else  if(s.equals("_id")){
                            jsonNew.put("id",jsonObject.getString(s));
                        }

                        continue;
                    }

                   jsonNew.put("x."+s,jsonObject.getString(s));
                }

                jsonNew.put("tel", jsonObject.getString("contacts_tel"));

                arrayNew.add(jsonNew);

            }
            Json2Excel.down(arrayNew);

        } catch (Exception e) {
            e.printStackTrace();
        }
     }
}
