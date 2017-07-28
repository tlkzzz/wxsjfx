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
import com.tlkzzz.jeesite.modules.ps.entity.SWithDraw;
import com.tlkzzz.jeesite.modules.ps.service.SWithDrawService;

/**
 * 提现记录Controller
 * @author xrc
 * @version 2017-07-28
 */
@Controller
@RequestMapping(value = "${adminPath}/ps/sWithDraw")
public class SWithDrawController extends BaseController {

	@Autowired
	private SWithDrawService sWithDrawService;
	
	@ModelAttribute
	public SWithDraw get(@RequestParam(required=false) String id) {
		SWithDraw entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sWithDrawService.get(id);
		}
		if (entity == null){
			entity = new SWithDraw();
		}
		return entity;
	}
	
	@RequiresPermissions("ps:sWithDraw:view")
	@RequestMapping(value = {"list", ""})
	public String list(SWithDraw sWithDraw, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SWithDraw> page = sWithDrawService.findPage(new Page<SWithDraw>(request, response), sWithDraw); 
		model.addAttribute("page", page);
		return "modules/ps/sWithDrawList";
	}

	@RequiresPermissions("ps:sWithDraw:view")
	@RequestMapping(value = "form")
	public String form(SWithDraw sWithDraw, Model model) {
		model.addAttribute("sWithDraw", sWithDraw);
		return "modules/ps/sWithDrawForm";
	}

	@RequiresPermissions("ps:sWithDraw:edit")
	@RequestMapping(value = "save")
	public String save(SWithDraw sWithDraw, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sWithDraw)){
			return form(sWithDraw, model);
		}
		sWithDrawService.save(sWithDraw);
		addMessage(redirectAttributes, "保存提现记录成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sWithDraw/?repage";
	}
	
	@RequiresPermissions("ps:sWithDraw:edit")
	@RequestMapping(value = "delete")
	public String delete(SWithDraw sWithDraw, RedirectAttributes redirectAttributes) {
		sWithDrawService.delete(sWithDraw);
		addMessage(redirectAttributes, "删除提现记录成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sWithDraw/?repage";
	}

}