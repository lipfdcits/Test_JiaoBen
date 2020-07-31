package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultHttpClientConnectionOperator;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {

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
        String result=EntityUtils.toString(response.getEntity(),"utf-8");
        //System.out.println(result);
        store=client.getCookieStore();
        List<Cookie> cookies=store.getCookies();
        for (Cookie cookie:cookies) {
            String name=cookie.getName();
            String value=cookie.getValue();
            System.out.println("name:"+name+"; value:"+value);
        }
    }

    @Test(dependsOnMethods = "testGetCookies")
    public void getWithCookies() throws IOException {
        //拼接测试路径
        String TestUrl=url+bundle.getString("test.get.with.cookies");
        HttpGet get=new HttpGet(TestUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        client.setCookieStore(store);
        HttpResponse response=client.execute(get);
        int code=response.getStatusLine().getStatusCode();
        if(code==200){
            String result=EntityUtils.toString(response.getEntity());
            System.out.println( result);
        }
    }

    @Test(dependsOnMethods = "testGetCookies")
    public void postWithcookies() throws IOException {
        String TestUrl=url+bundle.getString("test.post.with.cookies");
        HttpPost post=new HttpPost(TestUrl);
        //设置参数
        JSONObject param=new JSONObject();
        param.put("name","huhansan");
        param.put("age","18");
        //将参数添加到请求中
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置cookies
        DefaultHttpClient client=new DefaultHttpClient();
        client.setCookieStore(store);
        //设置请求头信息
        post.setHeader("content-type","application/json");
        //执行post方法
        HttpResponse response=client.execute(post);
        String result=EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

    }


}
