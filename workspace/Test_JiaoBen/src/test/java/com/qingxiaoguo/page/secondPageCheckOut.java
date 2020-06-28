package com.qingxiaoguo.page;

import lpf.driver.Action;
import lpf.driver.PageCheckOutUtil;
import lpf.driver.SwitchUtil;
import lpf.driver.login;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.chuangkouqu.*;
import page.firstPage;
import page.selectHospital;
import page.shouyinqu.*;
import page.yishengqu.DianZiBingLi;
import page.yishengqu.JianKangDangAn;
import page.yishengqu.JinRiJiuZhen;

import java.awt.*;
import java.lang.reflect.Method;

public class secondPageCheckOut {
    
    //登录
    @BeforeTest
    public void loginPage() throws InterruptedException {
        login.loginAction("chrome","https://testsaas.qingxiaoguo.com","15110403429","123456");
        Action.click(selectHospital.zhensuo1);
    }
    //关闭浏览器
    @AfterTest
    public void closed() throws InterruptedException {
        Action.closed();
    }
    @BeforeMethod
    public void clickElement(Method method) throws InterruptedException {
        String methodName = method.getName();
        switch (methodName){
            case "gailan":
                SwitchUtil.clickElement(firstPage.shouyinqu, firstPage.gailan);
                break;
            case "guahao":
                SwitchUtil.clickElement(firstPage.shouyinqu, firstPage.guahao);
                break;
            case "shoukuan":
                SwitchUtil.clickElement(firstPage.shouyinqu, firstPage.shoukuan);
                break;
            case "tuikuan":
                SwitchUtil.clickElement(firstPage.shouyinqu, firstPage.tuikuan);
                break;
            case "feiyong":
                SwitchUtil.clickElement(firstPage.shouyinqu, firstPage.feiyong);
                break;
            case "jinrijiuzhen":
                SwitchUtil.clickElement(firstPage.yishengqu, firstPage.jinrijiuzhen);
                break;
            case "xunzhengyixue":
                SwitchUtil.clickElement(firstPage.yishengqu, firstPage.xunzhengyixue);
                break;
            case "jiankangdangan":
                SwitchUtil.clickElement(firstPage.yishengqu, firstPage.jiankangdangan);
                break;
            case "dingdan":
                SwitchUtil.clickElement(firstPage.chuangkouqu, firstPage.dingdan);
                break;
            case "lingshou":
                SwitchUtil.clickElement(firstPage.chuangkouqu, firstPage.lingshou);
                break;
            case "fayao1":
                SwitchUtil.clickElement(firstPage.chuangkouqu, firstPage.fayao);
                break;
            case "fayao2":
                SwitchUtil.clickElement(firstPage.chuangkouqu, firstPage.fayao);
                break;
            case "zhenji":
                SwitchUtil.clickElement(firstPage.chuangkouqu, firstPage.zhenji);
                break;
            case "jiancha":
                SwitchUtil.clickElement(firstPage.chuangkouqu, firstPage.jiancha);
                break;
            case "jianyan":
                SwitchUtil.clickElement(firstPage.chuangkouqu, firstPage.jianyan);
                break;
            case "liliao":
                SwitchUtil.clickElement(firstPage.chuangkouqu, firstPage.liliao);
                break;
            case "shouyegailan":
                SwitchUtil.clickElement(firstPage.kucunguanli, firstPage.shouyegailan);
                break;
            case "shangpinruku":
                SwitchUtil.clickElement(firstPage.kucunguanli, firstPage.shangpinruku);
                break;
            case "shangpinchuku":
                SwitchUtil.clickElement(firstPage.kucunguanli, firstPage.shangpinchuku);
                break;
            case "kucunchaxun":
                SwitchUtil.clickElement(firstPage.kucunguanli, firstPage.kucunchaxun);
                break;
            case "kucunpandian":
                SwitchUtil.clickElement(firstPage.kucunguanli, firstPage.kucunpandian);
                break;
            case "shangpinyujing":
                SwitchUtil.clickElement(firstPage.kucunguanli, firstPage.shangpinyujing);
                break;
            case "shangpinbaosun":
                SwitchUtil.clickElement(firstPage.kucunguanli, firstPage.shangpinbaosun);
                break;
            case "shangpintiaojia":
                SwitchUtil.clickElement(firstPage.kucunguanli, firstPage.shangpintiaojia);
                break;
            case "jinxiaocuntongji":
                SwitchUtil.clickElement(firstPage.kucunguanli, firstPage.jinxiaocuntongji);
                break;
            case "shourutongji":
                SwitchUtil.clickElement(firstPage.caiwuguanli, firstPage.shourutongji);
                break;
            case "zhichutongji":
                SwitchUtil.clickElement(firstPage.caiwuguanli, firstPage.zhichutongji);
                break;
            case "feiyongmingxi":
                SwitchUtil.clickElement(firstPage.caiwuguanli, firstPage.feiyongmingxi);
                break;
            case "caiwubaobiao":
                SwitchUtil.clickElement(firstPage.caiwuguanli, firstPage.caiwubaobiao);
                break;
            case "rijieshenhe":
                SwitchUtil.clickElement(firstPage.caiwuguanli, firstPage.rijieshenhe);
                break;
            case "mubanweihu":
                SwitchUtil.clickElement(firstPage.chufangguanli, firstPage.mobanweihu);
                break;
            case "chufangjilu":
                SwitchUtil.clickElement(firstPage.chufangguanli, firstPage.chufangjilu);
                break;
            case "yonghuyunying":
                SwitchUtil.clickElement(firstPage.yunyingguanli, firstPage.yonghuyunying);
                break;
            case "jingyingfenxi":
                SwitchUtil.clickElement(firstPage.yunyingguanli, firstPage.jingyingfenxi);
                break;
            case "zhensuoguanli":
                SwitchUtil.clickElement(firstPage.xitongshezhi, firstPage.zhensuoguanli);
                break;
            case "zidiankuweihu":
                SwitchUtil.clickElement(firstPage.xitongshezhi, firstPage.zidiankuweihu);
                break;
            case "xinxiweihu":
                SwitchUtil.clickElement(firstPage.xitongshezhi, firstPage.xinxiweihu);
                break;
            case "feiyongshezhi":
                SwitchUtil.clickElement(firstPage.xitongshezhi, firstPage.feiyongshezhi);
                break;
            case "jichushezhi":
                SwitchUtil.clickElement(firstPage.xitongshezhi, firstPage.jichushezhi);
                break;
        }
    }
//    @AfterMethod
//    public void refresh(){
//        Action.refresh();
//    }
    @Test
    public void gailan() throws InterruptedException, AWTException {
        //点击预约挂号,查看并校验页面跳转
        PageCheckOutUtil.checkout("预约挂号", GaiLan.yuyueguahao, GuaHao.chakanyishengpaiban,"查看医生排班>");
        Action.click(firstPage.gailan);
        //点击待收款,查看并校验页面跳转
        PageCheckOutUtil.checkout("待收款", GaiLan.daishoukuan, ShouKuan.shoukuan,"收款");
        Action.click(firstPage.gailan);
        //点击待退款,查看并校验页面跳转
        PageCheckOutUtil.checkout("待退款", GaiLan.daituikuan, TuiKuan.tuikuanTital,"退款");
        Action.click(firstPage.gailan);
        //点击挂号编号.查看并校验页面跳转
        PageCheckOutUtil.checkout("挂号编号", GaiLan.dingdanbianhao, GaiLan.nianling,"年龄:");
        Action.click(firstPage.gailan);
        //点击退号
        PageCheckOutUtil.checkout("退号", GaiLan.tuihao, GaiLan.querentuikuan,"确认退款");
        Action.click(GaiLan.quxiao);
    }
    @Test
    public void guahao() throws InterruptedException {
        //点击查看医生排班
        PageCheckOutUtil.checkout("查看医生排班", GuaHao.chakanyishengpaiban, GuaHao.yishengpaiban,"医生排班");
        Action.click(GuaHao.quxiao);
    }
    @Test
    public void shoukuan() throws InterruptedException {
        //点击收款按钮
        PageCheckOutUtil.checkout("收款按钮", ShouKuan.shoukuanButton, ShouKuan.querenshoukuan,"确认收款");
        Action.click(ShouKuan.quxiao);
        //点击收款按钮
        PageCheckOutUtil.checkout("收款按钮", ShouKuan.shoukuanButton, ShouKuan.querenshoukuan,"确认收款");
//        //点击确定
//        PageCheckOutUtil.checkout("确定",ShouKuan.queren, ShouKuan.zhifuchenggong,"支付成功");
//        //点击关闭
//        PageCheckOutUtil.checkout("关闭",ShouKuan.guanbi,ShouKuan.shoukuan,"收款");
        //点击取消
        PageCheckOutUtil.checkout("取消", ShouKuan.quxiao, ShouKuan.shoukuan,"收款");
        //点击订单号
        PageCheckOutUtil.checkout("订单号", ShouKuan.dingdanhao, ShouKuan.danjia,"单价");
        //点击取消
        PageCheckOutUtil.checkout("取消", ShouKuan.dingdanquxiao, ShouKuan.shoukuan,"收款");
        //点击一键收款
        Action.click(ShouKuan.firstdingdan);
        PageCheckOutUtil.checkout("一键收款", ShouKuan.yijianshoukuan, ShouKuan.yingshoujine_yijian,"应收金额");
        //点击取消
        PageCheckOutUtil.checkout("取消", ShouKuan.quxiaozhifu, ShouKuan.shoukuan,"收款");
    }
    @Test
    public void tuikuan() throws InterruptedException {
        //点击退款按钮
        PageCheckOutUtil.checkout("退款", TuiKuan.tuikuanButton, TuiKuan.querentuikuan,"确认退款");
        //点击取消
        PageCheckOutUtil.checkout("退款取消", TuiKuan.quxiao, TuiKuan.tuikuanTital,"退款");
    }
    @Test
    public void feiyong() throws InterruptedException {
        //点击日结
        PageCheckOutUtil.checkout("日结", FeiYong.rijie, FeiYong.xinzeng,"新增");
        //点击新增
        PageCheckOutUtil.checkout("新增", FeiYong.xinzeng, FeiYong.xinzengrijie,"新增日结");
        //点击取消
        PageCheckOutUtil.checkout("取消", FeiYong.quxiao, FeiYong.rijie,"日结");
    }
    @Test
    public void jinrijiuzhen() throws InterruptedException {
        //点击快速就诊
        PageCheckOutUtil.checkout("快速就诊", JinRiJiuZhen.kuaisujiuzhen, DianZiBingLi.jibenxinxi,"基本信息");
    }
    @Test
    public void jiankangdangan() throws InterruptedException {
        //点击第一个卡片
        PageCheckOutUtil.checkout("卡片一", JianKangDangAn.kapian1, JianKangDangAn.jiuzhenjilu,"就诊记录");
    }
    @Test
    public void dingdan() throws InterruptedException {
        //待发药
        PageCheckOutUtil.checkout("待发药", DingDan.daifayao, FaYao.dingdanhao,"订单号");
        Action.click(firstPage.dingdan);
        //待执行
        PageCheckOutUtil.checkout("待执行", DingDan.daizhixing, ZhenJi.zhenji,"针剂");
        Action.click(firstPage.dingdan);
        //待检查
        PageCheckOutUtil.checkout("待检查", DingDan.daijiancha, JianCha.jiancha,"检查");
        Action.click(firstPage.dingdan);
        //待检验
        PageCheckOutUtil.checkout("待检验", DingDan.daijianyan, JianYan.jianyan,"检验");
        Action.click(firstPage.dingdan);
        //待理疗
        PageCheckOutUtil.checkout("待理疗", DingDan.daililiao, LiLiao.liliao,"理疗");
        Action.click(firstPage.dingdan);
        //收款
        PageCheckOutUtil.checkout("收款", DingDan.shoukuan, DingDan.yingshoujine,"应收金额");
        //取消
        PageCheckOutUtil.checkout("取消", DingDan.quxiao, DingDan.shoukuan,"收款");
//        //确定
//        PageCheckOutUtil.checkout("支付确定",DingDan.queren,DingDan.weixinzhifu,"微信支付");
//        //微信支付
//        PageCheckOutUtil.checkout("微信支付",DingDan.weixinzhifu,DingDan.zhifuchenggong,"支付成功");
//        Action.click(DingDan.guanbi);
        //一键收款
        Action.click(DingDan.first);
//        Action.click(DingDan.second);
        PageCheckOutUtil.checkout("一键收款", DingDan.yijianshoukuan, DingDan.yingshoujine_yijian,"应收金额");
//        //点击确定
//        PageCheckOutUtil.checkout("一键支付确定",DingDan.queren_yijian,DingDan.weixinzhifu_yijian,"微信支付");
//        //点击微信支付
//        PageCheckOutUtil.checkout("微信支付",DingDan.weixinzhifu_yijian,DingDan.zhifuchenggong_yijian,"支付成功");
//        //点击关闭
//        PageCheckOutUtil.checkout("关闭",DingDan.guanbi_yijian,DingDan.daishoukuan,"待收款");
        //点击取消
        PageCheckOutUtil.checkout("取消", DingDan.quxiao_yijian, DingDan.shoukuan,"收款");
    }
    @Test
    public void lingshou() throws InterruptedException {
        //点击商品
        PageCheckOutUtil.checkout("商品", LingShou.shangpin, LingShou.shangpinmingcheng,"商品名称");
        //添加商品后,点击收款
        Action.click(LingShou.shangpinjiaru);
        PageCheckOutUtil.checkout("收款", LingShou.shoukuan, LingShou.shoukuanmingxi,"收款明细");
        //点击取消
        PageCheckOutUtil.checkout("取消", LingShou.quxiao, LingShou.xinkaidingdan,"新开订单");
        //添加商品后,点击清空
        Action.click(LingShou.shangpinjiaru);
        PageCheckOutUtil.checkout("清空", LingShou.qingkong, LingShou.zanwuneirong,"暂无内容");
        //点击项目
        PageCheckOutUtil.checkout("项目", LingShou.xiangmu, LingShou.chanpinmingcheng,"产品名称");
        //点击挂单
        PageCheckOutUtil.checkout("挂单", LingShou.guadan, LingShou.qingshuruxingming,"请输入姓名");
        PageCheckOutUtil.checkout("挂单", LingShou.guadan, LingShou.qingshurushoujihao,"请输入手机号");
    }
    @Test
    public void fayao1() throws InterruptedException {
        //点击发药
        PageCheckOutUtil.checkout("发药", FaYao.fayao_xiayao, FaYao.xuanzefafang,"选择发放");
        //点击取消
        PageCheckOutUtil.checkout("取消", FaYao.quxiao_quxiao, FaYao.yifafang_xiyao,"已发放");
        //点击中药
        PageCheckOutUtil.checkout("中药", FaYao.zhongyao, FaYao.fayao_zhongyao,"发药");
        //点击发药
        PageCheckOutUtil.checkout("发药", FaYao.fayao_zhongyao, FaYao.tiaopei,"调配：");
        //点击取消
        PageCheckOutUtil.checkout("取消", FaYao.quxiao_zhongyao, FaYao.yifafang_zhongyao,"已发放");
        //点击提交
        Action.click(FaYao.fayao_zhongyao);
        PageCheckOutUtil.checkout("提交", FaYao.tijiao_zhongyao, FaYao.tijiaochenggong,"提交成功");
    }
//    @Test
//    public void fayao2() throws InterruptedException {
//        //点击已发放
//        PageCheckOutUtil.checkout("已发放",FaYao.yifafang_xiyao,FaYao.yaodan_xiyao,"药单");
//        //点击药单
//        PageCheckOutUtil.checkout("药单",FaYao.yaodan_xiyao,FaYao.fafangyaopin,"发放药品");
//        //点击关闭
//        PageCheckOutUtil.checkout("关闭",FaYao.guanbi_xiyao,FaYao.yifafang_xiyao,"已发放");
//        //点击中药,点击已发放
//        Action.click(FaYao.zhongyao);
//        Action.click(FaYao.yifafang_zhongyao);
//        //点击药单
//        PageCheckOutUtil.checkout("药单",FaYao.yaodan_zhongyao,FaYao.shenhe,"审核：");
//        //点击关闭
//        PageCheckOutUtil.checkout("关闭",FaYao.guanbi_zhongyao,FaYao.yifafang_zhongyao,"已发放");
//    }

