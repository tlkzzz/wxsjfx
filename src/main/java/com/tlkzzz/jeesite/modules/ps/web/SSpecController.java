/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ps.entity.SSpecClass;
import com.tlkzzz.jeesite.modules.ps.service.SSpecClassService;
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
import com.tlkzzz.jeesite.modules.ps.entity.SSpec;
import com.tlkzzz.jeesite.modules.ps.service.SSpecService;

import java.util.List;

/**
 * 规格Controller
 * @author szx
 * @version 2017-07-18
 */
@Controller
@RequestMapping(value = "${adminPath}/ps/sSpec")
public class SSpecController extends BaseController {

	@Autowired
	private SSpecService sSpecService;
	@Autowired
	private SSpecClassService sSpecClassService;
	
	@ModelAttribute
	public SSpec get(@RequestParam(required=false) String id) {
		SSpec entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sSpecService.get(id);
		}
		if (entity == null){
			entity = new SSpec();
		}
		return entity;
	}
	
	@RequiresPermissions("ps:sSpec:view")
	@RequestMapping(value = {"list", ""})
	public String list(SSpec sSpec, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SSpec> page = sSpecService.findPage(new Page<SSpec>(request, response), sSpec); 
		model.addAttribute("sSpec", sSpec);
		model.addAttribute("page", page);
		return "modules/ps/sSpecList";
	}

	@RequiresPermissions("ps:sSpec:view")
	@RequestMapping(value = "form")
	public String form(SSpec sSpec, Model model) {
		List<SSpecClass> sSpecClassList=sSpecClassService.findList(new SSpecClass());
		model.addAttribute("sSpec", sSpec);
		model.addAttribute("sSpecClassList", sSpecClassList);
		return "modules/ps/sSpecForm";
	}

	@RequiresPermissions("ps:sSpec:edit")
	@RequestMapping(value = "save")
	public String save(SSpec sSpec, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sSpec)){
			return form(sSpec, model);
		}
		sSpecService.save(sSpec);
		addMessage(redirectAttributes, "保存规格成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sSpec/?repage";
	}
	
	@RequiresPermissions("ps:sSpec:edit")
	@RequestMapping(value = "delete")
	public String delete(SSpec sSpec, RedirectAttributes redirectAttributes) {
		sSpecService.delete(sSpec);
		addMessage(redirectAttributes, "删除规格成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sSpec/?repage";
	}

}