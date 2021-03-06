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
import com.tlkzzz.jeesite.modules.ps.entity.SSpecGener;
import com.tlkzzz.jeesite.modules.ps.service.SSpecGenerService;

/**
 * 规格类型中间表Controller
 * @author szx
 * @version 2017-07-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ps/sSpecGener")
public class SSpecGenerController extends BaseController {

	@Autowired
	private SSpecGenerService sSpecGenerService;
	
	@ModelAttribute
	public SSpecGener get(@RequestParam(required=false) String id) {
		SSpecGener entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sSpecGenerService.get(id);
		}
		if (entity == null){
			entity = new SSpecGener();
		}
		return entity;
	}
	
	@RequiresPermissions("ps:sSpecGener:view")
	@RequestMapping(value = {"list", ""})
	public String list(SSpecGener sSpecGener, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SSpecGener> page = sSpecGenerService.findPage(new Page<SSpecGener>(request, response), sSpecGener); 
		model.addAttribute("sSpecGener", sSpecGener);
		model.addAttribute("page", page);
		return "modules/ps/sSpecGenerList";
	}

	@RequiresPermissions("ps:sSpecGener:view")
	@RequestMapping(value = "form")
	public String form(SSpecGener sSpecGener, Model model) {
		model.addAttribute("sSpecGener", sSpecGener);
		return "modules/ps/sSpecGenerForm";
	}

	@RequiresPermissions("ps:sSpecGener:edit")
	@RequestMapping(value = "save")
	public String save(SSpecGener sSpecGener, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sSpecGener)){
			return form(sSpecGener, model);
		}
		sSpecGenerService.save(sSpecGener);
		addMessage(redirectAttributes, "保存规格类型中间表成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sSpecGener/?repage";
	}
	
	@RequiresPermissions("ps:sSpecGener:edit")
	@RequestMapping(value = "delete")
	public String delete(SSpecGener sSpecGener, RedirectAttributes redirectAttributes) {
		sSpecGenerService.delete(sSpecGener);
		addMessage(redirectAttributes, "删除规格类型中间表成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sSpecGener/?repage";
	}

}