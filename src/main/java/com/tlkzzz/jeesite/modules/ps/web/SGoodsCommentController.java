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
import com.tlkzzz.jeesite.modules.ps.entity.SGoodsComment;
import com.tlkzzz.jeesite.modules.ps.service.SGoodsCommentService;

/**
 * 商品评论Controller
 * @author xrc
 * @version 2017-07-18
 */
@Controller
@RequestMapping(value = "${adminPath}/ps/sGoodsComment")
public class SGoodsCommentController extends BaseController {

	@Autowired
	private SGoodsCommentService sGoodsCommentService;
	
	@ModelAttribute
	public SGoodsComment get(@RequestParam(required=false) String id) {
		SGoodsComment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sGoodsCommentService.get(id);
		}
		if (entity == null){
			entity = new SGoodsComment();
		}
		return entity;
	}
	
	@RequiresPermissions("ps:sGoodsComment:view")
	@RequestMapping(value = {"list", ""})
	public String list(SGoodsComment sGoodsComment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SGoodsComment> page = sGoodsCommentService.findPage(new Page<SGoodsComment>(request, response), sGoodsComment); 
		model.addAttribute("page", page);
		return "modules/ps/sGoodsCommentList";
	}

	@RequiresPermissions("ps:sGoodsComment:view")
	@RequestMapping(value = "form")
	public String form(SGoodsComment sGoodsComment, Model model) {
		model.addAttribute("sGoodsComment", sGoodsComment);
		return "modules/ps/sGoodsCommentForm";
	}

	@RequiresPermissions("ps:sGoodsComment:edit")
	@RequestMapping(value = "save")
	public String save(SGoodsComment sGoodsComment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sGoodsComment)){
			return form(sGoodsComment, model);
		}
		sGoodsCommentService.save(sGoodsComment);
		addMessage(redirectAttributes, "保存商品评论成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sGoodsComment/?repage";
	}
	
	@RequiresPermissions("ps:sGoodsComment:edit")
	@RequestMapping(value = "delete")
	public String delete(SGoodsComment sGoodsComment, RedirectAttributes redirectAttributes) {
		sGoodsCommentService.delete(sGoodsComment);
		addMessage(redirectAttributes, "删除商品评论成功");
		return "redirect:"+Global.getAdminPath()+"/ps/sGoodsComment/?repage";
	}

}