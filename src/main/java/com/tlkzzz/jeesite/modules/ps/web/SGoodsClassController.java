/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.tlkzzz.jeesite.modules.ps.entity.SGoodsClass;
import com.tlkzzz.jeesite.modules.ps.service.SGoodsClassService;

/**
 * 商品分类管理Controller
 * @author szx
 * @version 2017-07-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ps/sGoodsClass")
public class SGoodsClassController extends BaseController {

	@Autowired
	private SGoodsClassService sGoodsClassService;
	
	@ModelAttribute
	public SGoodsClass get(@RequestParam(required=false) String id) {
		SGoodsClass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sGoodsClassService.get(id);
		}
		if (entity == null){
			entity = new SGoodsClass();
		}
		return entity;
	}
	
	@RequiresPermissions("ps:sGoodsClass:view")
	@RequestMapping(value = {"list", ""})
	public String list(SGoodsClass sGoodsClass, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SGoodsClass> page = sGoodsClassService.findPage(new Page<SGoodsClass>(request, response), sGoodsClass); 
		model.addAttribute("page", page);
		return "modules/ps/sGoodsClassList";
	}

	@RequiresPermissions("ps:sGoodsClass:view")
	@RequestMapping(value = "form")
	public String form(SGoodsClass sGoodsClass, Model model) {
		model.addAttribute("sGoodsClass", sGoodsClass);
		return "modules/ps/sGoodsClassForm";
	}

	@RequiresPermissions("ps:sGoodsClass:edit")
	@RequestMapping(value = "save")
	public String save(SGoodsClass sGoodsClass, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sGoodsClass)){
			return form(sGoodsClass, model);
		}
		sGoodsClassService.save(sGoodsClass);
		addMessage(redirectAttributes, "保存商品分类管理成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sGoodsClass/?repage";
	}
	
	@RequiresPermissions("ps:sGoodsClass:edit")
	@RequestMapping(value = "delete")
	public String delete(SGoodsClass sGoodsClass, RedirectAttributes redirectAttributes) {
		sGoodsClassService.delete(sGoodsClass);
		addMessage(redirectAttributes, "删除商品分类管理成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sGoodsClass/?repage";
	}

}