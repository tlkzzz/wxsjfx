/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ps.entity.SSpecGener;
import com.tlkzzz.jeesite.modules.ps.dao.SSpecGenerDao;

/**
 * 规格类型中间表Service
 * @author szx
 * @version 2017-07-19
 */
@Service
@Transactional(readOnly = true)
public class SSpecGenerService extends CrudService<SSpecGenerDao, SSpecGener> {

	public SSpecGener get(String id) {
		return super.get(id);
	}
	
	public List<SSpecGener> findList(SSpecGener sSpecGener) {
		return super.findList(sSpecGener);
	}
	
	public Page<SSpecGener> findPage(Page<SSpecGener> page, SSpecGener sSpecGener) {
		return super.findPage(page, sSpecGener);
	}
	
	@Transactional(readOnly = false)
	public void save(SSpecGener sSpecGener) {
		super.save(sSpecGener);
	}
	
	@Transactional(readOnly = false)
	public void delete(SSpecGener sSpecGener) {
		super.delete(sSpecGener);
	}
	
}