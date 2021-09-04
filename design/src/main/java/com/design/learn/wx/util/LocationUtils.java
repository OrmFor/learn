package com.design.learn.wx.util;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dyf on 2019/2/26 18:45
 * 根据经纬度计算距离
 */
public class LocationUtils {
    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * @Author wwy
     * @Description 根据当前经纬度，算出半径为raidus(千米)的经纬度范围区间
     * @Date 15:56 2019/2/27
     * @Param [lat, lon, raidus]
     * @return double[]
     **/
    public static Map<String,Double> getAround(double lat, double lon, int raidus){

        Double latitude = lat;
        Double longitude = lon;

        Double degree = (24901*1609)/360.0;
        double raidusMile = raidus * 1000 ;

        Double dpmLat = 1/degree;
        Double radiusLat = dpmLat*raidusMile;
        Double minLat = latitude - radiusLat;
        Double maxLat = latitude + radiusLat;

        Double mpdLng = degree*Math.cos(latitude * (Math.PI/180));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng*raidusMile;
        Double minLng = longitude - radiusLng;
        Double maxLng = longitude + radiusLng;
        Map<String,Double> data = new HashMap<String,Double>();
        System.out.println(MessageFormat.format("minLat={0},minLng={1},maxLat={2},maxLng={3}",
                minLat,minLng,maxLat,maxLng));
        data.put("minLat",minLat);
        data.put("minLng",minLng);
        data.put("maxLat",maxLat);
        data.put("maxLng",maxLng);
        return data;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s*1000;
        return s;
    }

    public static void main(String[] args) {
//        double distance = getDistance(30.23752, 120.210266, 30.0023797, 120.209663);
//        System.out.println(distance);
          getAround(30.05,120.7,1);

    }
}
