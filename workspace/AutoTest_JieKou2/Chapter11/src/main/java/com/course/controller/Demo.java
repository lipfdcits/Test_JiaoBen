package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(value = "v1",description = "第一个版本的demo")
public class Demo {
    //首先获取一个执行sql语句的对象
    @Autowired
    private SqlSessionTemplate template;
    @RequestMapping(value = "/getUserCount",method = RequestMethod.GET)
    public int getUserCount(){
        int a=template.selectOne("getUserCount");
        return a;
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public int addUser(@RequestBody User user){
        int a=template.insert("addUser",user);
        return a;
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public int updateUser(@RequestBody User user){
        int a=template.update("updateUser",user);
        return a;
    }
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public int deleteUser(@RequestParam int id){
        int a=template.delete("deleteUser",id);
        return a;
    }



}
