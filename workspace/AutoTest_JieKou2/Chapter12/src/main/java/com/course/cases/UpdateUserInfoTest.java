package com.course.cases;

import com.course.config.testConfig;
import com.course.model.InterfaceName;
import com.course.model.UpdateUserInfoCase;
import com.course.model.User;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class UpdateUserInfoTest {

    @Test(dependsOnGroups = "loginTrue",description = "更新用户信息接口测试")
    public void updateUserInfoTest() throws IOException {
        //获取测试路径
        testConfig.updateUserInfoUrl= ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        //获取session对象
        SqlSession session=DatabaseUtil.getSqlSession();
        //执行sql
        UpdateUserInfoCase updateUserInfoCase=session.selectOne("updateUserInfoCase",1);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(testConfig.updateUserInfoUrl);

        //发送请求,获得结果
        int result=getResult(updateUserInfoCase);
        System.out.println("执行请求:修改的条目数为:"+result);
        //查询数据库,查询出结果做比对
        User user=session.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);

    }



    @Test(dependsOnGroups = "loginTrue",description = "删除用户信息接口测试")
    public void deleteUserInfoTest() throws IOException {
        //获取测试路径
        testConfig.updateUserInfoUrl= ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        //获取session对象
        SqlSession session=DatabaseUtil.getSqlSession();
        //执行sql
        UpdateUserInfoCase updateUserInfoCase=session.selectOne("updateUserInfoCase",2);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(testConfig.updateUserInfoUrl);
        //发送请求,获得结果
        int result=getResult(updateUserInfoCase);
        System.out.println("执行请求:修改的条目数为:"+result);
        //查询数据库,查询出结果做比对
        User user=session.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);
        System.out.println("查询数据库返回的结果是:"+user);
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
    }

    private int getResult(UpdateUserInfoCase updateUserInfoCase) throws IOException {
        HttpPost post=new HttpPost(testConfig.updateUserInfoUrl);
        JSONObject param=new JSONObject();
        param.put("id",updateUserInfoCase.getId());
        param.put("userName",updateUserInfoCase.getUserName());
        param.put("sex",updateUserInfoCase.getSex());
        param.put("age",updateUserInfoCase.getAge());
        param.put("permission",updateUserInfoCase.getPermission());
        param.put("isDelete",updateUserInfoCase.getIsDelete());
        StringEntity entity =new StringEntity(param.toString());
        post.setEntity(entity);
        post.setHeader("content-type","application/json");
        testConfig.defaultHttpClient.setCookieStore(testConfig.store);
        HttpResponse response=testConfig.defaultHttpClient.execute(post);
        String result= EntityUtils.toString(response.getEntity(),"utf-8");


        return Integer.parseInt(result);
    }

}
