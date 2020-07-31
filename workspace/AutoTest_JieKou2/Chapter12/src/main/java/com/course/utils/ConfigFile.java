package com.course.utils;

import com.course.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 工具类:获取拼接的测试路径
 */
public class ConfigFile {

    private static ResourceBundle bundle=ResourceBundle.getBundle("application", Locale.CHINA);
    //拼接url
    public static String getUrl(InterfaceName name){
        String address=bundle.getString("test.url");
        String uri="";
        String testUrl;
        if(name==InterfaceName.ADDUSERINFO){
            uri=bundle.getString("addUser.uri");
        }else if(name==InterfaceName.UPDATEUSERINFO){
            uri=bundle.getString("updateUserInfo.uri");
        }else if(name==InterfaceName.LOGIN){
            uri=bundle.getString("login.uri");
        }else if(name==InterfaceName.GETUSERINFO){
            uri=bundle.getString("getUserInfo.uri");
        }else if(name==InterfaceName.GETUSERLIST){
            uri=bundle.getString("getUserList.uri");
        }else {
            System.out.println("参数不合法");
        }

        testUrl=address+uri;
        return testUrl;
    }

}
