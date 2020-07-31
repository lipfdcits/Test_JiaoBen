package com.course.server;

import com.course.bean.User;
import com.course.bean.User2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是我全部的post请求")
public class MyPostMethod {
    Cookie cookie=null;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登陆",httpMethod = "POST")
    public String getCookies(HttpServletResponse response,
                             @RequestParam(value = "userName",required = true) String userName,
                             @RequestParam(value = "password",required = true) String password){
        if(userName.equals("lpf")&&password.equals("123456")){
            cookie=new Cookie("login","true");
            response.addCookie(cookie);
            return "登陆成功,cookies信息获取成功!!!";
        }
        return "登陆失败,用户名或密码错误";
    }


    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                            @RequestBody User2 user){
        //获取cookies
        Cookie[] cookies=request.getCookies();
        //验证cookies
        for (Cookie c:cookies) {
            if(c.getName().equals("login")
                    &&c.getValue().equals("true")
                    &&user.getUserName().equals("lpf")
                    &&user.getPassword().equals("123456")){
                User u=new User();
                u.setName("zhangsan");
                u.setAge("18");
                u.setSex("man");
                return u.toString();
            }
        }

        return "参数不合法";
    }

}
