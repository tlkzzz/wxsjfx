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
import com.tlkzzz.jeesite.modules.ps.entity.SSpecClass;
import com.tlkzzz.jeesite.modules.ps.service.SSpecClassService;

/**
 * 规格分类Controller
 * @author szx
 * @version 2017-07-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ps/sSpecClass")
public class SSpecClassController extends BaseController {

	@Autowired
	private SSpecClassService sSpecClassService;
	
	@ModelAttribute
	public SSpecClass get(@RequestParam(required=false) String id) {
		SSpecClass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sSpecClassService.get(id);
		}
		if (entity == null){
			entity = new SSpecClass();
		}
		return entity;
	}
	
	@RequiresPermissions("ps:sSpecClass:view")
	@RequestMapping(value = {"list", ""})
	public String list(SSpecClass sSpecClass, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SSpecClass> page = sSpecClassService.findPage(new Page<SSpecClass>(request, response), sSpecClass); 
		model.addAttribute("page", page);
		return "modules/ps/sSpecClassList";
	}

	@RequiresPermissions("ps:sSpecClass:view")
	@RequestMapping(value = "form")
	public String form(SSpecClass sSpecClass, Model model) {
		model.addAttribute("sSpecClass", sSpecClass);
		return "modules/ps/sSpecClassForm";
	}

	@RequiresPermissions("ps:sSpecClass:edit")
	@RequestMapping(value = "save")
	public String save(SSpecClass sSpecClass, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sSpecClass)){
			return form(sSpecClass, model);
		}
		sSpecClassService.save(sSpecClass);
		addMessage(redirectAttributes, "保存规格分类成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sSpecClass/?repage";
	}
	
	@RequiresPermissions("ps:sSpecClass:edit")
	@RequestMapping(value = "delete")
	public String delete(SSpecClass sSpecClass, RedirectAttributes redirectAttributes) {
		sSpecClassService.delete(sSpecClass);
		addMessage(redirectAttributes, "删除规格分类成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sSpecClass/?repage";
	}

}