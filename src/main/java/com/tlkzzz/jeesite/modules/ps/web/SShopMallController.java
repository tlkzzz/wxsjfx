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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
    @Autowired
    private SSpecClassService sSpecClassService;
    @Autowired
    private SOrderService sOrderService;
    @Autowired
    private SReceiptService sReceiptService;

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

    /**
     * 订单确认方法
     * @param ids 购物车id 列:id1,id2,
     * @param specIds 规格ids 列:id1,id2,|id3,id4,
     * @param nums 数量列表 列:num1,num2,
     * @param model
     * @return
     */
    @RequestMapping(value = "confirmOrder")
    public String confirmOrder(String ids,String specIds,String nums,Model model){
        List<SShop> shopList = new ArrayList<SShop>();
        if(StringUtils.isBlank(ids)||StringUtils.isBlank(specIds)||StringUtils.isBlank(nums)){
            SShop sShop = new SShop();
            sShop.setCreateBy(UserUtils.getUser());
            shopList = sOrderService.findConfirmOrderList(sShop);
        }else {
            shopList = sShopMallService.confirmOrder(ids, specIds, nums);
        }
        if(shopList.size()==0)return "redirect:"+Global.getShopPath()+"";//重定向到购物车
        for(SShop s:shopList)
            s.setGoods(sShopMallService.findSpecInfo(s.getGoods(),s.getSpecIds()));
        model.addAttribute("orderTotal", sShopMallService.countOrderTotal(shopList));
        model.addAttribute("addressList", sShopMallService.findAddressListByM());
        model.addAttribute("orderList", shopList);
        return "modules/shop/confirmOrder";
    }

    /**
     * 支付订单方法
     * @param addressId 收货地址ID
     * @param model
     * @return
     */
    @RequestMapping(value = "paymentOrder")
    public String paymentOrder(String addressId,Model model){
        if(StringUtils.isBlank(addressId)){
            return "redirect:"+Global.getShopPath()+"/confirmOrder";//重定向到订单确认;
        }
        SShop sShop = new SShop();
        sShop.setCreateBy(UserUtils.getUser());
        List<SShop> shopList = sOrderService.findConfirmOrderList(sShop);
        double total = sShopMallService.countOrderTotal(shopList);
        if(shopList.size()<=0||total<=0)return "redirect:"+Global.getShopPath()+"";//重定向到购物车
        SReceipt receipt = sReceiptService.insertByTotal(String.valueOf(total));
        if(receipt==null)return "redirect:"+Global.getShopPath()+"/confirmOrder";//重定向到订单确认;
        for(SShop ss: shopList){
            sShopMallService.savaOrderByShop(ss,receipt,addressId);
        }
        return "modules/shop/paymentOver";
    }

    @RequestMapping(value = "paymentOver")
    public String paymentOver(String id, Model model){
        if(StringUtils.isNotBlank(id)){
            model.addAttribute("receipt", sReceiptService.get(id));
            model.addAttribute("orderList", sOrderService.findListByReceiptId(id));
            model.addAttribute("flag",true);
        }else {
            model.addAttribute("flag",false);
        }
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "sendSmsVCode")
    public String sendSmsVCode(String mobile){
        User user = UserUtils.getUser();
        if(StringUtils.isNotBlank(mobile)&&user!=null&&StringUtils.isNotBlank(user.getId())){
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
        UserUtils.clearCache(UserUtils.getUser().getMember());//清除当前会员缓存
        return "true";
    }
    /**
     * 个人中心
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "PersonalCenter")
    public String PersonalCenter(HttpServletRequest request,HttpServletResponse response,Model model){
        User user = UserUtils.getUser();
       String name=user.getName();
       String photo;
        photo=user.getMember().getPhoto();
        model.addAttribute("name",name);
        model.addAttribute("photo",photo);
        return "modules/shop/personalList";
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
//        sAddress.setAddress(xqdz);
        sAddress.setTel(sjhm);
        sAddress.setShr(shr);
        sAddressService.updatess(sAddress);
        return "modules/shop/shdzList";
    }

    @ResponseBody
    @RequestMapping(value = {"updateIs"})
    public String updateIs(String ids) {
        SAddress sAddress=new SAddress();
        sAddress.setMember(new SMember(UserUtils.getUser().getId()));
        sAddressService.updateOne(sAddress);
        sAddress.setId(ids);
        sAddressService.updateTwo(sAddress);
        return "true";
    }

    /**
     * shizx 我的订单详情页面
     * */
    @RequestMapping(value = {"myDdList"})
    public String myDdList() {
        return "modules/shop/MyddList";
    }


    /**
     * shizx 我的订单详情页面
     * */
    @ResponseBody
    @RequestMapping(value = {"myList"})
    public List<SOrder> myList(String bs) {
        SOrder sOrder=new SOrder();
        sOrder.setCreateBy(new User(UserUtils.getUser().getId()));
        sOrder.setDdbs(bs);
        List<SOrder> sorderList=sOrderService.findList(sOrder);
        return sorderList;
    }

    @ResponseBody
    @RequestMapping(value = {"saveHf"})
    public String saveHf(String hf,String ids) {
        SGoodsComment sGoodsComment=new SGoodsComment();
        sGoodsComment.setComment(hf);
        sGoodsComment.setOrder(new SOrder(ids));
        List<SOrder> sorderList=sOrderService.findList(new SOrder(ids));
        sGoodsComment.setGoods(new SGoods(sorderList.get(0).getGoods().getId()));
        sGoodsCommentService.save(sGoodsComment);
        return "true";
    }
    /**         商城代码结束          **/
}
