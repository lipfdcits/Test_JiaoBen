package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@Api(value = "v1",description = "用户管理系统")
@RequestMapping(value = "/v1")
public class UserManager {
    @Autowired
    private SqlSessionTemplate template;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口",httpMethod = "POST")
    public Boolean login(HttpServletResponse response, @RequestBody User user){
        //查询:根据输入的数据查询数据库,查看是否存在该数据
        //变量i表示数据库中存在i条符合条件的数据
        int i=template.selectOne("login",user);
        if(i==1){
            log.info("查到的用户是"+user.getUserName());
            Cookie cookie=new Cookie("login","true");
            response.addCookie(cookie);
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ApiOperation(value = "添加用户接口",httpMethod = "POST")
    public Boolean addUser(HttpServletRequest request,@RequestBody User user){
        int result=0;
        //判断cooikes信息是否正确
        Boolean x=verifyCookies(request);
        if(x==true){
            result= template.insert("addUser", user);
                log.info("添加用户"+user.getUserName()+"成功");
        }
        if(result>0){
            log.info("添加的用户数量为:"+result);
            return true;
        }
        return false;
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            if(cookie.getName().equals("login")&&cookie.getValue().equals("true")){
//                int i = template.insert("addUser", user);
//                log.info("添加用户"+user.getUserName()+"成功");
//                return true;
//            }
//        }
    }

    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表接口",httpMethod = "POST")
    public List<User> getUserList(HttpServletRequest request,@RequestBody User user){
        Boolean x=verifyCookies(request);
        if(x==true){
            List<User> users=template.selectList("getUserList",user);
            return users;
        }else {
            return null;
        }
    }

    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户信息接口",httpMethod = "POST")
    public List<User> getUserInfo(HttpServletRequest request,@RequestBody User user){
        Boolean x=verifyCookies(request);
        if(x==true){
            List<User> users=template.selectList("getUserList",user);
            return users;
        }else {
            return null;
        }
    }

    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    @ApiOperation(value = "更新用户接口",httpMethod = "POST")
    public int updateUser(HttpServletRequest request,@RequestBody User user){
        int result=0;
        //验证cookies
        Boolean x=verifyCookies(request);
        //业务逻辑
        if(x==true){
            result=template.update("updateUser",user);
        }
        log.info("更新的条目数为:"+result);
        return result;
    }





    //从接口中提取出验证cookies方法,方便以后调用
    private Boolean verifyCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            log.info("cookies为空");
            return false;
        }
        for (Cookie cookie: cookies) {
            if(cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                log.info("cookies验证通过");
                return true;
            }
        }
        return false;
    }


}
