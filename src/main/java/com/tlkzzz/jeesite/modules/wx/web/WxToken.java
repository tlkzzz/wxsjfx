package com.tlkzzz.jeesite.modules.wx.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/27.
 */
@Controller
@RequestMapping(value = "${adminPath}/wx")
public class WxToken  extends HttpServlet {
    private static final Logger logger = Logger.getLogger(WxToken.class);



    @RequestMapping(value = "token")
    public void token(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp，nonce参数
    String signature = request.getParameter("signature");
    //时间戳
    String timestamp = request.getParameter("timestamp");
    //随机数
    String nonce = request.getParameter("nonce");
    //随机字符串
    String echostr = request.getParameter("echostr");

//    if (SignUtil.checkSignature(signature, timestamp, nonce)) {
        logger.info("[signature: "+signature + "]<-->[timestamp: "+ timestamp+"]<-->[nonce: "+nonce+"]<-->[echostr: "+echostr+"]");
        response.getOutputStream().println(echostr);
//    }


}

}
