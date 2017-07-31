package com.tlkzzz.jeesite.modules.wx.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import com.tlkzzz.jeesite.modules.wx.entity.Menu;
import com.tlkzzz.jeesite.modules.wx.entity.Button;
import com.tlkzzz.jeesite.modules.wx.entity.CommonButton;
import com.tlkzzz.jeesite.modules.wx.entity.ComplexButton;
import com.tlkzzz.jeesite.modules.wx.entity.ViewButton;
import net.sf.json.JSONObject;

public class MenuUtil {
	/**
	 * 	创建自定义菜单(每天限制1000次)
	 * */
	public static int createMenu(Menu menu){
		String jsonMenu=JSONObject.fromObject(menu).toString();

		int status=0;
		 String access_token=WeiXinUtil.getToken();
		System.out.println("菜单："+jsonMenu);
		String path="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token;
		try {
			URL url=new URL(path);
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			http.setDoOutput(true);
			http.setDoInput(true);
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(jsonMenu.getBytes("UTF-8"));
			os.close();
			
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] bt = new byte[size];
			is.read(bt);
			String message=new String(bt,"UTF-8");
			JSONObject jsonMsg = JSONObject.fromObject(message);
			status = Integer.parseInt(jsonMsg.getString("errcode"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	
	
	/**
	 * 		封装菜单数据
	 * */
	public static Menu getMenu(){
		//预约挂号
		String yygh="http://wx.tlkzzz.com/s/QRScan";
		//我的挂号
		String wdgh="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3e4a89adbc62b1e9&redirect_uri=http://www.jiaqiankun.site/imessage/wechat/event/zyfy.html&response_type=code&scope=snsapi_base&state=0531_819";
		//门诊缴费
		String mzjf="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3e4a89adbc62b1e9&redirect_uri=http://www.jiaqiankun.site/imessage/wechat/event/zyfy.html&response_type=code&scope=snsapi_base&state=0531_819";
		//住院押金补缴
		String zyyj="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3e4a89adbc62b1e9&redirect_uri=http://www.jiaqiankun.site/imessage/wechat/event/zyfy.html&response_type=code&scope=snsapi_base&state=0531_819";
		//我的报告
		String wdbg="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3e4a89adbc62b1e9&redirect_uri=http://www.jiaqiankun.site/imessage/wechat/event/wdbg.html&response_type=code&scope=snsapi_base&state=0531_819";

		ViewButton q = new ViewButton();
		q.setName("二维码");
		q.setType("view");
		q.setUrl(wdbg);
		ComplexButton qq = new ComplexButton();
		qq.setName("二维码");
		qq.setSub_button(new Button[]{q});
//
		ViewButton vb = new ViewButton();
		vb.setName("商城");
		vb.setType("view");
		vb.setUrl(yygh);

		ComplexButton cx_2 = new ComplexButton();
		cx_2.setName("商城");
		cx_2.setSub_button(new Button[]{vb});
//
//




		CommonButton cb_6 = new CommonButton();
		cb_6.setKey("cjwt");
		cb_6.setName("常见问题");
		cb_6.setType("click");
		CommonButton cb_7 = new CommonButton();
		cb_7.setKey("myddc");
		cb_7.setName("满意度调查");
		cb_7.setType("click");
		CommonButton cb_8 = new CommonButton();
		cb_8.setKey("jyfk");
		cb_8.setName("建议反馈");
		cb_8.setType("click");
		CommonButton cb_9 = new CommonButton();
		cb_9.setKey("app");
		cb_9.setName("APP下载");
		cb_9.setType("click");
		ComplexButton cx_3 = new ComplexButton();
		cx_3.setName("服务");
		cx_3.setSub_button(new Button[]{cb_6,cb_7,cb_8,cb_9});
		
		Menu menu=new Menu();
		menu.setButton(new ComplexButton[]{qq,cx_2,cx_3});
		
		return menu;
	}
}
