package page.shouyinqu;

import org.openqa.selenium.By;

public class GuaHao {
    //选择科室
    public static By xuanzekeshi= By.xpath("//*[@id=\"docRegistered\"]/div[1]/div/div[2]/div[1]/div/input");
    //内科
    public static By neike= By.xpath("/html/body/div[3]/div[1]/div[1]/ul/li");
    //耳鼻喉科
    public static By erbihouke=By.xpath("/html/body/div[2]/div[1]/div[1]/ul/li[3]");
    //妇科
    public static By fuke=By.xpath("/html/body/div[3]/div[1]/div[1]/ul/li[2]");
    //选择医生
    public static By xuanzeyisheng= By.xpath("//*[@id=\"docRegistered\"]/div[1]/div/div[2]/div[2]/div[1]/input");
    //李鹏飞
    public static By houzhiyuan= By.xpath("/html/body/div[4]/div[1]/div[1]/ul/li[6]");
    //查看医生排班
    public static By chakanyishengpaiban= By.xpath("//*[@id=\"docRegistered\"]/div[1]/div/div[2]/div[3]");
        //医生排班
        public static By yishengpaiban=By.xpath("//*[@id=\"doctorsScheduling\"]/div[1]/div/div[1]/span");
        //确定
        public static By queding=By.xpath("//*[@id=\"doctorsScheduling\"]/div[1]/div/div[3]/span/button[1]");
        //取消
        public static By quxiao=By.xpath("//*[@id=\"doctorsScheduling\"]/div[1]/div/div[3]/span/button[2]");
    //姓名
    public static By name= By.xpath("//*[@id=\"docRegistered\"]/div[1]/div/div[3]/div[2]/form/div[1]/div/div/div/div[1]/input");
    //性别
    public static By gender= By.xpath("//*[@id=\"docRegistered\"]/div[1]/div/div[3]/div[2]/form/div[2]/div/div/div/div[1]");
        //男
        public static By man=By.xpath("/html/body/div[3]/div[1]/div[1]/ul/li[1]");
        //女
        public static By woman=By.xpath("/html/body/div[3]/div[1]/div[1]/ul/li[2]");
    //出生年月
    public static By birthday= By.xpath("//*[@id=\"docRegistered\"]/div[1]/div/div[3]/div[2]/form/div[3]/div[2]/div/div/input");
    //手机号码
    public static By tel= By.xpath("//*[@id=\"docRegistered\"]/div[1]/div/div[3]/div[2]/form/div[4]/div/div/div[1]/input");
    //身份证号码
    public static By shenfenzheng= By.xpath("//*[@id=\"docRegistered\"]/div[1]/div/div[3]/div[2]/form/div[5]/div/div/div/input");
    //收款
    public static By shoukuan= By.xpath("//*[@id=\"docRegistered\"]/div[1]/div/div[4]/button");
        //收款明细
        public static By shoukuanmingxi=By.xpath("//*[@id=\"moneyConfirm\"]/div[1]/div/div[2]/div[4]");
        //收款确定
        public static By quedingshoukuan=By.xpath("//*[@id=\"moneyConfirm\"]/div[1]/div/div[3]/span/button[1]");
        //收款取消
        public static By quxiaoshoukuan=By.xpath("//*[@id=\"moneyConfirm\"]/div[1]/div/div[3]/span/button[2]");
        //关闭
        public static By guanbi=By.xpath("//*[@id=\"moneySuccess\"]/div[1]/div/div[3]/span/button");
    //挂号日期
    public static By guahaoriqi= By.xpath("//*[@id=\"docRegistered\"]/div[1]/div/div[3]/div[1]/div/div[3]/div/div/div[1]/div[2]/div[1]");
    //上下周
    public static By shangxiazhou= By.xpath("//*[@id=\"docRegistered\"]/div[1]/div/div[3]/div[1]/div/div[3]/button");
    //未选时间,提示信息
    public static By tishi=By.xpath("/html/body/div[3]");

}
