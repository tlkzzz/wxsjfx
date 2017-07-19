/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ps.entity.SMemberCommission;
import com.tlkzzz.jeesite.modules.ps.dao.SMemberCommissionDao;

/**
 * 会员提成Service
 * @author xlc
 * @version 2017-07-19
 */
@Service
@Transactional(readOnly = true)
public class SMemberCommissionService extends CrudService<SMemberCommissionDao, SMemberCommission> {

	public SMemberCommission get(String id) {
		return super.get(id);
	}
	
	public List<SMemberCommission> findList(SMemberCommission sMemberCommission) {
		return super.findList(sMemberCommission);
	}
	
	public Page<SMemberCommission> findPage(Page<SMemberCommission> page, SMemberCommission sMemberCommission) {
		return super.findPage(page, sMemberCommission);
	}
	
	@Transactional(readOnly = false)
	public void save(SMemberCommission sMemberCommission) {
		super.save(sMemberCommission);
	}
	
	@Transactional(readOnly = false)
	public void delete(SMemberCommission sMemberCommission) {
		super.delete(sMemberCommission);
	}
	
}