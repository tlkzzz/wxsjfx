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
import com.tlkzzz.jeesite.modules.ps.entity.SReceipt;
import com.tlkzzz.jeesite.modules.ps.service.SReceiptService;

/**
 * 收款表Controller
 * @author xlc
 * @version 2017-07-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ps/sReceipt")
public class SReceiptController extends BaseController {

	@Autowired
	private SReceiptService sReceiptService;
	
	@ModelAttribute
	public SReceipt get(@RequestParam(required=false) String id) {
		SReceipt entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sReceiptService.get(id);
		}
		if (entity == null){
			entity = new SReceipt();
		}
		return entity;
	}
	
	@RequiresPermissions("ps:sReceipt:view")
	@RequestMapping(value = {"list", ""})
	public String list(SReceipt sReceipt, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SReceipt> page = sReceiptService.findPage(new Page<SReceipt>(request, response), sReceipt); 
		model.addAttribute("page", page);
		return "modules/ps/sReceiptList";
	}

	@RequiresPermissions("ps:sReceipt:view")
	@RequestMapping(value = "form")
	public String form(SReceipt sReceipt, Model model) {
		model.addAttribute("sReceipt", sReceipt);
		return "modules/ps/sReceiptForm";
	}

	@RequiresPermissions("ps:sReceipt:edit")
	@RequestMapping(value = "save")
	public String save(SReceipt sReceipt, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sReceipt)){
			return form(sReceipt, model);
		}
		sReceiptService.save(sReceipt);
		addMessage(redirectAttributes, "保存收款表成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sReceipt/?repage";
	}
	
	@RequiresPermissions("ps:sReceipt:edit")
	@RequestMapping(value = "delete")
	public String delete(SReceipt sReceipt, RedirectAttributes redirectAttributes) {
		sReceiptService.delete(sReceipt);
		addMessage(redirectAttributes, "删除收款表成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sReceipt/?repage";
	}

}