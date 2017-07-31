package com.tlkzzz.jeesite.modules.wx.web;

import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.modules.wx.entity.WeixinUserInfo;
import com.tlkzzz.jeesite.modules.wx.util.WeiXinUtil;
import com.tlkzzz.jeesite.modules.wx.util.WxConfig;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/25.
 */
@Controller
@RequestMapping(value = "${adminPath}/wx/wxuser")
public class WxUserController extends BaseController {
    public static final String AppID = "wx4ac0452ec50187c7";
    public static final String AppSecret = "7639a52231050886e150d0c53f33a7cd";


    //微信授权登录传入code
    @RequestMapping(value = "toCode")    //实际访问的url地址
    public String toCode() {
        //http://wx.tlkzzz.com/a/wx/wxuser/getAccessToken
        String resultUrl =WxConfig.ACCTOKENRUL;
//       String url =  "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxc863f9e26a0576e3&redirect_uri=http%3a%2f%2fwx.tlkzzz.com%2fhome%2fTest%2fgetAccessToken&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
        //用户授权，获取code
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + WxConfig.AppID + ""
                + "&redirect_uri=" + resultUrl + ""
                + "&response_type=code"
                + "&scope=snsapi_userinfo"
                + "&state=123#wechat_redirect";
        //forward redirect

        return "redirect:" + url + "";
    }

    //code获取accesstoken
    @RequestMapping(value = "getAccessToken")
    public String getAccessToken(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        System.out.println("session中获取第一次的access——tooken：" + session.getAttribute("access_token"));
        //获取code
        String code = request.getParameter("code");
        String url = null;
        if (code != null) {
            url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
                    + "appid=" + WxConfig.AppID + ""
                    + "&secret=" + WxConfig.AppSecret + ""
                    + "&code=" + code + ""
                    + "&grant_type=authorization_code";
            JSONObject jsonObject = null;
            try {
//                if(session.getAttribute("access_token")==null){//token已经失效或者未获取
                System.out.println("----------------token已经失效或者未获取");
                jsonObject = WeiXinUtil.doGetStr(url);
                String access_token = jsonObject.getString("access_token");
                String openid = jsonObject.getString("openid");
                System.out.println(openid);
                session.setMaxInactiveInterval(110 * 60);
                session.setAttribute("access_token", access_token);
                session.setAttribute("openid", openid);
                WeixinUserInfo snsUserInfo = getUserInfo(access_token, openid);
//                }else{//token 已存在
//                    System.out.println("----------------token已经存在session中");
//                    String t = session.getAttribute("access_token").toString();
//                    String o = session.getAttribute("openid").toString();
//                    WeixinUserInfo snsUserInfo= getUserInfo(t,o);
//                }
                System.out.println("session中获取的access——tooken：" + session.getAttribute("access_token"));
                return "modules/wx/success";
            } catch (IOException e) {
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                System.out.print("获取用户信息失败 errcode:{} errmsg:{}" + errorCode + "," + errorMsg);
                e.printStackTrace();
            }
        } else {
            System.out.print("获取CODE失败！");
        }


        return "modules/wx/err";    //返回Views文件夹下的success.jsp页面
    }


    /**
     * 获取用户信息
     *
     * @return
     */
    public WeixinUserInfo getUserInfo(String ACCESS_TOKEN, String OPENID) {
//             "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN"
        String url =WxConfig.GETUSERURL + ACCESS_TOKEN +
                "&openid=" + OPENID +
                "&lang=zh_CN";
        JSONObject jsonObject = null;
        WeixinUserInfo snsUserInfo = null;
        try {
            jsonObject = WeiXinUtil.doGetStr(url);
            snsUserInfo = new WeixinUserInfo();
            // 用户的标识
            snsUserInfo.setOpenId(jsonObject.getString("openid"));
            // 昵称
            snsUserInfo.setNickname(jsonObject.getString("nickname"));
            // 性别（1是男性，2是女性，0是未知）
            snsUserInfo.setSex(jsonObject.getInt("sex"));
            // 用户所在国家
            snsUserInfo.setCountry(jsonObject.getString("country"));
            // 用户所在省份
            snsUserInfo.setProvince(jsonObject.getString("province"));
            // 用户所在城市
            snsUserInfo.setCity(jsonObject.getString("city"));
            // 用户头像
            snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
        } catch (IOException e) {
            int errorCode = jsonObject.getInt("errcode");
            String errorMsg = jsonObject.getString("errmsg");
            System.out.print("获取用户信息失败 errcode:{} errmsg:{}" + errorCode + "," + errorMsg);
            e.printStackTrace();
        }
        return snsUserInfo;    //返回Views文件夹下的success.jsp页面
    }



}