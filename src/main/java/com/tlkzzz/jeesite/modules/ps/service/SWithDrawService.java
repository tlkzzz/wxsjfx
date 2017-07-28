/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ps.entity.SWithDraw;
import com.tlkzzz.jeesite.modules.ps.dao.SWithDrawDao;

/**
 * 提现记录Service
 * @author xrc
 * @version 2017-07-28
 */
@Service
@Transactional(readOnly = true)
public class SWithDrawService extends CrudService<SWithDrawDao, SWithDraw> {

	public SWithDraw get(String id) {
		return super.get(id);
	}
	
	public List<SWithDraw> findList(SWithDraw sWithDraw) {
		return super.findList(sWithDraw);
	}
	
	public Page<SWithDraw> findPage(Page<SWithDraw> page, SWithDraw sWithDraw) {
		return super.findPage(page, sWithDraw);
	}
	
	@Transactional(readOnly = false)
	public void save(SWithDraw sWithDraw) {
		super.save(sWithDraw);
	}
	
	@Transactional(readOnly = false)
	public void delete(SWithDraw sWithDraw) {
		super.delete(sWithDraw);
	}
	
}