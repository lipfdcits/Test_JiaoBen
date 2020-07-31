package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我的全部get方法")
public class MyGetMethod {
    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获得cookies",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        Cookie cookie1=new Cookie("login","true");
        //Cookie cookie2=new Cookie("stutes","100");
        response.addCookie(cookie1);
        //response.addCookie(cookie2);
        return "恭喜你获得cookies信息成功";
    }

    /**
     * 要求客户端必须携带cookies信息才能访问
     * @return
     */
    @RequestMapping(value = "/getWithCookies",method = RequestMethod.GET)
    @ApiOperation(value = "要求客户携带cookies信息访问",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        if(Objects.isNull(cookies)){
            return "访问失败,cookies信息丢失";
        }

        for (Cookie cookie:cookies) {
            if(cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                return "访问成功";
            }
        }
        return "必须携带cookies信息才能访问";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/getWithParam",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问的get方法1",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,@RequestParam Integer end){
        Map<String,Integer> map=new HashMap<>();
        map.put("衣服",100);
        map.put("鞋",300);
        map.put("裤子",200);
        return map;
    }
    @RequestMapping(value = "/getWithParam/{start}/{end}",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问的get方法2",httpMethod = "GET")
    public Map<String,Integer> getList2(@PathVariable Integer start, @PathVariable Integer end){
        Map<String,Integer> map=new HashMap<>();
        map.put("衣服2",200);
        map.put("鞋2",200);
        map.put("裤子2",200);
        return map;
    }

}
