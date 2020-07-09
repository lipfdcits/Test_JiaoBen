package com.lpf.flow;

import com.lpf.driver.Action;
import com.lpf.driver.SwitchUtil;
import com.lpf.driver.elementFind;
import com.lpf.driver.login;
import com.lpf.driver.tools.JxlFun;
import jxl.read.biff.BiffException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.firstPage;
import page.selectHospital;
import page.xitongshezhi.ZhenSuoGuanLi;

import java.io.IOException;
import java.util.List;

public class PaiBanLIuCheng {
    //登录成功后页面跳转检验
    @BeforeTest
    public static void loginPage() throws InterruptedException {
        login.loginAction("chrome", "https://testsaas.qingxiaoguo.com", "15110403429", "123456");
    }

    //关闭浏览器
//    @AfterTest
//    public void closed() throws InterruptedException {
//        Action.closed();
//    }
    @Test
    public static void PaiBanFlow() throws InterruptedException, IOException, BiffException {
        String path=System.getProperty("user.dir")+"\\TestExcle\\GuaHao.xls";
        String tishi = null;
        //点击诊所管理
        SwitchUtil.clickElement(firstPage.xitongshezhi, firstPage.zhensuoguanli);
        //遍历
        List<WebElement> list = elementFind.findElements(By.xpath("//*[@id=\"app\"]/div/div[2]/div[2]/div/div/div/div[1]/div/div/div/div"));
//        System.out.println(list.size());

        for (int i = 0; i < list.size(); i++) {
            String text = list.get(i).getText();
//            System.out.println(text);
            if (text.equals("排班设置")) {
                Action.click(ZhenSuoGuanLi.paibanshezhi);
                break;
            } else {
                continue;
            }
        }
//        //点击排班时间
//        Action.click(ZhenSuoGuanLi.paibanshijian);
//        //点击日期
//        Action.click(By.xpath("//*[@id=\"app\"]/div/div[2]/div[2]/div/div[2]/table/tbody/tr[1]/td[6]/div"));

//        List<WebElement> list1 = elementFind.findElements(By.xpath("//*[@id=\"pane-scheduling\"]/div/div/div[2]/div[3]/table/tbody/tr[5]/td"));
//        for (int i = 0; i < list1.size(); i++) {
//            String text1 = list1.get(i).getText();
//            System.out.println(text1);
//            if (text1.equals("李鹏飞")) {
//                Action.click(ZhenSuoGuanLi.paibanshijian);
//                break;
//            } else {
//                continue;
//            }
//        }
        loop1:
        for (int nameId = 2; nameId < JxlFun.readRows(path, "Sheet3")+1; nameId++) {

            //点击排班设置后输入查询内容
            Action.sendText(ZhenSuoGuanLi.chaxun, JxlFun.readText(path, "Sheet3",nameId,1));
            //点击查询
            Action.click(ZhenSuoGuanLi.chaxunButton);
            Thread.sleep(1000);
            if(Action.getText(By.xpath("//*[@id=\"pane-scheduling\"]/div/div/div[2]/div[3]/div/span")).equals("暂无数据")){
                nameId++;
                Action.sendText(ZhenSuoGuanLi.chaxun, JxlFun.readText(path, "Sheet3",nameId,1));
                //点击查询
                Action.click(ZhenSuoGuanLi.chaxunButton);
                continue loop1;
            }else {

                //点击排班时间
                Action.click(ZhenSuoGuanLi.paibanshijian1);
            }

            loop2:
            for (int i = 2; i < JxlFun.readRows(path, "Sheet2")+1; i++) {
                //选择排班日期
                Action.click(By.xpath(JxlFun.readText(path, "Sheet2", i, 1)));
                //点击接诊
                Action.click(ZhenSuoGuanLi.jiezhen);
                //点击接诊诊室
                Action.click(ZhenSuoGuanLi.jiezhenzhenshi);
//        List<WebElement> list2=elementFind.findElements(By.xpath("/html/body/div[3]/div[1]/div[1]/ul"));
//        System.out.println(list2.size());
//        for (int i = 0; i < list2.size(); i++) {
//            String text2=list2.get(i).getText();
//            System.out.println(text2);
//        }
                //点击选择具体诊室(第二个)
                //Action.click(By.cssSelector("body > div.el-select-dropdown.el-popper > div.el-scrollbar > div.el-select-dropdown__wrap.el-scrollbar__wrap > ul > li:nth-child(2)"));
                //点击选择具体诊室(第一个)
                Action.click(By.cssSelector("body > div.el-select-dropdown.el-popper > div.el-scrollbar > div.el-select-dropdown__wrap.el-scrollbar__wrap > ul > li"));
                //填写接诊人数
                Action.sendText(ZhenSuoGuanLi.jiezhenrenshu, "100");
//        //点击取消
//        Action.click(ZhenSuoGuanLi.quxiao);
                //点击确认
                Action.click(ZhenSuoGuanLi.queding);
                Thread.sleep(3000);
                //判断"取消"是否存在
                if(Action.isDisplay(ZhenSuoGuanLi.quxiao)){
                    Action.click(ZhenSuoGuanLi.quxiao);
                }else {
                    //排班--当前时间
                    Action.click(ZhenSuoGuanLi.fanhui);
                    break loop2;
                    //排班--本周所有时间
                    //continue;
                }
            }
            //Action.click(ZhenSuoGuanLi.fanhui);
            //Action.refresh();
        }


    }
}
