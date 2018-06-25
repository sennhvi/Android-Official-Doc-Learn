package com.endergeek.rookie.androidnanohttpdsuperbigfileuploadserver;

import android.util.Log;

/**
 * Url字段解析工具类
 * Created by wangsenhui on 12/23/16.
 */

public class UrlParserUtil {

    private static final String TAG = UrlParserUtil.class.getSimpleName();

    /**
    /**
     * 与ip顺序相反的16进制到10进制ip地址转换使用此方法
     * 由于wifi获取地址为反,因此使用此方法
     * @param ip
     * @return
     */
    public static String selfLongToStringIp(long ip) {
        String ipstr;
        if(ip != 0x00000000)
            ipstr = (ip & 0xff)+ "." + ((ip >> 8) & 0xff)  + "." + ((ip >> 16) & 0xff)
                    + "." +  ((ip >> 24) & 0xff);
        else
            ipstr = "Relaunch with WiFi";
        Log.w(TAG, "ipSelfstr-long:" + ipstr);
        return ipstr;
    }

    /**
     * 与ip顺序一致的16进制到10进制ip地址转换使用此方法
     * @param ip
     * @return
     */
    public static String longToStringIp(long ip) {
        String ipstr;
        if(ip != 0x00000000)
            ipstr = ((ip >> 24) & 0xff)+ "." +  ((ip >> 16) & 0xff) + "." + ((ip >> 8) & 0xff)
                    + "." + (ip & 0xff) ;
        else
            ipstr = "Relaunch with WiFi";
        Log.w(TAG, "ipstr-long:" + ipstr);
        return ipstr;
    }

    /**
     * 判断ip与ip/mask是否处于同一网段,如"192.168.31.28" 与"192.168.31.254/24"
     * @param ip
     * @param cidr
     * @return
     */
    public static boolean isInRange(String ip, String cidr) {
        String[] ips = ip.split("\\.");
        int ipAddr = (Integer.parseInt(ips[0]) << 24)
                | (Integer.parseInt(ips[1]) << 16)
                | (Integer.parseInt(ips[2]) << 8) | Integer.parseInt(ips[3]);
        int type = Integer.parseInt(cidr.replaceAll(".*/", ""));
        int mask = 0xFFFFFFFF << (32 - type);
        String cidrIp = cidr.replaceAll("/.*", "");
        String[] cidrIps = cidrIp.split("\\.");
        int cidrIpAddr = (Integer.parseInt(cidrIps[0]) << 24)
                | (Integer.parseInt(cidrIps[1]) << 16)
                | (Integer.parseInt(cidrIps[2]) << 8)
                | Integer.parseInt(cidrIps[3]);

        return (ipAddr & mask) == (cidrIpAddr & mask);
    }
}

