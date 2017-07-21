/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ps.entity.SGenre;
import com.tlkzzz.jeesite.modules.ps.service.SGenreService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tlkzzz.jeesite.common.config.Global;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ps.entity.SGoodsClass;
import com.tlkzzz.jeesite.modules.ps.service.SGoodsClassService;

/**
 * 商品分类管理Controller
 * @author szx
 * @version 2017-07-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ps/sGoodsClass")
public class SGoodsClassController extends BaseController {

	@Autowired
	private SGoodsClassService sGoodsClassService;
	@Autowired
	private SGenreService sGenreService;
	
	@ModelAttribute
	public SGoodsClass get(@RequestParam(required=false) String id) {
		SGoodsClass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sGoodsClassService.get(id);
		}
		if (entity == null){
			entity = new SGoodsClass();
		}
		return entity;
	}
	
	@RequiresPermissions("ps:sGoodsClass:view")
	@RequestMapping(value = {"list", ""})
	public String list(SGoodsClass sGoodsClass, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<SGoodsClass> list = sGoodsClassService.findList(sGoodsClass); 
		model.addAttribute("sGoodsClass", sGoodsClass);
		model.addAttribute("list", list);
		return "modules/ps/sGoodsClassList";
	}

	@RequiresPermissions("ps:sGoodsClass:view")
	@RequestMapping(value = "form")
	public String form(SGoodsClass sGoodsClass, Model model) {
		if (sGoodsClass.getParent()!=null && StringUtils.isNotBlank(sGoodsClass.getParent().getId())){
			sGoodsClass.setParent(sGoodsClassService.get(sGoodsClass.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(sGoodsClass.getId())){
				SGoodsClass sGoodsClassChild = new SGoodsClass();
				sGoodsClassChild.setParent(new SGoodsClass(sGoodsClass.getParent().getId()));
				List<SGoodsClass> list = sGoodsClassService.findList(sGoodsClass); 
				if (list.size() > 0){
					sGoodsClass.setSort(list.get(list.size()-1).getSort());
					if (sGoodsClass.getSort() != null){
						sGoodsClass.setSort(sGoodsClass.getSort() + 30);
					}
				}
			}
		}
		if (sGoodsClass.getSort() == null){
			sGoodsClass.setSort(30);
		}
		model.addAttribute("genreList", sGenreService.findList(new SGenre()));
		model.addAttribute("sGoodsClass", sGoodsClass);
		return "modules/ps/sGoodsClassForm";
	}

	@RequiresPermissions("ps:sGoodsClass:edit")
	@RequestMapping(value = "save")
	public String save(SGoodsClass sGoodsClass, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sGoodsClass)){
			return form(sGoodsClass, model);
		}
		sGoodsClassService.save(sGoodsClass);
		addMessage(redirectAttributes, "保存商品分类管理成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sGoodsClass/?repage";
	}
	
	@RequiresPermissions("ps:sGoodsClass:edit")
	@RequestMapping(value = "delete")
	public String delete(SGoodsClass sGoodsClass, RedirectAttributes redirectAttributes) {
		sGoodsClassService.delete(sGoodsClass);
		addMessage(redirectAttributes, "删除商品分类管理成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sGoodsClass/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<SGoodsClass> list = sGoodsClassService.findList(new SGoodsClass());
		for (int i=0; i<list.size(); i++){
			SGoodsClass e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}