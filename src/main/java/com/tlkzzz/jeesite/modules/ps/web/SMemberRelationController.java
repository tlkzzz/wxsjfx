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
import com.tlkzzz.jeesite.modules.ps.entity.SMemberRelation;
import com.tlkzzz.jeesite.modules.ps.service.SMemberRelationService;

/**
 * 会员关系Controller
 * @author xrc
 * @version 2017-07-18
 */
@Controller
@RequestMapping(value = "${adminPath}/ps/sMemberRelation")
public class SMemberRelationController extends BaseController {

	@Autowired
	private SMemberRelationService sMemberRelationService;
	
	@ModelAttribute
	public SMemberRelation get(@RequestParam(required=false) String id) {
		SMemberRelation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sMemberRelationService.get(id);
		}
		if (entity == null){
			entity = new SMemberRelation();
		}
		return entity;
	}
	
	@RequiresPermissions("ps:sMemberRelation:view")
	@RequestMapping(value = {"list", ""})
	public String list(SMemberRelation sMemberRelation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SMemberRelation> page = sMemberRelationService.findPage(new Page<SMemberRelation>(request, response), sMemberRelation); 
		model.addAttribute("page", page);
		return "modules/ps/sMemberRelationList";
	}

	@RequiresPermissions("ps:sMemberRelation:view")
	@RequestMapping(value = "form")
	public String form(SMemberRelation sMemberRelation, Model model) {
		model.addAttribute("sMemberRelation", sMemberRelation);
		return "modules/ps/sMemberRelationForm";
	}

	@RequiresPermissions("ps:sMemberRelation:edit")
	@RequestMapping(value = "save")
	public String save(SMemberRelation sMemberRelation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sMemberRelation)){
			return form(sMemberRelation, model);
		}
		sMemberRelationService.save(sMemberRelation);
		addMessage(redirectAttributes, "保存会员关系成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sMemberRelation/?repage";
	}
	
	@RequiresPermissions("ps:sMemberRelation:edit")
	@RequestMapping(value = "delete")
	public String delete(SMemberRelation sMemberRelation, RedirectAttributes redirectAttributes) {
		sMemberRelationService.delete(sMemberRelation);
		addMessage(redirectAttributes, "删除会员关系成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sMemberRelation/?repage";
	}

}