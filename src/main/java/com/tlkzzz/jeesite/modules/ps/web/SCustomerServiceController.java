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
import com.tlkzzz.jeesite.modules.ps.entity.SCustomerService;
import com.tlkzzz.jeesite.modules.ps.service.SCustomerServiceService;

/**
 * 客服表Controller
 * @author xlc
 * @version 2017-07-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ps/sCustomerService")
public class SCustomerServiceController extends BaseController {

	@Autowired
	private SCustomerServiceService sCustomerServiceService;
	
	@ModelAttribute
	public SCustomerService get(@RequestParam(required=false) String id) {
		SCustomerService entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sCustomerServiceService.get(id);
		}
		if (entity == null){
			entity = new SCustomerService();
		}
		return entity;
	}
	
	@RequiresPermissions("ps:sCustomerService:view")
	@RequestMapping(value = {"list", ""})
	public String list(SCustomerService sCustomerService, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SCustomerService> page = sCustomerServiceService.findPage(new Page<SCustomerService>(request, response), sCustomerService); 
		model.addAttribute("page", page);
		model.addAttribute("sCustomerService",sCustomerService);
		return "modules/ps/sCustomerServiceList";
	}

	@RequiresPermissions("ps:sCustomerService:view")
	@RequestMapping(value = "form")
	public String form(SCustomerService sCustomerService, Model model) {
		model.addAttribute("sCustomerService", sCustomerService);
		return "modules/ps/sCustomerServiceForm";
	}

	@RequiresPermissions("ps:sCustomerService:edit")
	@RequestMapping(value = "save")
	public String save(SCustomerService sCustomerService, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sCustomerService)){
			return form(sCustomerService, model);
		}
		sCustomerServiceService.save(sCustomerService);
		addMessage(redirectAttributes, "保存客服表成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sCustomerService/?repage";
	}
	
	@RequiresPermissions("ps:sCustomerService:edit")
	@RequestMapping(value = "delete")
	public String delete(SCustomerService sCustomerService, RedirectAttributes redirectAttributes) {
		sCustomerServiceService.delete(sCustomerService);
		addMessage(redirectAttributes, "删除客服表成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sCustomerService/?repage";
	}

}