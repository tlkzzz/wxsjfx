/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ps.entity.SSpecClass;
import com.tlkzzz.jeesite.modules.ps.dao.SSpecClassDao;

/**
 * 规格分类Service
 * @author szx
 * @version 2017-07-19
 */
@Service
@Transactional(readOnly = true)
public class SSpecClassService extends CrudService<SSpecClassDao, SSpecClass> {

	public SSpecClass get(String id) {
		return super.get(id);
	}
	
	public List<SSpecClass> findList(SSpecClass sSpecClass) {
		return super.findList(sSpecClass);
	}
	
	public Page<SSpecClass> findPage(Page<SSpecClass> page, SSpecClass sSpecClass) {
		return super.findPage(page, sSpecClass);
	}
	
	@Transactional(readOnly = false)
	public void save(SSpecClass sSpecClass) {
		super.save(sSpecClass);
	}
	
	@Transactional(readOnly = false)
	public void delete(SSpecClass sSpecClass) {
		super.delete(sSpecClass);
	}
	
}