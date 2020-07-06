package com.lpf.checkOut;

import com.lpf.driver.Action;
import com.lpf.driver.PageCheckOutUtil;
import com.lpf.driver.elementFind;
import com.lpf.driver.login;
import com.lpf.driver.tools.JxlFun;
import jxl.read.biff.BiffException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.caiwugaunli.*;
import page.chuangkouqu.*;
import page.chufangguanli.ChuFangJiLu;
import page.chufangguanli.MuBanWeiHu;
import page.firstPage;
import page.kucunguanli.*;
import page.selectHospital;
import page.shouyinqu.*;
import page.xitongshezhi.*;
import page.yishengqu.*;
import page.yunyingguanli.JingYingFenXi;
import page.yunyingguanli.YongHuYunYing;

import java.io.IOException;

//页面跳转检验
public class pageCheckOut {
    String path=System.getProperty("user.dir")+"\\TestExcle\\登录.xls";
    //登录成功后页面跳转检验
    @BeforeTest
    public void loginPage() throws InterruptedException, IOException, BiffException {
        login.loginAction("chrome","https://testsaas.qingxiaoguo.com", JxlFun.readText(path,"Sheet1",2,1),JxlFun.readText(path,"Sheet1",2,2));
    }
    //关闭浏览器
    @AfterTest
    public void closed() throws InterruptedException {
        Action.closed();
    }
    //收银区页面跳转校验
    @Test
    public void ShouYinQuCheckOut() throws InterruptedException {
        //点击医生区,查看下拉框是否展开
        boolean panduan=elementFind.findElement(firstPage.gailan).isDisplayed();
        if(panduan) {
            //点击概览,查看并校验页面跳转
            PageCheckOutUtil.checkout("概览",firstPage.gailan, GaiLan.yuyueguahao,"预约挂号");
        }else{
            Action.click(firstPage.shouyinqu);
            //点击概览,查看并校验页面跳转
            PageCheckOutUtil.checkout("概览",firstPage.gailan, GaiLan.yuyueguahao,"预约挂号");
        }
        //点击挂号,查看并校验页面跳转
        PageCheckOutUtil.checkout("挂号",firstPage.guahao, GuaHao.chakanyishengpaiban,"查看医生排班>");
        //点击收款,查看并校验页面跳转
        PageCheckOutUtil.checkout("收款",firstPage.shoukuan, ShouKuan.shoukuan,"收款");
        //点击退款,查看并校验页面跳转
        PageCheckOutUtil.checkout("退款",firstPage.tuikuan, TuiKuan.tuikuanTital,"退款");
        //点击费用,查看并校验页面跳转
        PageCheckOutUtil.checkout("费用",firstPage.feiyong, FeiYong.rijie,"日结");

    }
    //医生区页面校验
    @Test
    public void YiShengQuCheckOut() throws InterruptedException {
        //点击医生区,查看下拉框是否展开
        boolean panduan=elementFind.findElement(firstPage.jinrijiuzhen).isDisplayed();
        if(panduan) {
            //点击今日就诊,查看并校验页面跳转
            PageCheckOutUtil.checkout("今日就诊",firstPage.jinrijiuzhen, JinRiJiuZhen.kuaisujiuzhen,"快速就诊");
        }else{
            Action.click(firstPage.yishengqu);
            //点击今日就诊,查看并校验页面跳转
            PageCheckOutUtil.checkout("今日就诊",firstPage.jinrijiuzhen, JinRiJiuZhen.kuaisujiuzhen,"快速就诊");
        }
        //点击快速就诊,查看并校验页面跳转
        PageCheckOutUtil.checkout("快速就诊", JinRiJiuZhen.kuaisujiuzhen, KuaiSuJiuZhen.wanchengjiuzhen,"完成就诊");
        //点击电子病历,查看并校验页面跳转
        PageCheckOutUtil.checkout("电子病历",KuaiSuJiuZhen.dianzibingli, DianZiBingLi.jibenxinxi,"基本信息");
        //点击徇证医学,查看并校验页面跳转
        PageCheckOutUtil.checkout("徇证医学",firstPage.xunzhengyixue, XunZhengYixue.yongyaozhilan,"用药指南");
        //点击健康档案,查看并校验页面跳转
        PageCheckOutUtil.checkout("健康档案",firstPage.jiankangdangan, JianKangDangAn.jiankangdangan,"健康档案");

    }

