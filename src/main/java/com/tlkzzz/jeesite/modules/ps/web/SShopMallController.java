package com.tlkzzz.jeesite.modules.ps.web;


import com.tlkzzz.jeesite.common.config.Global;
import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.modules.ps.entity.SAddress;
import com.tlkzzz.jeesite.modules.ps.entity.SGenre;
import com.tlkzzz.jeesite.modules.ps.entity.SGoods;
import com.tlkzzz.jeesite.modules.ps.entity.SMember;
import com.tlkzzz.jeesite.modules.ps.service.SAddressService;
import com.tlkzzz.jeesite.modules.ps.service.SGoodsService;
import com.tlkzzz.jeesite.modules.sys.entity.Area;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品Controller
 * @author xrc
 * @version 2017-07-18
 */
@Controller
@RequestMapping(value = "${shopPath}")
public class SShopMallController extends BaseController{
    private String url = "";

    @Autowired
    private SGoodsService sGoodsService;
    @Autowired
    private SAddressService sAddressService;

    public String check(ModelAndView modelAndView) {
        if (StringUtils.isBlank(UserUtils.getUser().getId())){
            url =  "redirect:"+ Global.getAdminPath();
        }
        return url;
    }
    /**         商城代码开始          **/
    @RequestMapping(value = {"index",""})
    public String index(){/**首页**/
        return "modules/shop/index";
    }
    @RequestMapping(value = "foot")
    public String foot(){/**页脚**/
        return "modules/shop/foot";
    }
    @RequestMapping(value = "home")/**主页**/
    public String home(HttpServletRequest request,HttpServletResponse response,Model model){
        model.addAttribute("page",sGoodsService.findPage(new Page<SGoods>(request,response),new SGoods()));
        return "modules/shop/home";
    }

    @ResponseBody
    @RequestMapping(value = "getGoodsList")/**获取商品列表**/
    public List<SGoods> getGoodsList(HttpServletRequest request,HttpServletResponse response){
        Page<SGoods> page = sGoodsService.findPage(new Page<SGoods>(request,response),new SGoods());
        return page.getList();
    }

    /**
     * shizx 收货地址添加页面调用方法
     * */
    @RequestMapping(value = {"listss"})
    public String listss() {
        return "modules/shop/shopShdzForm";
    }

    /**
     *  shizx收货地址保存方法
     **/
    @RequestMapping(value = {"shdzSave"})
    public String shdzSave(HttpServletRequest request, HttpServletResponse response, Model model) {
        String shr=request.getParameter("shr");
        String sjhm=request.getParameter("sjhm");
        String xqdz=request.getParameter("xqdz");
        String ssq=request.getParameter("ssq");
        SAddress sAddress=new SAddress();
        sAddress.setMember(new SMember(UserUtils.getUser().getId()));
        sAddress.setArea(new Area(ssq));
        sAddress.setAddress(xqdz);
        sAddress.setTel(sjhm);
        sAddress.setShr(shr);
        sAddressService.save(sAddress);
        return "modules/shop/shdzList";
    }

    /**
     * shizx 地址页面
     * */
    @RequestMapping(value = {"shdzList"})
    public String shdzList() {
        return "modules/shop/shdzList";
    }

    @RequestMapping(value = {"dzList"})
    public List<SAddress> dzList() {
        SAddress sAddress=new SAddress();
        sAddress.setMember(new SMember(UserUtils.getUser().getId()));
        List<SAddress> sAddressList=sAddressService.findList(sAddress);
        return  sAddressList;
    }


    /**         商城代码结束          **/
}
