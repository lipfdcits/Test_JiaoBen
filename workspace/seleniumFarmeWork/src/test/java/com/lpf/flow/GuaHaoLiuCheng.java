package com.lpf.flow;

import com.lpf.driver.*;
import com.lpf.driver.tools.JxlFun;
import jxl.read.biff.BiffException;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.firstPage;
import page.selectHospital;
import page.shouyinqu.GuaHao;

import java.io.IOException;

//挂号流程,挂号日期未自动选择,后续优化,目前手动更改第73行代码调整至对应日期可自动挂号

public class GuaHaoLiuCheng {
    static String exclePath=System.getProperty("user.dir")+"\\TestExcle\\GuaHao.xls";

    //登录成功后页面跳转检验
    @Test
    public void loginPage() throws InterruptedException {
        login.loginAction("chrome","https://testsaas.qingxiaoguo.com","15110403429","123456");
    }
    //关闭浏览器
//    @AfterTest
//    public void closed() throws InterruptedException {
//        Action.closed();
//    }
    @Test
    public static void GuaHaoFlow() throws InterruptedException, IOException, BiffException {
        //点击挂号
        SwitchUtil.clickElement(firstPage.shouyinqu,firstPage.guahao);
        //遍历
        for (int i = 2; i < JxlFun.readRows(exclePath,"Sheet1")+1; i++) {
            //点击选择诊室
            Action.click(GuaHao.xuanzekeshi);
            //点击选择医生
            //Action.click(GuaHao.xuanzeyisheng);
            //Thread.sleep(1000);
            //选择耳鼻喉科
            Action.click(GuaHao.erbihouke);
            //选择妇科
            //Action.click(GuaHao.fuke);
            //选择医生
            //Action.click(By.xpath("/html/body/div[2]/div[1]/div[1]/ul/li[6]"));

            //输入姓名
            Action.sendText(GuaHao.name,JxlFun.readText(exclePath,"Sheet1",i,1));
            //点击性别
            Action.click(GuaHao.gender);
            //点击女或男
            if ((RanDom.randomSix() % 2) == 1) {
                Action.click(GuaHao.woman);
            } else {
                Action.click(GuaHao.man);
            }
            //输入年龄
            Action.sendText(GuaHao.birthday, JxlFun.readText(exclePath,"Sheet1",i,3));
            //输入手机号
            String tel="139"+ RanDom.randomTel(9);
            Action.sendText(GuaHao.tel,tel);
//            String telText= Action.getText(GuaHao.tel);
//            while (telText.length()!=11){
//                String tel2="139"+ RanDom.randomTel(8);
//                Action.sendText(GuaHao.tel,tel2);
//            }
            //输入身份证号
            //Action.sendText(GuaHao.shenfenzheng,"110101199003079016");

            for (int j = 6; j < JxlFun.readRows(exclePath,"Sheet4")+1;j++) {
                //选择挂号日期
                Action.click(By.xpath(JxlFun.readText(exclePath, "Sheet4", j, 1)));
                //点击收款
                Action.click(GuaHao.shoukuan);
                Thread.sleep(2000);
                //判断"确定"按钮是否存在 (此处待优化)
                if(Action.isDisplay(GuaHao.quedingshoukuan)) {
                    //点击确定
                    Action.click(GuaHao.quedingshoukuan);
                    //点击关闭
                    Action.click(GuaHao.guanbi);
                    break;
                }
            }
            //刷新页面
            Action.refresh();
        }
    }





//    @Test
//    public void find(){
//        List<WebElement> list= elementFind.findElements(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div[1]/div[1]/div/ul/div"));
//        for (int i = 0; i < list.size(); i++) {
//            String text=list.get(i).getText();
//            System.out.println(text);
//        }
//    }
//    @Test
//    public void find2(){
//        List<WebElement> list2=elementFind.findElements(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div[1]/div[1]/div/ul/div[1]/li/ul/div"));
//        for (int i = 0; i < list2.size(); i++) {
//            String text=list2.get(i).getText();
//            System.out.println(text);
//        }
//    }



}




//*[@id="pane-scheduling"]/div/div/div[2]/div[3]/table/tbody/tr[1]/td[7]/div/span
//*[@id="pane-scheduling"]/div/div/div[2]/div[3]/table/tbody/tr[2]/td[7]