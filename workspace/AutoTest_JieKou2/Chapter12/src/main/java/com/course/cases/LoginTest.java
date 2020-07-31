package com.course.cases;

import com.course.config.testConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.model.User;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class LoginTest {


    @BeforeTest(groups = "loginTrue",description = "获取测试路径和执行请求的对象")
    public void beforeTest(){
        //拼接获取测试url
        testConfig.loginUrl= ConfigFile.getUrl(InterfaceName.LOGIN);
        testConfig.addUserUrl=ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        testConfig.getUserInfoUrL=ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        testConfig.getUserListUrl=ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        testConfig.updateUserInfoUrl=ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        testConfig.defaultHttpClient=new DefaultHttpClient();
    }
    @Test(groups = "loginTrue",description = "登陆成功接口测试")
    public void loginTrue() throws IOException {
        SqlSession session= DatabaseUtil.getSqlSession();
        LoginCase loginCase=session.selectOne("loginCase",1);
        System.out.println(loginCase.toString());
        System.out.println(testConfig.loginUrl);

        //发送请求,获得结果
        String result=getResult(loginCase);
        System.out.println("发起登录请求,返回的结果是:"+result);
        //验证结果
        Assert.assertEquals(loginCase.getExpected(),result);


    }

    @Test(groups = "loginFalse",description = "登陆失败接口测试")
    public void loginFalse() throws IOException {
        SqlSession session= DatabaseUtil.getSqlSession();
        LoginCase loginCase=session.selectOne("loginCase",2);
        System.out.println(loginCase.toString());
        System.out.println(testConfig.loginUrl);
        //发送请求,获得结果
        String result=getResult(loginCase);
        System.out.println("发起登录请求,返回的结果是:"+result);
        //验证结果
        Assert.assertEquals(loginCase.getExpected(),result);
    }

    private String getResult(LoginCase loginCase) throws IOException {
        //定义请求方式并绑定测试路径
        HttpPost post=new HttpPost(testConfig.loginUrl);
        //设置参数
        JSONObject param=new JSONObject();
        param.put("userName",loginCase.getUserName());
        param.put("password",loginCase.getPassword());
        //将参数带入请求
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置请求头信息
        post.setHeader("content-type","application/json");
        //执行post方法
        HttpResponse response=testConfig.defaultHttpClient.execute(post);
        String result= EntityUtils.toString(response.getEntity());

        //为cookies赋值
        testConfig.store=testConfig.defaultHttpClient.getCookieStore();
        return result;
    }


}
