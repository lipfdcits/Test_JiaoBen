package com.course.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyHttpClient {
    @Test
    public void test1() throws IOException {
        //定义get方法,并设置访问路径
        HttpGet get=new HttpGet("http://www.baidu.com");
        //执行get请求
        HttpClient client=new DefaultHttpClient();
        HttpResponse response=client.execute(get);
        String s = EntityUtils.toString(response.getEntity());
        System.out.println(s);
    }


}
