package com.design.learn;

import java.util.Map;

public class JSONUtils {

    public static Map<Object, Object> jsonToMap(Object jsonObj) {
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(jsonObj);
        Map<Object, Object> map = (Map)jsonObject;
        return map;
    }
}