    //窗口区页面校验
    @Test
    public void chuangkouquPageCheckOut() throws InterruptedException {
        boolean panduan=elementFind.findElement(firstPage.dingdan).isDisplayed();
        if(panduan) {
            //点击订单,查看并校验页面跳转
            PageCheckOutUtil.checkout("订单",firstPage.dingdan, DingDan.tuikuandingdan,"退款订单>");
        }else{
            Action.click(firstPage.chuangkouqu);
            //点击订单,查看并校验页面跳转
            PageCheckOutUtil.checkout("订单",firstPage.dingdan, DingDan.tuikuandingdan,"退款订单>");
        }
        //点击零售,查看并校验页面跳转
        PageCheckOutUtil.checkout("零售",firstPage.lingshou, LingShou.xinkaidingdan,"新开订单");
        //点击发药,查看并校验页面跳转
        PageCheckOutUtil.checkout("发药",firstPage.fayao, FaYao.dingdanhao,"订单号");
        //点击针剂,查看并校验页面跳转
        PageCheckOutUtil.checkout("针剂",firstPage.zhenji, ZhenJi.zhenji,"针剂");
        //点击检查,查看并校验页面跳转
        PageCheckOutUtil.checkout("检查",firstPage.jiancha, JianCha.jiancha,"检查");
        //点击检验,查看并校验页面跳转
        PageCheckOutUtil.checkout("检验",firstPage.jianyan,JianYan.jianyan,"检验");
        //点击理疗,查看并校验页面跳转
        PageCheckOutUtil.checkout("理疗",firstPage.liliao,LiLiao.liliao,"理疗");
    }
    //库存管理页面校验
    @Test
    public void KuCunGuanLiPageCHeckOut() throws InterruptedException {
        boolean panduan=elementFind.findElement(firstPage.shouyegailan).isDisplayed();
        if(panduan) {
            //点击订单,查看并校验页面跳转
            PageCheckOutUtil.checkout("首页概览",firstPage.shouyegailan, ShouYeGaiLan.jinrirukudan,"今日入库单");
        }else{
            Action.click(firstPage.kucunguanli);
            //点击订单,查看并校验页面跳转
            PageCheckOutUtil.checkout("首页概览",firstPage.shouyegailan, ShouYeGaiLan.jinrirukudan,"今日入库单");
        }
        //点击商品入库,查看并校验页面跳转
        PageCheckOutUtil.checkout("商品入库",firstPage.shangpinruku, ShangPinRuKu.shangpinruku,"商品入库");
        //点击商品出库,查看并校验页面跳转
        PageCheckOutUtil.checkout("商品出库",firstPage.shangpinchuku, ShangPinChuKu.shangpinchuku,"商品出库");
        //库存查询,查看并校验页面跳转
        PageCheckOutUtil.checkout("库存查询",firstPage.kucunchaxun, KuCunChaXun.shangpinkucun,"商品库存");
        //库存盘点,查看并校验页面跳转
        PageCheckOutUtil.checkout("库存盘点",firstPage.kucunpandian, KuCunPanDian.pandiandanbianhao,"盘点单编号");
        //商品预警,查看并校验页面跳转
        PageCheckOutUtil.checkout("商品预警",firstPage.shangpinyujing,ShangPinYuJing.youxiaoqiyujing,"有效期预警");
        //商品报损,查看并校验页面跳转
        PageCheckOutUtil.checkout("商品报损",firstPage.shangpinbaosun,ShangPinBaoSun.shangpinbaosun,"商品报损");
        //商品调价,查看并校验页面跳转
        PageCheckOutUtil.checkout("商品调价",firstPage.shangpintiaojia,ShangPinTiaoJia.shangpintiaojia,"商品调价");
        //调价统计,查看并校验页面跳转
        PageCheckOutUtil.checkout("进销存统计",firstPage.jinxiaocuntongji,JinXiaoCun.tiaojiatongji,"调价统计");
    }
    @Test
    public void CaiWuGuanLiPageCheckOut() throws InterruptedException {
        boolean panduan=elementFind.findElement(firstPage.shourutongji).isDisplayed();
        if(panduan) {
            //点击收入统计,查看并校验页面跳转
            PageCheckOutUtil.checkout("收入统计",firstPage.shourutongji, ShouRuTongJi.zhifubaoxianxia,"支付宝线下");
        }else{
            Action.click(firstPage.caiwuguanli);
            //点击收入统计,查看并校验页面跳转
            PageCheckOutUtil.checkout("收入统计",firstPage.shourutongji, ShouRuTongJi.zhifubaoxianxia,"支付宝线下");
        }
        //点击支出统计,查看并校验页面跳转
        PageCheckOutUtil.checkout("支出统计",firstPage.zhichutongji, ZhiChuTongJi.tichengtongji,"提成统计");
        //点击费用明细,查看并校验页面跳转
        PageCheckOutUtil.checkout("费用明细",firstPage.feiyongmingxi, FeiYongMingXi.shoufeiriqi,"费用日期");
        //点击财务报表,查看并校验页面跳转
        PageCheckOutUtil.checkout("财务报表",firstPage.caiwubaobiao, CaiWuBaoBiao.caiwubaobiao,"财务报表");
        //点击日结审核,查看并校验页面跳转
        PageCheckOutUtil.checkout("日结审核",firstPage.rijieshenhe, RiJieShenHe.rijieshenhe,"日结审核");
    }

