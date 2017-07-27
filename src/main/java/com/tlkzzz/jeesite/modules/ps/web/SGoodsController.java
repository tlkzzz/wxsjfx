/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.common.utils.Encodes;
import com.tlkzzz.jeesite.modules.ps.dao.SShopDao;
import com.tlkzzz.jeesite.modules.ps.entity.*;
import com.tlkzzz.jeesite.modules.ps.service.*;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tlkzzz.jeesite.common.config.Global;
import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.common.utils.StringUtils;

import java.util.List;

/**
 * 商品Controller
 * @author xrc
 * @version 2017-07-18
 */
@Controller
@RequestMapping(value = "${adminPath}/ps/sGoods")
public class SGoodsController extends BaseController {

	@Autowired
	private SGoodsService sGoodsService;
	@Autowired
	private SGoodsClassService sGoodsClassService;
	@Autowired
	private SGenreService sGenreService;
	@Autowired
	private SOrderService sOrderService;
	@Autowired
	private SSpecService sSpecService;
	@Autowired
	private SSpecClassService sSpecClassService;
	@Autowired
	private SShopDao shopDao;

	@ModelAttribute
	public SGoods get(@RequestParam(required=false) String id) {
		SGoods entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sGoodsService.get(id);
		}
		if (entity == null){
			entity = new SGoods();
		}
		return entity;
	}
	
	@RequiresPermissions("ps:sGoods:view")
	@RequestMapping(value = {"list", ""})
	public String list(SGoods sGoods, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SGoods> page = sGoodsService.findPage(new Page<SGoods>(request, response), sGoods); 
		model.addAttribute("gClassList", sGoodsClassService.findList(new SGoodsClass()));
		model.addAttribute("generList", sGenreService.findList(new SGenre()));
		model.addAttribute("sGoods", sGoods);
		model.addAttribute("page", page);
		model.addAttribute("sGoods", sGoods);
		return "modules/ps/sGoodsList";
	}

//	@RequestMapping(value = "shoplist")
//	public String shoplist(SShop sShop, String id, HttpServletRequest request, HttpServletResponse response, Model model) {
//		id="c9b39e65d066410289672fbf3hd4cf04";
//		String name= UserUtils.getUser().getId();
//		SShop sShop1=new SShop();
//		sShop1=sOrderService.getlist(id,name);
//        if(StringUtils.isNotBlank(id) && sShop1==null) {
//			SGoods sGoods=new SGoods();
//			sGoods=sGoodsService.get(id);
//			if(sGoods!=null&&sGoods.getGener()!=null)
//			sGoods.setGener(sGenreService.getAll(sGoods.getGener().getId()));
//			sGoods.getGener().setSpecClassList(sSpecClassService.findList(new SSpecClass()));
//			for(SSpecClass sc : sGoods.getGener().getSpecClassList()){
//				sc.setsSpecList(sSpecService.findList(new SSpec()));
//			}
//			sOrderService.savelist(sGoods);
//			model.addAttribute("sGoods",sGoods);
//		}
//		List<SShop>  sshoplist = sOrderService.findList(sShop);
//		model.addAttribute("generList", sSpecService.findList(new SSpec()));
//		model.addAttribute("sshoplist",sshoplist);
//		return "modules/shop/shoplist";
//	}
//
//	@ResponseBody
//	@RequestMapping(value = {"shopList"})
//	public String shopList(String ids) {
//		sOrderService.deletes(new SShop(ids));
//		return "true";
//	}
	@RequiresPermissions("ps:sGoods:view")
	@RequestMapping(value = "form")
	public String form(SGoods sGoods, Model model) {
		if(sGoods.getGClass()!=null&&StringUtils.isNotBlank(sGoods.getGClass().getId())) {
			SGoodsClass goodsClass = sGoodsClassService.get(sGoods.getGClass().getId());
			if(goodsClass!=null&&goodsClass.getsGenre()!=null&&StringUtils.isNotBlank(goodsClass.getsGenre().getId()))
				sGoods.setGener(sGenreService.getAll(goodsClass.getsGenre().getId()));
		}
		if(StringUtils.isNotBlank(sGoods.getGoodsDesc()))
			sGoods.setGoodsDesc(Encodes.unescapeHtml(sGoods.getGoodsDesc()));
		model.addAttribute("genreList", sGenreService.findList(new SGenre()));
		model.addAttribute("sGoods", sGoods);
		return "modules/ps/sGoodsForm";
	}

	@RequiresPermissions("ps:sGoods:edit")
	@RequestMapping(value = "save")
	public String save(SGoods sGoods, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sGoods)){
			return form(sGoods, model);
		}
		sGoodsService.save(sGoods);
		addMessage(redirectAttributes, "保存商品成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sGoods/?repage";
	}
	
	@RequiresPermissions("ps:sGoods:edit")
	@RequestMapping(value = "delete")
	public String delete(SGoods sGoods, RedirectAttributes redirectAttributes) {
		sGoodsService.delete(sGoods);
		addMessage(redirectAttributes, "删除商品成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sGoods/?repage";
	}

}