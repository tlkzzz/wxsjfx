package com.tlkzzz.jeesite.modules.ps.web;


import com.tlkzzz.jeesite.common.config.Global;
import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.utils.Encodes;
import com.tlkzzz.jeesite.common.utils.JuheSmsUtils;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.modules.ps.entity.*;
import com.tlkzzz.jeesite.modules.ps.service.*;
import com.tlkzzz.jeesite.modules.sys.entity.Area;
import com.tlkzzz.jeesite.modules.sys.entity.User;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
    private SGenreService sGenreService;
    @Autowired
    private SGoodsClassService sGoodsClassService;
    @Autowired
    private SAddressService sAddressService;
    @Autowired
    private SGoodsCommentService sGoodsCommentService;
    @Autowired
    private SMemberService sMemberService;
    @Autowired
    private SShopMallService sShopMallService;

    public String check(ModelAndView modelAndView) {
        if (StringUtils.isBlank(UserUtils.getUser().getId())){
            url =  "redirect:"+ Global.getAdminPath();
        }
        return url;
    }
    /**         商城代码开始          **/
    @RequestMapping(value = {"index",""})
    public String index(){/**首页**/
        SMember member = sMemberService.saveUserByJson("{\"openid\":\"oI-t8wHURtTZVlvYAHCzvqPO81CM\",\"nickname\":\"国服卡牌\",\"sex\":1,\"language\":\"zh_CN\",\"city\":\"长沙\",\"province\":\"湖南\",\"country\":\"中国\",\"headimgurl\":\"http://wx.qlogo.cn/mmhead/PiajxSqBRaEKmEB9icvchLClMf608zv19X72ya8h6eaQpPwm3nRFmJeA/0\",\"privilege\":[],\"unionid\":\"oGjjfspNk16U8ENVjjd93QYzU4ro\"}");
        if(member!=null&&StringUtils.isNotBlank(member.getId()))UserUtils.setMemberId(member.getId());
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
     * 商品详情页
     * @param goods
     * @param model
     * @return
     */
    @RequestMapping(value = "goodsInfo")
    public String goodsInfo(SGoods goods,Model model){
        SGoodsComment goodsComment = new SGoodsComment();
        goodsComment.setGoods(goods);
        goods = sGoodsService.get(goods);
        if(goods!=null&&goods.getGener()!=null)
            goods.setGener(sGenreService.getAll(goods.getGener().getId()));
        goods.setGoodsDesc(Encodes.unescapeHtml(goods.getGoodsDesc()));
        model.addAttribute("goods", goods);
        model.addAttribute("commentList",sGoodsCommentService.findList(goodsComment));
        return "modules/shop/xiangqing";
    }



    @RequestMapping(value = "smsVCode")
    public String smsVCode(SGoods goods,Model model){
        return "modules/shop/denglu";
    }

    @ResponseBody
    @RequestMapping(value = "sendSmsVCode")
    public String sendSmsVCode(String mobile){
        User user = UserUtils.getUser();
        if(StringUtils.isNotBlank(mobile)&&user!=null&&StringUtils.isNotBlank(user.getId())){
        //if(StringUtils.isNotBlank(mobile)){
            Date oldDate = (Date)UserUtils.getCache("SmsDate");
            Date date = new Date();
            if(oldDate!=null&&((date.getTime()-oldDate.getTime())/(1000*60))<1){
                return "true";
            }
            String s = sShopMallService.random(6);
            String code = "#name#="+user.getName()+"&#code#="+s;
            //if(JuheSmsUtils.getRequest(mobile,code)){
            if(true){
                UserUtils.putCache("SmsVCode",s);
                UserUtils.putCache("SmsMobile",mobile.trim());
                UserUtils.putCache("SmsDate",new Date());
                return "true";
            }
        }
        return "false";
    }

    @ResponseBody
    @RequestMapping(value = "checkSmsVCode")
    public String checkSmsVCode(String mobile, String vCode){
        if(UserUtils.getUser()==null||StringUtils.isBlank(UserUtils.getUser().getId()))return "false";
        if(StringUtils.isBlank(mobile)||StringUtils.isBlank(vCode))return "false";
        mobile = mobile.trim();vCode = vCode.trim();
        String cacheMobile = (String)UserUtils.getCache("SmsMobile");
        String cacheVcode = (String)UserUtils.getCache("SmsVCode");
        if(StringUtils.isBlank(cacheMobile)||StringUtils.isBlank(cacheVcode))return "false";
        if(!mobile.equals(cacheMobile)||!vCode.equals(cacheVcode))return "false";
        sMemberService.updateMobile(UserUtils.getUser().getId(),cacheMobile);
        return "true";
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

    @ResponseBody
    @RequestMapping(value = {"dzList"})
    public List<SAddress> dzList() {
        SAddress sAddress=new SAddress();
        sAddress.setMember(new SMember(UserUtils.getUser().getId()));
        List<SAddress> sAddressList=sAddressService.findList(sAddress);
        return sAddressList;
    }

    @ResponseBody
    @RequestMapping(value = {"scshList"})
    public String scshList(String ids) {
        sAddressService.delete(new SAddress(ids));
        return "true";
    }


    @RequestMapping(value = {"xgshList"})
    public String xgshList(HttpServletRequest request,Model model) {
        String ids=request.getParameter("data");
        SAddress sAddress=new SAddress(ids);
        List<SAddress> sAddressList=sAddressService.findList(sAddress);
        model.addAttribute("sAddressList",sAddressList);
        return "modules/shop/shopXgForm";
    }

    /**
     *  shizx收货地址修改方法
     **/
    @RequestMapping(value = {"xgdzSave"})
    public String xgdzSave(HttpServletRequest request, HttpServletResponse response, Model model) {
        String idss=request.getParameter("ids");
        String shr=request.getParameter("shr");
        String sjhm=request.getParameter("sjhm");
        String xqdz=request.getParameter("xqdz");
        String ssq=request.getParameter("ssq");
        SAddress sAddress=new SAddress();
        sAddress.setId(idss);
        sAddress.setMember(new SMember(UserUtils.getUser().getId()));
        sAddress.setArea(new Area(ssq));
        sAddress.setAddress(xqdz);
        sAddress.setTel(sjhm);
        sAddress.setShr(shr);
        sAddressService.updatess(sAddress);
        return "modules/shop/shdzList";
    }
    /**         商城代码结束          **/
}