    @Test
    public void ChuFangGuanLiPageCheckOut() throws InterruptedException {
        boolean panduan=elementFind.findElement(firstPage.mobanweihu).isDisplayed();
        if(panduan){
            //点击模板维护,查看并校验页面跳转
            PageCheckOutUtil.checkout("模板维护",firstPage.mobanweihu, MuBanWeiHu.jingfangmuban,"经方模板");
        }else{
            //点击处方管理
            Action.click(firstPage.chufangguanli);
            //点击模板维护,查看并校验页面跳转
            PageCheckOutUtil.checkout("模板维护",firstPage.mobanweihu, MuBanWeiHu.jingfangmuban,"经方模板");
        }
        //点击处方记录,查看并校验页面跳转
        PageCheckOutUtil.checkout("处方记录",firstPage.chufangjilu, ChuFangJiLu.chufangjilu,"处方记录");
    }

    @Test
    public void YongHuYunYingPageCheckOut() throws InterruptedException {
        boolean panduan=elementFind.findElement(firstPage.yonghuyunying).isDisplayed();
        if(panduan){
            //点击用户运营,查看并校验页面跳转
            PageCheckOutUtil.checkout("用户运营",firstPage.yonghuyunying, YongHuYunYing.yonghutongji,"用户统计");
        }else{
            //点击运营管理
            Action.click(firstPage.yunyingguanli);
            //点击用户运营,查看并校验页面跳转
            PageCheckOutUtil.checkout("用户运营",firstPage.yonghuyunying, YongHuYunYing.yonghutongji,"用户统计");
        }
        //点击经营分析
        PageCheckOutUtil.checkout("经营分析",firstPage.jingyingfenxi, JingYingFenXi.xiaoshoufenxi,"销售分析");
    }

    @Test
    public void XiTongSheZhiPageCheckOut() throws InterruptedException {
        boolean panduan=elementFind.findElement(firstPage.zhensuoguanli).isDisplayed();
        if(panduan){
            //点击诊所设置,查看并校验页面跳转
            PageCheckOutUtil.checkout("诊所管理",firstPage.zhensuoguanli, ZhenSuoGuanLi.shebeiguanli,"设备管理");
        }else {
            //点击系统设置
            Action.click(firstPage.xitongshezhi);
            //点击诊所设置,查看并校验页面跳转
            PageCheckOutUtil.checkout("诊所管理",firstPage.zhensuoguanli, ZhenSuoGuanLi.shebeiguanli,"设备管理");
        }
        //点击字典库维护,查看并校验页面跳转
        PageCheckOutUtil.checkout("字典库维护",firstPage.zidiankuweihu, ZiDianKuWeiHu.ziduankuweihu,"字典库维护");
        //点击信息维护,查看并校验页面跳转
        PageCheckOutUtil.checkout("信息维护",firstPage.xinxiweihu, XinXiWeiHu.cangkuxinxi,"仓库信息");
        //点击费用设置,查看并校验页面跳转
        PageCheckOutUtil.checkout("费用设置",firstPage.feiyongshezhi, FeiYongSheZhi.fujiafei,"附加费");
        //点击基础设置,查看并校验页面跳转
        PageCheckOutUtil.checkout("基础设置",firstPage.jichushezhi, JiChuSheZhi.jichushezhi,"基础设置");
    }

}
