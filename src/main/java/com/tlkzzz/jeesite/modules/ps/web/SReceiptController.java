/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ps.entity.*;
import com.tlkzzz.jeesite.modules.ps.service.SMemberCommissionService;
import com.tlkzzz.jeesite.modules.ps.service.SMemberRelationService;
import com.tlkzzz.jeesite.modules.ps.service.SProportionCommissionService;
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
import com.tlkzzz.jeesite.modules.ps.service.SReceiptService;

import java.util.List;

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
	@Autowired
	private SMemberCommissionService sMemberCommissionService;
	@Autowired
	private SMemberRelationService sMemberRelationService;
	@Autowired
	private SProportionCommissionService sProportionCommissionService;
	
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
		model.addAttribute("sReceipt",sReceipt);
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

	@RequiresPermissions("ps:sReceipt:view")
	@RequestMapping(value = "tcAdd")
	public String tcAdd(SReceipt sReceipt) {
		String btcr=sReceipt.getCreateBy().getId();   //被提成人
		String userId=sReceipt.getCreateBy().getId(); //购买人用户名
		String shje=sReceipt.getRevenueMoney();		//实际收款
		String reId=sReceipt.getId();				//收款单id
		List<SProportionCommission> sProportionCommissionList=sProportionCommissionService.findList(new SProportionCommission());
//		List<SMemberCommission> sMemberCommissionList=sMemberCommissionService.findList(new SMemberCommission());
		for(int i=0;i<sProportionCommissionList.size();i++){
				SMemberRelation sMemberRelation=new SMemberRelation();
				sMemberRelation.setNewMember(new SMember(userId));
				List<SMemberRelation> sMemberRelationList=sMemberRelationService.findList(sMemberRelation);
				if(sMemberRelationList.size()!=0){
					userId=sMemberRelationList.get(0).getOldMember().getId();
					SMemberCommission sMemberCommission=new SMemberCommission();
					sMemberCommission.setOldMemberId(userId);
					sMemberCommission.setNewMemberId(btcr);
					sMemberCommission.setReceipt(new SReceipt(reId));
					Double sh=Double.parseDouble(shje);
					Double tcbl=Double.parseDouble(sProportionCommissionList.get(i).getCommission());
					Double tc=sh*tcbl/100;
					sMemberCommission.setTotal(tc.toString());
					sMemberCommissionService.save(sMemberCommission);
					/**
					 * 提成表添加完成，预留支付接口
					 * */
					sReceiptService.updateTc(sReceipt);//更新提成表提成状态
				}
		}
		return "modules/ps/sReceiptList";
	}

}