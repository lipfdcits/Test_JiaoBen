package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    private String url;
    private ResourceBundle bundle;
    private CookieStore store;
    @BeforeTest
    public void beforeTest(){
        bundle=ResourceBundle.getBundle("application", Locale.CHINA);
        url=bundle.getString("test.url");
    }

    /**
     * 获取cookies信息
     */
    @Test
    public void testGetCookies() throws IOException {
        String TestUrl=url+bundle.getString("getCookies.uri");
        //System.out.println(TestUrl);
        HttpGet get=new HttpGet(TestUrl);
        DefaultHttpClient client=new DefaultHttpClient();
        HttpResponse response=client.execute(get);
        String result= EntityUtils.toString(response.getEntity(),"utf-8");
        //System.out.println(result);
        store=client.getCookieStore();
        List<Cookie> cookies=store.getCookies();
        for (Cookie cookie:cookies) {
            String name=cookie.getName();
            String value=cookie.getValue();
            System.out.println("name:"+name+"; value:"+value);
        }
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostMethod() throws IOException {
        //声明请求方法和路径
        HttpPost post=new HttpPost(url+bundle.getString("test.post.with.cookies"));
        //设置参数
        JSONObject param=new JSONObject();
        param.put("name","huhansan");
        param.put("age","18");
        //将参数带入请求中
        StringEntity entity=new StringEntity(param.toString());
        post.setEntity(entity);
        //设置请求头信息
        post.setHeader("content-type","application/json");
        //将cookies信息带入请求
        DefaultHttpClient client=new DefaultHttpClient();
        client.setCookieStore(store);
        //执行post方法,获取响应结果
        HttpResponse response=client.execute(post);
        String result=EntityUtils.toString(response.getEntity());
        System.out.println(result);
        //获取状态码
        int code=response.getStatusLine().getStatusCode();
        System.out.println(code);
        //结果比对
        //将响应结果转换为json格式,通过key获取value值,进行比对
        JSONObject jsonResult=new JSONObject(result);
        String huhansan= (String) jsonResult.get("huhansan");
        String status= (String) jsonResult.get("status");
        Assert.assertEquals(huhansan,"success");
        Assert.assertEquals(status,"1");
    }



}
