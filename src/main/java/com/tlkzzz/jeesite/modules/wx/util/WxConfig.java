package com.tlkzzz.jeesite.modules.wx.util;

/**
 * Created by Administrator on 2017/7/28.
 */
public class WxConfig {

    //公众号id
    public static final String AppID="wx4ac0452ec50187c7";

    //公众号密钥
    public static final String AppSecret="7639a52231050886e150d0c53f33a7cd";

    //商户id
    public static final String Mch_id ="1486380092";

    //商户子账户
    public static final String Sub_mch_id ="";

    //商户key
    public static final String key ="14863800921486380092148638009200";

    //ip
    public static final String IP ="39.108.194.82";


    //证书url
    public static final String ZSURL ="C:/Users/Administrator/Desktop/cert/apiclient_cert.p12";



    //现金红包URL
    public static final String XJHBURL="https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";


    //获取acctoken回掉//http://wx.tlkzzz.com/a/wx/wxuser/getAccessToken
    public static final String ACCTOKENRUL="http%3a%2f%2fwx.tlkzzz.com%2fa%2fwx%2fwxuser%2fgetAccessToken";


    //获取用户信息
    public static final String GETUSERURL="https://api.weixin.qq.com/sns/userinfo?access_token=";

    //创建菜单
    public static final String CREATMENU="https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=";

    //支付接口
    public static final String PAYURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    //支付回掉
    public static final String Notify_url="http://wx.tlkzzz.com/a/wx/wxpay/notifyUrl";








}
