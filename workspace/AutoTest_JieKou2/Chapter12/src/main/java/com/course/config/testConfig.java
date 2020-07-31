package com.course.config;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

public class testConfig {
    public static String loginUrl;
    public static String updateUserInfoUrl;
    public static String getUserInfoUrL;
    public static String getUserListUrl;
    public static String addUserUrl;
    //执行方法的对象
    public static DefaultHttpClient defaultHttpClient;
    //cookies信息
    public static CookieStore store;
}
