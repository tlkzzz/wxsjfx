package com.tlkzzz.jeesite.modules.wx.web;

import com.thoughtworks.xstream.mapper.Mapper;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.modules.wx.entity.ClickButton;
import com.tlkzzz.jeesite.modules.wx.entity.ViewButton;
import com.tlkzzz.jeesite.modules.wx.util.MenuUtil;
import com.tlkzzz.jeesite.modules.wx.util.WeiXinUtil;
import com.tlkzzz.jeesite.modules.wx.util.WxConfig;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/28.
 */

public class WxMenuController {

    //公众号id

//    @RequestMapping(value = "create")
    public static String create() {

//        ClickButton cbt=new ClickButton();
//        cbt.setKey("image");
//        cbt.setName("回复图片");
//        cbt.setType("click");


        ViewButton vbt=new ViewButton();
        vbt.setUrl("http://www.cuiyongzhi.com");
        vbt.setName("博客");
        vbt.setType("view");

        JSONArray sub_button=new JSONArray();
//        sub_button.add(cbt);
        sub_button.add(vbt);


        JSONObject buttonOne=new JSONObject();
        buttonOne.put("name", "菜单");
        buttonOne.put("sub_button", sub_button);

        JSONArray button=new JSONArray();
        button.add(vbt);
        button.add(buttonOne);
//        button.add(cbt);

        JSONObject json=new JSONObject();
        json.put("button", button);
        System.out.println(json);
         String ss = "{\n" +
                 "    \"button\": [\n" +
                 "        {\n" +
                 "            \"name\": \"博客\", \n" +
                 "            \"type\": \"view\", \n" +
                 "            \"url\": \"http://www.cuiyongzhi.com\"\n" +
                 "        }, \n" +
                 "     \n" +
                 "      \n" +
                 "    ]\n" +
                 "}";

         String s="{\n" +
                 "     \"button\":[\n" +
                 "     {  \n" +
                 "          \"type\":\"click\",\n" +
                 "          \"name\":\"今日歌曲\",\n" +
                 "          \"key\":\"V1001_TODAY_MUSIC\"\n" +
                 "      },\n" +
                 "      {\n" +
                 "           \"name\":\"菜单\",\n" +
                 "           \"sub_button\":[\n" +
                 "           {    \n" +
                 "               \"type\":\"view\",\n" +
                 "               \"name\":\"搜索\",\n" +
                 "               \"url\":\"http://www.soso.com/\"\n" +
                 "            },\n" +
                 "            {\n" +
                 "               \"type\":\"view\",\n" +
                 "               \"name\":\"视频\",\n" +
                 "               \"url\":\"http://v.qq.com/\"\n" +
                 "            },\n" +
                 "            {\n" +
                 "               \"type\":\"click\",\n" +
                 "               \"name\":\"赞一下我们\",\n" +
                 "               \"key\":\"V1001_GOOD\"\n" +
                 "            }]\n" +
                 "       }]\n" +
                 " }";
//        String user_define_menu = "{\"button\":[{\"type\":\"click\",\"name\":\"项目管理\",\"key\":\"20_PROMANAGE\"},{\"type\":\"click\",\"name\":\"机构运作\",\"key\":\"30_ORGANIZATION\"},{\"name\":\"日常工作\",\"sub_button\":[{\"type\":\"click\",\"name\":\"待办工单\",\"key\":\"01_WAITING\"},{\"type\":\"click\",\"name\":\"已办工单\",\"key\":\"02_FINISH\"},{\"type\":\"click\",\"name\":\"我的工单\",\"key\":\"03_MYJOB\"},{\"type\":\"click\",\"name\":\"公告消息箱\",\"key\":\"04_MESSAGEBOX\"},{\"type\":\"click\",\"name\":\"签到\",\"key\":\"05_SIGN\"}]}]}";
//        System.out.println(user_define_menu);
        String access_token=WeiXinUtil.getToken();
        String  url = WxConfig.CREATMENU+access_token;
        JSONObject jsonObject = null;
        try {
             jsonObject=  WeiXinUtil.doPostStr(url,s);

            System.out.println(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    public static void main(String args[]) {
//        HttpServletRequest request=new HttpServletRequest();
//        HttpSession session = request.getSession();
//        String t = session.getAttribute("access_token").toString();
//       String jsonObject=WxMenuController.create();

//        String access_token=WeiXinUtil.getToken();
//        System.out.println(jsonObject);

        int status = MenuUtil.createMenu(MenuUtil.getMenu());

        if(status==0){
            System.out.println("菜单创建成功！");
        }else{
            System.out.println("菜单创建失败！");
        }

    }



}
