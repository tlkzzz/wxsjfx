/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ps.entity.SSpecClass;
import com.tlkzzz.jeesite.modules.ps.entity.SSpecGener;
import com.tlkzzz.jeesite.modules.ps.service.SSpecClassService;
import com.tlkzzz.jeesite.modules.ps.service.SSpecGenerService;
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
import com.tlkzzz.jeesite.modules.ps.entity.SGenre;
import com.tlkzzz.jeesite.modules.ps.service.SGenreService;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型管理Controller
 * @author szx
 * @version 2017-07-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ps/sGenre")
public class SGenreController extends BaseController {

	@Autowired
	private SGenreService sGenreService;
	@Autowired
	private SSpecClassService sSpecClassService;
	@Autowired
	private SSpecGenerService sSpecGenerService;

	@ModelAttribute
	public SGenre get(@RequestParam(required=false) String id) {
		SGenre entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sGenreService.get(id);
		}
		if (entity == null){
			entity = new SGenre();
		}
		return entity;
	}
	
	@RequiresPermissions("ps:sGenre:view")
	@RequestMapping(value = {"list", ""})
	public String list(SGenre sGenre, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SGenre> page = sGenreService.findPage(new Page<SGenre>(request, response), sGenre); 
		model.addAttribute("sGenre", sGenre);
		model.addAttribute("page", page);
		return "modules/ps/sGenreList";
	}

	@RequiresPermissions("ps:sGenre:view")
	@RequestMapping(value = "form")
	public String form(SGenre sGenre, Model model) {
		List<SSpecClass> sSpecClassList=sSpecClassService.findList(new SSpecClass());
		SSpecGener sSpecGener=new SSpecGener();
		sSpecGener.setGenerId(sGenre.getId());
		List<SSpecGener> sSpecGenerList=sSpecGenerService.findList(sSpecGener);
		String ss = "";
		for(SSpecGener sg:sSpecGenerList){
			ss += (sg.getSpecId()+",");
		}/*
		for(int j=0;j<sSpecClassList.size();j++){
			for(int i=0;i<sSpecGenerList.size();i++){
				if(sSpecGenerList.get(i).getSpecId()==sSpecClassList.get(j).getId()){
					ss += (sSpecClassList.get(j).getId()+",");
				}
		}
		}*/
		sGenre.setSpecClass(ss);
		model.addAttribute("sGenre", sGenre);
		model.addAttribute("sSpecClassList", sSpecClassList);
		return "modules/ps/sGenreForm";
	}

	@RequiresPermissions("ps:sGenre:edit")
	@RequestMapping(value = "save")
	public String save(SGenre sGenre, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sGenre)){
			return form(sGenre, model);
		}
		sGenreService.save(sGenre);
		String[] SList=sGenre.getSpecClass().split(",");
		SSpecGener sSpecGener=new SSpecGener();
		sSpecGener.setGenerId(sGenre.getId());
		sSpecGenerService.deleteIn(sSpecGener);

		for(int i=0;i<SList.length;i++){
			sSpecGener.setGenerId(sGenre.getId());
			sSpecGener.setSpecId(SList[i]);
			sSpecGenerService.saveIn(sSpecGener);
		}
		addMessage(redirectAttributes, "保存类型管理成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sGenre/?repage";
	}
	
	@RequiresPermissions("ps:sGenre:edit")
	@RequestMapping(value = "delete")
	public String delete(SGenre sGenre, RedirectAttributes redirectAttributes) {
		sGenreService.delete(sGenre);
		addMessage(redirectAttributes, "删除类型管理成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sGenre/?repage";
	}

}