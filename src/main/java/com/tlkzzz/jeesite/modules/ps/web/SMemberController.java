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
import com.tlkzzz.jeesite.modules.ps.entity.SMember;
import com.tlkzzz.jeesite.modules.ps.service.SMemberService;

/**
 * 会员Controller
 * @author xrc
 * @version 2017-07-18
 */
@Controller
@RequestMapping(value = "${adminPath}/ps/sMember")
public class SMemberController extends BaseController {

	@Autowired
	private SMemberService sMemberService;
	
	@ModelAttribute
	public SMember get(@RequestParam(required=false) String id) {
		SMember entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sMemberService.get(id);
		}
		if (entity == null){
			entity = new SMember();
		}
		return entity;
	}
	
	@RequiresPermissions("ps:sMember:view")
	@RequestMapping(value = {"list", ""})
	public String list(SMember sMember, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SMember> page = sMemberService.findPage(new Page<SMember>(request, response), sMember); 
		model.addAttribute("page", page);
		return "modules/ps/sMemberList";
	}

	@RequiresPermissions("ps:sMember:view")
	@RequestMapping(value = "form")
	public String form(SMember sMember, Model model) {
		model.addAttribute("sMember", sMember);
		return "modules/ps/sMemberForm";
	}

	@RequiresPermissions("ps:sMember:edit")
	@RequestMapping(value = "save")
	public String save(SMember sMember, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sMember)){
			return form(sMember, model);
		}
		sMemberService.save(sMember);
		addMessage(redirectAttributes, "保存会员成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sMember/?repage";
	}
	
	@RequiresPermissions("ps:sMember:edit")
	@RequestMapping(value = "delete")
	public String delete(SMember sMember, RedirectAttributes redirectAttributes) {
		sMemberService.delete(sMember);
		addMessage(redirectAttributes, "删除会员成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sMember/?repage";
	}

}