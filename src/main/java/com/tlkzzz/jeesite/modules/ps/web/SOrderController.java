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
import com.tlkzzz.jeesite.modules.ps.entity.SOrder;
import com.tlkzzz.jeesite.modules.ps.service.SOrderService;

/**
 * 订单Controller
 * @author xlc
 * @version 2017-07-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ps/sOrder")
public class SOrderController extends BaseController {

	@Autowired
	private SOrderService sOrderService;
	
	@ModelAttribute
	public SOrder get(@RequestParam(required=false) String id) {
		SOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sOrderService.get(id);
		}
		if (entity == null){
			entity = new SOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("ps:sOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(SOrder sOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SOrder> page = sOrderService.findPage(new Page<SOrder>(request, response), sOrder); 
		model.addAttribute("page", page);
		model.addAttribute("sOrder",sOrder);
		return "modules/ps/sOrderList";
	}

	@RequiresPermissions("ps:sOrder:view")
	@RequestMapping(value = "form")
	public String form(SOrder sOrder, Model model) {
		model.addAttribute("sOrder", sOrder);
		return "modules/ps/sOrderForm";
	}

	@RequiresPermissions("ps:sOrder:edit")
	@RequestMapping(value = "save")
	public String save(SOrder sOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sOrder)){
			return form(sOrder, model);
		}
		sOrderService.save(sOrder);
		addMessage(redirectAttributes, "保存订单成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sOrder/?repage";
	}
	
	@RequiresPermissions("ps:sOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(SOrder sOrder, RedirectAttributes redirectAttributes) {
		sOrderService.delete(sOrder);
		addMessage(redirectAttributes, "删除订单成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sOrder/?repage";
	}

}