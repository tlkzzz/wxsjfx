package com.tlkzzz.jeesite.modules.wx.util;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DecriptUtil {

    /**
     * 微信支付拼接字符串
     * @param map
     * @return
     */
    public static String Str(Map<Object,String> map){
        StringBuffer sign = new StringBuffer();
        sign.append("appid=").append(map.get("appid"));
        sign.append("&mch_id=").append(map.get("mch_id"));
        sign.append("&out_trade_no=").append(map.get("out_trade_no"));
        sign.append("&sub_mch_id=").append(map.get("sub_mch_id"));
        sign.append("&openid=").append(map.get("openid"));
        sign.append("&trade_type=").append(map.get("trade_type"));
        sign.append("&nonce_str=").append(map.get("nonce_str"));
        sign.append("&total_fee=").append(map.get("total_fee"));
        sign.append("&notify_url=").append(map.get("notify_url"));
        sign.append("&body=").append(map.get("body"));
        sign.append("&spbill_create_ip=").append(map.get("spbill_create_ip"));
        sign.append("&key=").append(map.get("key"));

       return sign.toString();
    }


    /**
     * 微信支付拼接xml
     * @param sign
     * @param map
     * @return
     */
    public static String xmls(String sign , Map<Object,String> map){
        String xml="<xml>"+
                "<appid>"+map.get("appid")+"</appid>"+
                "<mch_id>"+map.get("mch_id")+"</mch_id>"+
                "<nonce_str>"+map.get("nonce_str")+"</nonce_str>"+
                "<sign>"+sign+"</sign>"+
                "<sub_mch_id>"+map.get("sub_mch_id")+"</sub_mch_id>"+
                "<trade_type>"+map.get("trade_type")+"</trade_type>"+
                "<body><![CDATA["+map.get("body")+"]]></body>"+
                "<out_trade_no>"+map.get("out_trade_no")+"</out_trade_no>"+
                "<total_fee>"+map.get("total_fee")+""+"</total_fee>"+
                "<spbill_create_ip>"+map.get("spbill_create_ip")+"</spbill_create_ip>"+
                "<notify_url>"+map.get("notify_url")+"</notify_url>"+
                "<openid>"+map.get("openid")+"</openid>"+
                "</xml>";
        return xml;
    }


    /**
     * 生成sign MD5签名
     * @param sourceStr
     * @return
     */
    public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes("UTF-8"));
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
            //  System.out.println("MD5(" + sourceStr + ",32) = " + result);
            // System.out.println("MD5(" + sourceStr + ",16) = " + buf.toString().substring(8, 24));
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return result;
    }

    /**
     * 生成时间随机数
     * @return
     */
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
    public static Map<String, String> readStringXmlOut(String xml) {
        Map<String, String> map=new HashMap<String, String>();;
        Document doc = null;
        int num=0;
        try {

            doc = DocumentHelper.parseText(xml); //
            Element employees=doc.getRootElement();
//	        	for(Iterator i = employees.elementIterator(); i.hasNext();){
//		        	Element employee = (Element) i.next();
            for(Iterator j = employees.elementIterator(); j.hasNext();){
                Element node=(Element) j.next();
//			        	System.out.println(node.getName()+":"+node.getText());
                if(map.size()>0 && null!=map.get(node.getName())){
                    map.put(node.getName()+String.valueOf(num), node.getText());
                    System.out.println(node.getName()+String.valueOf(num)+":"+node.getText());
                }else{
                    map.put(node.getName(), node.getText());
                    System.out.println(node.getName()+":"+node.getText());
                }
//		        	}
                num++;
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


}