    @Test
    public void zhenji() throws InterruptedException {
        //点击开始执行
        PageCheckOutUtil.checkout("开始执行", ZhenJi.kaishizhixing, ZhenJi.tijiao,"提交");
        //点击取消
        PageCheckOutUtil.checkout("取消", ZhenJi.quxiao, ZhenJi.daizhixing,"待执行");
    }
//    @Test
//    public void zhenji() throws InterruptedException {
//        //点击已执行
//        PageCheckOutUtil.checkout("已执行",ZhenJi.yizhixing,ZhenJi.zhixingdan,"执行单");
//        //点击执行单
//        PageCheckOutUtil.checkout("执行单",ZhenJi.zhixingdan,ZhenJi.zhixingjilu,"执行记录");
//        //点击关闭
//        PageCheckOutUtil.checkout("关闭",ZhenJi.guanbi1,ZhenJi.daizhixing,"待执行");
//    }
    @Test
    public void jiancha() throws InterruptedException {
        //点击开始执行
        PageCheckOutUtil.checkout("开始执行", JianCha.kaishizhixing, JianCha.quxiao,"取消");
        //点击取消
        PageCheckOutUtil.checkout("取消", JianCha.quxiao, JianCha.kaishizhixing,"开始执行");
        //点击开始执行并提交
        Action.click(JianCha.kaishizhixing);
        PageCheckOutUtil.checkout("提交", JianCha.tijiao, JianCha.kaishizhixing,"开始执行");
    }

    @Test
    public void jianyan() throws InterruptedException {
        //点击开始执行
        PageCheckOutUtil.checkout("开始执行", JianYan.kaishizhixing, JianYan.quxiao,"取消");
        //点击取消
        PageCheckOutUtil.checkout("取消", JianYan.quxiao, JianYan.kaishizhixing,"开始执行");
        //点击开始执行并提交
        Action.click(JianYan.kaishizhixing);
        PageCheckOutUtil.checkout("提交", JianYan.tijiao, JianYan.kaishizhixing,"开始执行");
    }
    @Test
    public void liliao() throws InterruptedException {
        //点击开始执行
        PageCheckOutUtil.checkout("开始执行", LiLiao.kaishizhixing, LiLiao.quxiao,"取消");
        //点击取消
        PageCheckOutUtil.checkout("取消", LiLiao.quxiao, LiLiao.kaishizhixing,"开始执行");
        //点击开始执行并提交
        Action.click(JianYan.kaishizhixing);
        PageCheckOutUtil.checkout("提交", LiLiao.tijiao, LiLiao.kaishizhixing,"开始执行");
    }
}
