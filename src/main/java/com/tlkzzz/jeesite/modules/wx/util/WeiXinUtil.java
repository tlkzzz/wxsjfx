package com.tlkzzz.jeesite.modules.wx.util;

import com.tlkzzz.jeesite.common.utils.IdGen;
import com.tlkzzz.jeesite.modules.wx.web.WxUserController;
import groovy.xml.SAXBuilder;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import javax.net.ssl.TrustManager;



//import org.jdom.input.SAXBuilder;
/**
 * Created by Administrator on 2017-07-11.
 */
public class WeiXinUtil {



    /**
     * 获取token
     * @return
     */
    public static String getToken() {
        String url ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ WxConfig.AppID+"&secret="+WxConfig.AppSecret;

        String  token=null;

        try {
            JSONObject   jsonObject = WeiXinUtil.doGetStr(url);
            token =  jsonObject.getString("access_token");

        } catch (IOException e) {
            e.printStackTrace();
        }


        return token;
    }
    /**
     * get请求
     * @param url
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static JSONObject doGetStr(String url) throws ParseException, IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        org.apache.http.HttpResponse httpResponse = client.execute(httpGet);
        HttpEntity entity = httpResponse.getEntity();
        if(entity != null){
            String result = EntityUtils.toString(entity,"UTF-8");
            jsonObject = JSONObject.fromObject(result);
            System.out.println(jsonObject);
        }
        return jsonObject;
    }
    /**
     * POST请求
     * @param url
     * @param outStr
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static JSONObject doPostStr(String url,String outStr) throws ParseException, IOException{
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);
        JSONObject jsonObject = null;
        httpost.setEntity(new StringEntity(outStr,"UTF-8"));
        HttpResponse response = client.execute(httpost);
        String result = EntityUtils.toString(response.getEntity(),"UTF-8");
        jsonObject = JSONObject.fromObject(result);
        System.out.print(jsonObject);
        return jsonObject;
    }

    /**
     * 支付获取订单号：prepay_id
     * @param url
     * @param xmlParam
     * @return
     */
    public static String getPayNo(String url, String xmlParam) {
        DefaultHttpClient client = new DefaultHttpClient();
        client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS,
                true);
        HttpPost httpost =  new HttpPost(url);
        String prepay_id = "";
        try {
            httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
            HttpResponse response = client.execute(httpost);
            String jsonStr = EntityUtils
                    .toString(response.getEntity(), "UTF-8");
            Map<String, Object> dataMap = new HashMap<String, Object>();

            if (jsonStr.indexOf("FAIL") != -1) {
                return prepay_id;
            }
            Map map = doXMLParse(jsonStr);
            String return_code = (String) map.get("return_code");
            prepay_id = (String) map.get("prepay_id");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return prepay_id;
    }

    /**
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     *
     * @param strxml
     * @return
//     * @throws JDOMException
     * @throws IOException
     */
    public static Map doXMLParse(String strxml) throws Exception {
        if (null == strxml || "".equals(strxml)) {
            return null;
        }

        Map m = new HashMap();
        InputStream in = String2Inputstream(strxml);
        org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if (children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }

            m.put(k, v);
        }

        // 关闭流
        in.close();

