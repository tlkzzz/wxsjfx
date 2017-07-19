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
import com.tlkzzz.jeesite.modules.ps.entity.SMemberCommission;
import com.tlkzzz.jeesite.modules.ps.service.SMemberCommissionService;

/**
 * 会员提成Controller
 * @author xlc
 * @version 2017-07-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ps/sMemberCommission")
public class SMemberCommissionController extends BaseController {

	@Autowired
	private SMemberCommissionService sMemberCommissionService;
	
	@ModelAttribute
	public SMemberCommission get(@RequestParam(required=false) String id) {
		SMemberCommission entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sMemberCommissionService.get(id);
		}
		if (entity == null){
			entity = new SMemberCommission();
		}
		return entity;
	}
	
	@RequiresPermissions("ps:sMemberCommission:view")
	@RequestMapping(value = {"list", ""})
	public String list(SMemberCommission sMemberCommission, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SMemberCommission> page = sMemberCommissionService.findPage(new Page<SMemberCommission>(request, response), sMemberCommission); 
		model.addAttribute("page", page);
		return "modules/ps/sMemberCommissionList";
	}

	@RequiresPermissions("ps:sMemberCommission:view")
	@RequestMapping(value = "form")
	public String form(SMemberCommission sMemberCommission, Model model) {
		model.addAttribute("sMemberCommission", sMemberCommission);
		return "modules/ps/sMemberCommissionForm";
	}

	@RequiresPermissions("ps:sMemberCommission:edit")
	@RequestMapping(value = "save")
	public String save(SMemberCommission sMemberCommission, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sMemberCommission)){
			return form(sMemberCommission, model);
		}
		sMemberCommissionService.save(sMemberCommission);
		addMessage(redirectAttributes, "保存会员提成成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sMemberCommission/?repage";
	}
	
	@RequiresPermissions("ps:sMemberCommission:edit")
	@RequestMapping(value = "delete")
	public String delete(SMemberCommission sMemberCommission, RedirectAttributes redirectAttributes) {
		sMemberCommissionService.delete(sMemberCommission);
		addMessage(redirectAttributes, "删除会员提成成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sMemberCommission/?repage";
	}

}