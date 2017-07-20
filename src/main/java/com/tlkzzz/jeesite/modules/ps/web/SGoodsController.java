/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.common.utils.Encodes;
import com.tlkzzz.jeesite.modules.ps.entity.SGenre;
import com.tlkzzz.jeesite.modules.ps.entity.SGoodsClass;
import com.tlkzzz.jeesite.modules.ps.service.SGenreService;
import com.tlkzzz.jeesite.modules.ps.service.SGoodsClassService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tlkzzz.jeesite.common.config.Global;
import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ps.entity.SGoods;
import com.tlkzzz.jeesite.modules.ps.service.SGoodsService;

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

	@RequiresPermissions("ps:sGoods:view")
	@RequestMapping(value = "form")
	public String form(SGoods sGoods, String classId, Model model) {
		if(StringUtils.isNotBlank(classId)) {
			SGoodsClass goodsClass = sGoodsClassService.get(classId);
			sGoods.setGener(sGenreService.getAll(goodsClass.getGenerId()));
		}
		if(StringUtils.isNotBlank(sGoods.getGoodsDesc()))Encodes.unescapeHtml(sGoods.getGoodsDesc());
		model.addAttribute("gClassList", sGoodsClassService.findList(new SGoodsClass()));
		model.addAttribute("generList", sGenreService.findList(new SGenre()));
		model.addAttribute("sGoods", sGoods);
		return "modules/ps/sGoodsForm";
	}

	@RequiresPermissions("ps:sGoods:edit")
	@RequestMapping(value = "save")
	public String save(SGoods sGoods, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sGoods)){
			return form(sGoods, sGoods.getGClass().getId(), model);
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