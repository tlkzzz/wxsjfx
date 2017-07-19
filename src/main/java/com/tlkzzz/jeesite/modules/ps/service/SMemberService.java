/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.service;

import java.util.List;

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
	
	public List<SMember> findList(SMember sMember) {
		return super.findList(sMember);
	}
	
	public Page<SMember> findPage(Page<SMember> page, SMember sMember) {
		return super.findPage(page, sMember);
	}
	
	@Transactional(readOnly = false)
	public void save(SMember sMember) {
		super.save(sMember);
	}
	
	@Transactional(readOnly = false)
	public void delete(SMember sMember) {
		super.delete(sMember);
	}
	
}