        return m;
    }

    /**
     * 获取子结点的xml
     *
     * @param children
     * @return String
     */
    public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            Iterator it = children.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }

    public static InputStream String2Inputstream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }
    /**
     * 处理xml请求信息
     * @param request
     * @return
     */
    public static String getXmlRequest(HttpServletRequest request){
        java.io.BufferedReader bis = null;
        String result = "";
        try{
            bis = new java.io.BufferedReader(new java.io.InputStreamReader(request.getInputStream()));
            String line = null;
            while((line = bis.readLine()) != null){
                result += line;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(bis != null){
                try{
                    bis.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    public static String getSign(Map<String,String> paramMap,String key){
        List list = new ArrayList(paramMap.keySet());
        Object[] ary = list.toArray();
        Arrays.sort(ary);
        list = Arrays.asList(ary);
        String str = "";
        for(int i=0;i<list.size();i++){
            str+=list.get(i)+"="+paramMap.get(list.get(i)+"")+"&";
        }
        str+="key="+key;
        str = DecriptUtil.MD5(str).toUpperCase();

        return str;
    }
    /**
     * 获取统一订单提交返回字符串值
     * @param url
     * @param xmlParam
     * @return
     */
    public static String getTradeOrder(String url, String xmlParam) {
        DefaultHttpClient client = new DefaultHttpClient();
        client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS,
                true);
        HttpPost httpost = new HttpPost(url);
        String jsonStr = "";
        try {
            httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
            HttpResponse response = client.execute(httpost);
            jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonStr;
    }


    /**
     * 用户转账
     * @return
     */
    public static boolean toUserZz(String appid,String je){

        String httpurl =WxConfig.XJHBURL;
        String noncestr= IdGen.uuid();//随机字符串
        String mchid=WxConfig.Mch_id;//商户号
        String  body="微信转账";
        String device_info=je;//付款金额
        String stringSignTemp=
                "noncestr="+noncestr+
                        "&body="+body+
                        "&opid="+appid+
                        "&mch_id="+mchid+
                        "&device_info="+device_info+
                        "&key="+WxConfig.key;
        String sign=DecriptUtil.MD5(stringSignTemp).toUpperCase();//签名

        String pr1="<xml>"+
                "<mch_id>"+mchid+"</mch_id>"+
                "<nonce_str>"+noncestr+"</nonce_str>"+
                "<body>"+body+"</body>"+
                "<device_info>"+device_info+"</device_info>"+
                "<appid>"+appid+"</appid>"+
                "<sign>"+sign+"</sign>"+
                "</xml>";

        InputStream in = null;
        StringBuilder sb = new StringBuilder();
        HttpEntity entity=null;
        try {
            //加载密钥
            File file=new File(WxConfig.ZSURL);//证书路径，这个路径本地测试可以，但在服务器中不对
            FileInputStream fileInputStream=new FileInputStream(file);
            KeyStore clientTrustKeyStore = KeyStore.getInstance("PKCS12");
            clientTrustKeyStore.load(fileInputStream, mchid.toCharArray());
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(clientTrustKeyStore, mchid.toCharArray());
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("TLSv1");
            sslContext.init(kmf.getKeyManagers(), tm, new java.security.SecureRandom());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            HttpPost httppost = new HttpPost(httpurl);
            httppost.setEntity(new StringEntity(pr1, "utf-8"));
            System.out.println(EntityUtils.toString(httppost.getEntity()));
            CloseableHttpResponse response = httpclient.execute(httppost);
            entity = response.getEntity();
            in=entity.getContent();
            byte[] bytes = new byte[1024];
            int len = 0;
            while((len = in.read(bytes)) !=-1){
                sb.append(new String(bytes, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(in != null){
                try {
                    in.close();
                    EntityUtils.toString(entity);
                } catch (IOException e) {
                }finally{
                    if(entity != null){
                    }
                }
            }
        }
        Map<String, String> map=DecriptUtil.readStringXmlOut(sb.toString());
        if(map.get("result_code").equals("SUCCESS")){
            return true;
        }else{
            return false;
        }

    }


    /**
     * 用户红包
     * @return
     */
    public static boolean toUserHb(String opid,int je){

        String httpurl =WxConfig.XJHBURL;
        String noncestr= IdGen.uuid();//随机字符串
        String mchid=WxConfig.Mch_id;//商户号
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");//设置日期格式
        String times=(df.format(new Date())+((int)(Math.random()*10))).toString();// new Date()为获取当前系统时间
        String mchbillno=mchid+times;//商户订单号（每个订单号必须唯一）组成： mch_id+yyyymmdd+10位一天内不能重复的数字（可以为hhMMssDD1）。接口根据商户订单号支持重入， 如出现超时可再调用。
        String wxappid=WxConfig.AppID;//公众账号appid
        String nickname="小龙虾信息科技";//提供方名称
        String sendname="小龙虾信息科技";//红包发送者名称
//		String reopenid=opid;//用戶openID
        String reopenid=opid;//用戶openID
        int totalamount=je;//付款金额
        int totalnum=1;//红包发放总人数
        String wishing="谢谢";//红包祝福语
        String clientip=WxConfig.IP;//调用接口的机器Ip地址
        String actname="余额提现";//活动名称
        String remark="余额提现";//备注信息
        String stringSignTemp=
                "act_name="+actname+
                        "&client_ip="+clientip+
                        "&mch_billno="+mchbillno+
                        "&mch_id="+mchid+
                        "&nick_name="+nickname+
                        "&nonce_str="+noncestr+
                        "&re_openid="+reopenid+
                        "&remark="+remark+
                        "&send_name="+sendname+
                        "&total_amount="+totalamount+
                        "&total_num="+totalnum+
                        "&wishing="+wishing+
                        "&wxappid="+wxappid+
                        "&key="+WxConfig.key;
        String sign=DecriptUtil.MD5(stringSignTemp).toUpperCase();//签名

        String pr1="<xml>"+
                "<act_name>"+actname+"</act_name>"+
                "<client_ip>"+clientip+"</client_ip>"+
                "<mch_billno>"+mchbillno+"</mch_billno>"+
                "<mch_id>"+mchid+"</mch_id>"+
                "<nick_name>"+nickname+"</nick_name>"+
                "<nonce_str>"+noncestr+"</nonce_str>"+
                "<re_openid>"+reopenid+"</re_openid>"+
                "<remark>"+remark+"</remark>"+
                "<send_name>"+sendname+"</send_name>"+
                "<total_amount>"+totalamount+"</total_amount>"+
                //"<min_value>"+minvalue+"</min_value>"+
                //"<max_value>"+maxvalue+"</max_value>"+
                "<total_num>"+totalnum+"</total_num>"+
                "<wishing>"+wishing+"</wishing>"+
                "<wxappid>"+wxappid+"</wxappid>"+
                "<sign>"+sign+"</sign>"+
                "</xml>";

        InputStream in = null;
        StringBuilder sb = new StringBuilder();
        HttpEntity entity=null;
        try {
            //加载密钥
            File file=new File(WxConfig.ZSURL);//证书路径，这个路径本地测试可以，但在服务器中不对
            FileInputStream fileInputStream=new FileInputStream(file);
            KeyStore clientTrustKeyStore = KeyStore.getInstance("PKCS12");
            clientTrustKeyStore.load(fileInputStream, mchid.toCharArray());
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(clientTrustKeyStore, mchid.toCharArray());
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("TLSv1");
            sslContext.init(kmf.getKeyManagers(), tm, new java.security.SecureRandom());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            HttpPost httppost = new HttpPost(httpurl);
            httppost.setEntity(new StringEntity(pr1, "utf-8"));
            System.out.println(EntityUtils.toString(httppost.getEntity()));
            CloseableHttpResponse response = httpclient.execute(httppost);
            entity = response.getEntity();
            in=entity.getContent();
            byte[] bytes = new byte[1024];
            int len = 0;
            while((len = in.read(bytes)) !=-1){
                sb.append(new String(bytes, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(in != null){
                try {
                    in.close();
                    EntityUtils.toString(entity);
                } catch (IOException e) {
                }finally{
                    if(entity != null){
                    }
                }
            }
        }
        Map<String, String> map=DecriptUtil.readStringXmlOut(sb.toString());
        if(map.get("result_code").equals("SUCCESS")){
            return true;
        }else{
            return false;
        }

    }





}
