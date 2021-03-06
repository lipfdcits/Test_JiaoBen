package com.lpf.flow;


import com.lpf.driver.Action;
import com.lpf.driver.login;
import com.lpf.driver.windowsClick;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.firstPage;
import page.selectHospital;
import page.shouyinqu.ShouKuan;

import javax.accessibility.Accessible;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ShouKuanLiuCheng {
    @BeforeTest
    public void loginPage() throws InterruptedException {
        login.loginAction("chrome", "https://testsaas.qingxiaoguo.com", "15110403429", "123456");
        Action.click(selectHospital.zhensuo1);
    }

    //关闭浏览器
//    @AfterTest
//    public void closed() throws InterruptedException {
//        Action.closed();
//    }

    @Test
    public static void ShouKuanFlow() throws InterruptedException, AWTException {
        Robot robot=new Robot();
        //点击收款模块
        Action.click(firstPage.shoukuan);
        String text=Action.getText(ShouKuan.shoukuanButton);
        while (text.equals("收款")){
            Thread.sleep(3000);
            //点击收款
            Action.click(ShouKuan.shoukuanButton);
            Thread.sleep(3000);
            //点击确定
            Action.click(ShouKuan.queren);
            Thread.sleep(2000);
            //点击打印类型
            windowsClick.windowsC("\\TestExcle\\点击打印名称.exe");
            //Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_DOWN);
            Thread.sleep(500);
            robot.keyPress(KeyEvent.VK_DOWN);
            Thread.sleep(500);
            robot.keyPress(KeyEvent.VK_DOWN);
            Thread.sleep(500);
            robot.keyPress(KeyEvent.VK_DOWN);
            Thread.sleep(500);
            robot.keyPress(KeyEvent.VK_DOWN);
            Thread.sleep(500);
            robot.keyPress(KeyEvent.VK_DOWN);
            Thread.sleep(500);
            robot.keyPress(KeyEvent.VK_DOWN);
            Thread.sleep(500);
            robot.keyPress(KeyEvent.VK_DOWN);
            Thread.sleep(500);
            //点击打印小票确定按钮
            windowsClick.windowsC("\\TestExcle\\打印.exe");
            //点击关闭
            Action.click(ShouKuan.guanbi);
            Thread.sleep(3000);
        }
    }
}
