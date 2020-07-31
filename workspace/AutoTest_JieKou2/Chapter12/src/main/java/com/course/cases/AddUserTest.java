package com.course.cases;

import com.course.config.testConfig;
import com.course.model.AddUserCase;
import com.course.model.InterfaceName;
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

public class AddUserTest {
    /**
     * 取测试数据(自己在数据库中设计的测试数据)
     * @throws IOException
     */
    @Test(dependsOnGroups = "loginTrue",description = "添加用户接口测试")
    public void addUser() throws IOException, InterruptedException {
        //取测试用例数据
        //先获取执行sql的对象
        SqlSession session=DatabaseUtil.getSqlSession();
        //在用获得的session对象执行sql
        AddUserCase addUserCase=session.selectOne("AddUserCase",1);
        System.out.println(addUserCase.toString());
        System.out.println(testConfig.addUserUrl);


        //用测试用例的数据,向接口发送请求,获取结果
        String result=getResult(addUserCase);
        System.out.println("执行请求返回的结果是:"+result);
        Thread.sleep(3000);
        //验证返回结果
        //从接口对应的数据库中查询实际返回结果
        User user=session.selectOne("AddUser",addUserCase);
        System.out.println("查询数据库返回的结果是:"+user.toString());
        Assert.assertEquals(addUserCase.getExpected(),result);

    }

    private String getResult(AddUserCase addUserCase) throws IOException {
        //声明post请求,并绑定测试地址
        HttpPost post=new HttpPost(ConfigFile.getUrl(InterfaceName.ADDUSERINFO));
        //设置参数
        JSONObject param=new JSONObject();
        param.put("userName",addUserCase.getUserName());
        param.put("password",addUserCase.getPassword());
        param.put("sex",addUserCase.getSex());
        param.put("age",addUserCase.getAge());
        param.put("permission",addUserCase.getPermission());
        param.put("isDelete",addUserCase.getIsDelete());
        //将参数带入请求
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置请求头信息
        post.setHeader("content-type","application/json");
        //设置cookies信息
        testConfig.defaultHttpClient.setCookieStore(testConfig.store);
        //执行post方法
        HttpResponse response = testConfig.defaultHttpClient.execute(post);
        String result=EntityUtils.toString(response.getEntity());
        //System.out.println(result);
        return result;

    }

}
