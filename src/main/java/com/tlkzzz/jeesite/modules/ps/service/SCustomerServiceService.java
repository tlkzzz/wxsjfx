/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ps.entity.SCustomerService;
import com.tlkzzz.jeesite.modules.ps.dao.SCustomerServiceDao;

/**
 * 客服表Service
 * @author xlc
 * @version 2017-07-19
 */
@Service
@Transactional(readOnly = true)
public class SCustomerServiceService extends CrudService<SCustomerServiceDao, SCustomerService> {

	public SCustomerService get(String id) {
		return super.get(id);
	}
	
	public List<SCustomerService> findList(SCustomerService sCustomerService) {
		return super.findList(sCustomerService);
	}
	
	public Page<SCustomerService> findPage(Page<SCustomerService> page, SCustomerService sCustomerService) {
		return super.findPage(page, sCustomerService);
	}
	
	@Transactional(readOnly = false)
	public void save(SCustomerService sCustomerService) {
		super.save(sCustomerService);
	}
	
	@Transactional(readOnly = false)
	public void delete(SCustomerService sCustomerService) {
		super.delete(sCustomerService);
	}
	
}