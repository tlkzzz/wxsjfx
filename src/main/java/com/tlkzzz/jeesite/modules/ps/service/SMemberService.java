/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.service;

import java.util.Date;
import java.util.List;

import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.common.web.Servlets;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ps.entity.SMember;
import com.tlkzzz.jeesite.modules.ps.dao.SMemberDao;

/**
 * 会员Service
 * @author xrc
 * @version 2017-07-18
 */
@Service
@Transactional(readOnly = true)
public class SMemberService extends CrudService<SMemberDao, SMember> {

	public SMember get(String id) {
		return super.get(id);
	}

	public SMember getByKey(String loginKey) {
		return dao.getByKey(loginKey);
	}
	
	public List<SMember> findList(SMember sMember) {
		return super.findList(sMember);
	}
	
	public Page<SMember> findPage(Page<SMember> page, SMember sMember) {
		return super.findPage(page, sMember);
	}

	@Transactional(readOnly = false)
	public SMember saveUserByJson(String json){
		if(StringUtils.isBlank(json))return null;
		JSONObject object = JSONObject.fromObject(json);
		if(object==null)return null;
		SMember member = getByKey(object.get("openid").toString().trim());
		if(member == null){
			member = new SMember();
			member.setName(object.get("nickname").toString().trim());
			member.setLoginName(member.getName());
			member.setLoginKey(object.get("openid").toString().trim());
			member.setMemberType(object.get("sex").toString().trim());
			member.setPhoto(object.get("headimgurl").toString().trim());
			member.setLoginFlag("1");
		}else {
			member.setName(object.get("nickname").toString().trim());
			member.setLoginName(member.getName());
			member.setMemberType(object.get("sex").toString().trim());
			member.setPhoto(object.get("headimgurl").toString().trim());
		}
		member.setLoginDate(new Date());
		member.setLoginIp(StringUtils.getRemoteAddr(Servlets.getRequest()));
		save(member);
		return member;
	}
	
	@Transactional(readOnly = false)
	public void save(SMember sMember) {
		super.save(sMember);
	}

	@Transactional(readOnly = false)
	public void balanceUp(SMember sMember) {
		dao.balanceUp(sMember);
	}

	@Transactional(readOnly = false)
	public void updateMobile(String id,String mobile) {
		dao.updateMobile(id,mobile);
	}
	
	@Transactional(readOnly = false)
	public void delete(SMember sMember) {
		super.delete(sMember);
	}
	
}