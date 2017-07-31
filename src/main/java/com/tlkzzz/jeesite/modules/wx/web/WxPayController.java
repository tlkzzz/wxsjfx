package com.tlkzzz.jeesite.modules.wx.web;

import com.tlkzzz.jeesite.common.utils.IdGen;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.modules.wx.entity.WeixinUserInfo;
import com.tlkzzz.jeesite.modules.wx.util.DecriptUtil;
import com.tlkzzz.jeesite.modules.wx.util.WeiXinUtil;
import com.tlkzzz.jeesite.modules.wx.util.WxConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/25.
 */
@Controller
@RequestMapping(value = "${adminPath}/wx/wxpay")
public class WxPayController extends BaseController {
    //公众号id
    public static final String AppID = "wxc863f9e26a0576e3";
    public static final String AppSecret = "5cebe6117859d77acc961a04ac4a4e90";
    private String url;

    /**
     * 去支付 根据openid获取订单id
     * https://api.mch.weixin.qq.com/pay/unifiedorder
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/toPay")
    public String toPay(HttpServletRequest request, HttpServletResponse response,Model model){

        //后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
        String url = WxConfig.PAYURL;
        /**
         * appid 公众号id
         */
        String appid =WxConfig.AppID;
        /**
         * mch_id  商户号 商品平台中获取1443283402/1486380092
         */
        String  mch_id=WxConfig.Mch_id;

        /**
         * out_trade_no 32订单号   页面传过来的订单号
         */
        String out_trade_no = "515155254565s565s556544522s5s5s5";

        /**
         * openid  获取用户用户标识
         */
        HttpSession session = request.getSession();
        String openid = session.getAttribute("openid").toString();

        /**
         * 生成nonce_str 随机字符串32
         */
        String nonce_str = IdGen.uuid();

        /**
         * total_fee 金额 1元=100分
         */
        String total_fee = "100";

        /**
         * 	String notify_url = ; 支付订单回调方法
         */
        String notify_url = WxConfig.Notify_url;
        /**
         * trade_type  H5类型为MWEB
         */
        String trade_type="MWEB";
        /**
         * body  商品描述
         */
        String body = "支付测试";
        /**
         * sub_mch_id 子商户号
         */
        String sub_mch_id=WxConfig.Sub_mch_id;
        /**
         * spbill_create_ip 终端ip
         */
        String spbill_create_ip =WxConfig.IP;



        /**
         * key  商户平台中获取
         */
        String  key =WxConfig.key;

        Map<Object,String> map =new HashMap<Object,String>();
        map.put("appid",appid);
        map.put("mch_id",mch_id);
        map.put("out_trade_no",out_trade_no);
        map.put("openid",openid);
        map.put("trade_type",trade_type);
        map.put("nonce_str",nonce_str);
        map.put("total_fee",total_fee);
        map.put("notify_url",notify_url);
        map.put("sub_mch_id",sub_mch_id);
        map.put("body",body);
        map.put("spbill_create_ip",spbill_create_ip);
        map.put("key",key);
        //拼接字符串 然后MD5加密
        String str =   DecriptUtil.Str(map);

        /**
         * sign  签名
         */
        String sign = DecriptUtil.MD5(str);
        /**
         * 拼xml格式字符串
         */
        String xmls = DecriptUtil.xmls(sign, map);
        //调用接口返回prepay_id
         String prepay_id = WeiXinUtil.getPayNo(url,xmls);
        try {
            prepay_id =  WeiXinUtil.getPayNo(url,xmls);
            System.out.println("prepay_id:" + prepay_id);
            if(prepay_id.equals("")){
                request.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
                response.sendRedirect("modules/wx/err");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        model.addAttribute("appid", appid);
        model.addAttribute("timeStamp", DecriptUtil.getTimeStamp());//时间戳
        model.addAttribute("nonceStr", nonce_str); //随机字符串
        model.addAttribute("packageValue", "prepay_id="+prepay_id);
        model.addAttribute("sign", sign); //签名

//        model.addAttribute("bizOrderId", orderId);
//        model.addAttribute("orderId", orderId);
//        model.addAttribute("payPrice", total_fee);




        return "modules/wx/jsapi";
    }

    /**
     * 支付回掉方法
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/notifyUrl")
    public String weixinReceive(HttpServletRequest request,HttpServletResponse response, Model model){
        System.out.println("进入支付回掉方法！！！！！！！！！！！！！！！----");
        String xml_review_result = WeiXinUtil.getXmlRequest(request);
        System.out.println("微信支付结果:"+xml_review_result);
        Map resultMap = null;
        try {
            resultMap = WeiXinUtil.doXMLParse(xml_review_result);
            System.out.println("resultMap:"+resultMap);
            if(resultMap != null && resultMap.size() > 0){
                String sign_receive = (String)resultMap.get("sign");
                System.out.println("sign_receive:"+sign_receive);
                resultMap.remove("sign");
                String checkSign = WeiXinUtil.getSign(resultMap,WxConfig.key);
                System.out.println("checkSign:"+checkSign);

                //签名校验成功
                if(checkSign != null && sign_receive != null &&
                        checkSign.equals(sign_receive.trim())){
                    System.out.println("weixin receive check Sign sucess");
                    try{
                        //获得返回结果
                        String return_code = (String)resultMap.get("return_code");

                        if("SUCCESS".equals(return_code)){
                            String out_trade_no = (String)resultMap.get("out_trade_no");
                            System.out.println("weixin pay sucess,out_trade_no:"+out_trade_no);
                            //处理支付成功以后的逻辑，这里是写入相关信息到文本文件里面，如果有订单的处理订单
                            try{
//                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh24:mi:ss");
//                                String content = out_trade_no+"        "+sdf.format(new Date());
//                                String fileUrl = System.getProperty("user.dir") + File.separator+"WebContent" + File.separator + "data" + File.separator + "order.txt";
//                                TxtUtil.writeToTxt(content, fileUrl);
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }else{
                            model.addAttribute("payResult", "0");
                            model.addAttribute("err_code_des", "通信错误");
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    //签名校验失败
                    System.out.println("weixin receive check Sign fail");
                    String checkXml = "<xml><return_code><![CDATA[FAIL]]></return_code>"+
                            "<return_msg><![CDATA[check sign fail]]></return_msg></xml>";
                    WeiXinUtil.getTradeOrder("http://weixin.xinfor.com/wx/notifyUrl", checkXml);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }







}
