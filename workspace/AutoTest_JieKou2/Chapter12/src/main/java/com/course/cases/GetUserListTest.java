package com.course.cases;

import com.course.config.testConfig;
import com.course.model.GetUserListCase;
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
import java.util.List;

public class GetUserListTest {

    @Test(dependsOnGroups = "loginTrue",description = "获得用户列表接口测试")
    public void getUserListTest() throws IOException {
        //获取测试路径
        testConfig.getUserListUrl= ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        //获取执行sql的session对象
        SqlSession session=DatabaseUtil.getSqlSession();
        //使用session对象执行sql
        GetUserListCase getUserListCase=session.selectOne("getUserListCase",1);
        System.out.println(getUserListCase.toString());
        System.out.println(testConfig.getUserListUrl);

        //拿到测试数据后(getUserListCase),发送请求,并获得结果
        JSONArray resultJson=getJsonResult(getUserListCase);
        System.out.println("执行请求获取的结果是:"+resultJson);
        //获得相应结果后,与测试用例中的期望值做对比
        List<User> userList=session.selectList(getUserListCase.getExpected(),getUserListCase);
        for(User u:userList){
            System.out.println("查询数据库获取的user:"+u.toString());
        }
        JSONArray userListJson=new JSONArray(userList);
        Assert.assertEquals(userListJson.length(),resultJson.length());
        for (int i = 0; i < resultJson.length(); i++) {
            JSONObject expect= (JSONObject) resultJson.get(i);
            JSONObject actual= (JSONObject) resultJson.get(i);
            Assert.assertEquals(expect.toString(),actual.toString());
        }
    }

    private JSONArray getJsonResult(GetUserListCase getUserListCase) throws IOException {
        //定义请求方式并绑定测试路径
        HttpPost post=new HttpPost(testConfig.getUserListUrl);
        //设置参数
        JSONObject param=new JSONObject();
        param.put("userName",getUserListCase.getUserName());
        param.put("age",getUserListCase.getAge());
        param.put("sex",getUserListCase.getSex());
        //将参数带入请求
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置请求头信息
        post.setHeader("content-type","application/json");
        //设置cookies信息
        testConfig.defaultHttpClient.setCookieStore(testConfig.store);
        //发送请求,获取结果
        HttpResponse response=testConfig.defaultHttpClient.execute(post);
        String result=EntityUtils.toString(response.getEntity(),"utf-8");
        //将结果转换为list格式
        JSONArray jsonArray=new JSONArray(result);

        return jsonArray;
    }

}
