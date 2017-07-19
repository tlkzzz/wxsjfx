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
import com.tlkzzz.jeesite.modules.ps.entity.SProportionCommission;
import com.tlkzzz.jeesite.modules.ps.service.SProportionCommissionService;

/**
 * 提成比例Controller
 * @author xlc
 * @version 2017-07-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ps/sProportionCommission")
public class SProportionCommissionController extends BaseController {

	@Autowired
	private SProportionCommissionService sProportionCommissionService;
	
	@ModelAttribute
	public SProportionCommission get(@RequestParam(required=false) String id) {
		SProportionCommission entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sProportionCommissionService.get(id);
		}
		if (entity == null){
			entity = new SProportionCommission();
		}
		return entity;
	}
	
	@RequiresPermissions("ps:sProportionCommission:view")
	@RequestMapping(value = {"list", ""})
	public String list(SProportionCommission sProportionCommission, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SProportionCommission> page = sProportionCommissionService.findPage(new Page<SProportionCommission>(request, response), sProportionCommission); 
		model.addAttribute("page", page);
		model.addAttribute("sProportionCommission",sProportionCommission);
		return "modules/ps/sProportionCommissionList";
	}

	@RequiresPermissions("ps:sProportionCommission:view")
	@RequestMapping(value = "form")
	public String form(SProportionCommission sProportionCommission, Model model) {
		model.addAttribute("sProportionCommission", sProportionCommission);
		return "modules/ps/sProportionCommissionForm";
	}

	@RequiresPermissions("ps:sProportionCommission:edit")
	@RequestMapping(value = "save")
	public String save(SProportionCommission sProportionCommission, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sProportionCommission)){
			return form(sProportionCommission, model);
		}
		sProportionCommissionService.save(sProportionCommission);
		addMessage(redirectAttributes, "保存提成比例表成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sProportionCommission/?repage";
	}
	
	@RequiresPermissions("ps:sProportionCommission:edit")
	@RequestMapping(value = "delete")
	public String delete(SProportionCommission sProportionCommission, RedirectAttributes redirectAttributes) {
		sProportionCommissionService.delete(sProportionCommission);
		addMessage(redirectAttributes, "删除提成比例表成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sProportionCommission/?repage";
	}

}