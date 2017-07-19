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
import com.tlkzzz.jeesite.modules.ps.entity.SAddress;
import com.tlkzzz.jeesite.modules.ps.service.SAddressService;

/**
 * 送货地址Controller
 * @author xrc
 * @version 2017-07-18
 */
@Controller
@RequestMapping(value = "${adminPath}/ps/sAddress")
public class SAddressController extends BaseController {

	@Autowired
	private SAddressService sAddressService;
	
	@ModelAttribute
	public SAddress get(@RequestParam(required=false) String id) {
		SAddress entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sAddressService.get(id);
		}
		if (entity == null){
			entity = new SAddress();
		}
		return entity;
	}
	
	@RequiresPermissions("ps:sAddress:view")
	@RequestMapping(value = {"list", ""})
	public String list(SAddress sAddress, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SAddress> page = sAddressService.findPage(new Page<SAddress>(request, response), sAddress); 
		model.addAttribute("page", page);
		return "modules/ps/sAddressList";
	}

	@RequiresPermissions("ps:sAddress:view")
	@RequestMapping(value = "form")
	public String form(SAddress sAddress, Model model) {
		model.addAttribute("sAddress", sAddress);
		return "modules/ps/sAddressForm";
	}

	@RequiresPermissions("ps:sAddress:edit")
	@RequestMapping(value = "save")
	public String save(SAddress sAddress, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sAddress)){
			return form(sAddress, model);
		}
		sAddressService.save(sAddress);
		addMessage(redirectAttributes, "保存送货地址成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sAddress/?repage";
	}
	
	@RequiresPermissions("ps:sAddress:edit")
	@RequestMapping(value = "delete")
	public String delete(SAddress sAddress, RedirectAttributes redirectAttributes) {
		sAddressService.delete(sAddress);
		addMessage(redirectAttributes, "删除送货地址成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sAddress/?repage";
	}

}