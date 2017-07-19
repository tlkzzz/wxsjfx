/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ps.entity.SMemberRelation;
import com.tlkzzz.jeesite.modules.ps.dao.SMemberRelationDao;

/**
 * 会员关系Service
 * @author xrc
 * @version 2017-07-18
 */
@Service
@Transactional(readOnly = true)
public class SMemberRelationService extends CrudService<SMemberRelationDao, SMemberRelation> {

	public SMemberRelation get(String id) {
		return super.get(id);
	}
	
	public List<SMemberRelation> findList(SMemberRelation sMemberRelation) {
		return super.findList(sMemberRelation);
	}
	
	public Page<SMemberRelation> findPage(Page<SMemberRelation> page, SMemberRelation sMemberRelation) {
		return super.findPage(page, sMemberRelation);
	}
	
	@Transactional(readOnly = false)
	public void save(SMemberRelation sMemberRelation) {
		super.save(sMemberRelation);
	}
	
	@Transactional(readOnly = false)
	public void delete(SMemberRelation sMemberRelation) {
		super.delete(sMemberRelation);
	}
	
}