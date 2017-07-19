/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ps.entity.SSpec;
import com.tlkzzz.jeesite.modules.ps.dao.SSpecDao;

/**
 * 规格Service
 * @author szx
 * @version 2017-07-18
 */
@Service
@Transactional(readOnly = true)
public class SSpecService extends CrudService<SSpecDao, SSpec> {

	public SSpec get(String id) {
		return super.get(id);
	}
	
	public List<SSpec> findList(SSpec sSpec) {
		return super.findList(sSpec);
	}
	
	public Page<SSpec> findPage(Page<SSpec> page, SSpec sSpec) {
		return super.findPage(page, sSpec);
	}
	
	@Transactional(readOnly = false)
	public void save(SSpec sSpec) {
		super.save(sSpec);
	}
	
	@Transactional(readOnly = false)
	public void delete(SSpec sSpec) {
		super.delete(sSpec);
	}
	
}