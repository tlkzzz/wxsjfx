package com.tlkzzz.jeesite.modules.ps.web;


import com.tlkzzz.jeesite.common.config.Global;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品Controller
 * @author xrc
 * @version 2017-07-18
 */
@Controller
@RequestMapping(value = "${shopPath}")
public class SShopMallController {


    @ModelAttribute
    public String check() {
        String url = "";
        if (StringUtils.isBlank(UserUtils.getUser().getId())){
            url =  "redirect:"+ Global.getAdminPath();
        }
        return url;
    }
    /**         商城代码开始          **/
    @RequestMapping(value = {"index",""})
    public String index(){
        return "";
    }




    /**         商城代码结束          **/
}
