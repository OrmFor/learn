package com.design.learn.basejava.IP2int;

public class IP2int {
    public static void main(String[] args) {
        String ip = "192.168.10.2";
        String[] ipSplit = ip.split("\\.");
        long l = (Long.parseLong(ipSplit[0]) << 24) + (Long.parseLong(ipSplit[1]) << 16) + (Long.parseLong(ipSplit[2]) << 8) + Long.parseLong(ipSplit[3]);
        System.out.println(l);
        System.out.println(longToIp(l));

    }


    public static String longToIp(long longIp){
        StringBuffer sb = new StringBuffer("");
        sb.append(String.valueOf((longIp>>>24)));
        sb.append(".");
        sb.append(String.valueOf((longIp  & 0x00FFFFFF)   >>> 16));
        sb.append(".");

        sb.append(String.valueOf((longIp   & 0x0000FFFF) >>> 8) );
        sb.append(".");

        sb.append(String.valueOf(longIp & 0x000000FF));

        return sb.toString();

    }

}
