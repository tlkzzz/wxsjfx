/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ps.entity.SOrder;
import com.tlkzzz.jeesite.modules.ps.dao.SOrderDao;

/**
 * 订单Service
 * @author xlc
 * @version 2017-07-19
 */
@Service
@Transactional(readOnly = true)
public class SOrderService extends CrudService<SOrderDao, SOrder> {

	public SOrder get(String id) {
		return super.get(id);
	}
	
	public List<SOrder> findList(SOrder sOrder) {
		return super.findList(sOrder);
	}
	
	public Page<SOrder> findPage(Page<SOrder> page, SOrder sOrder) {
		return super.findPage(page, sOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(SOrder sOrder) {
		super.save(sOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(SOrder sOrder) {
		super.delete(sOrder);
	}
	
}