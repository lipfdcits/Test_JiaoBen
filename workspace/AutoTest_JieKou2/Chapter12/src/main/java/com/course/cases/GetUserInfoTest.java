package com.course.cases;

import com.course.config.testConfig;
import com.course.model.GetUserInfoCase;
import com.course.model.InterfaceName;
import com.course.model.User;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoTest {
    @Test(dependsOnGroups = "loginTrue",description = "获取用户信息接口测试")
    public void getUserInfoCase() throws IOException {
        //拼接获得测试路径
        testConfig.getUserInfoUrL= ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        //用session对象执行sql
        SqlSession session=DatabaseUtil.getSqlSession();
        GetUserInfoCase getUserInfoCase=session.selectOne("getUserInfoCase",1);
        System.out.println(getUserInfoCase.toString());
        System.out.println(testConfig.getUserInfoUrL);

        //拿到测试数据后(getUserInfoCase),发送请求,并获得结果
        JSONArray resultJson=getResult(getUserInfoCase);
        System.out.println("执行请求返回的结果是:"+resultJson);
        //获得相应结果后,与测试用例中的期望值做对比
        //获取数据库中查询结果
        User user=session.selectOne(getUserInfoCase.getExpected(),getUserInfoCase);
        /**
         * 将查询出的数据结果转换为JSONArray格式,因为从请求中返回的结果是JSONArray格式,
         * 只能先将数据库中查询出的结果,转换为list格式后,在转换为JSONArray格式,同种格式下进行比较
         */

        List userList=new ArrayList();
        userList.add(user);
        JSONArray jsonArray=new JSONArray(userList);
        JSONArray jsonArray1=new JSONArray(resultJson.getString(0));
        System.out.println("查询数据库返回的结果是:"+jsonArray);
        Assert.assertEquals(jsonArray.toString(),jsonArray1.toString());

    }

    private JSONArray getResult(GetUserInfoCase getUserInfoCase) throws IOException {
        //定义请求方式并绑定测试路径
        HttpPost post=new HttpPost(testConfig.getUserInfoUrL);
        //设置参数
        JSONObject param=new JSONObject();
        param.put("id",getUserInfoCase.getId());
        //设置请求头信息
        post.setHeader("content-type","application/json");
        //将参数带入请求
        StringEntity entity=new StringEntity(param.toString());
        post.setEntity(entity);
        //设置cookies信息
        testConfig.defaultHttpClient.setCookieStore(testConfig.store);
        HttpResponse response=testConfig.defaultHttpClient.execute(post);
        String result=EntityUtils.toString(response.getEntity(),"utf-8");
        List resultList= Arrays.asList(result);
        JSONArray array=new JSONArray(resultList);

        return array;
    }
}
