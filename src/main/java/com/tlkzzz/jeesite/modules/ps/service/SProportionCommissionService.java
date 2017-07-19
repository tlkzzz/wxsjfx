/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ps.entity.SProportionCommission;
import com.tlkzzz.jeesite.modules.ps.dao.SProportionCommissionDao;

/**
 * 提成比例Service
 * @author xlc
 * @version 2017-07-19
 */
@Service
@Transactional(readOnly = true)
public class SProportionCommissionService extends CrudService<SProportionCommissionDao, SProportionCommission> {

	public SProportionCommission get(String id) {
		return super.get(id);
	}
	
	public List<SProportionCommission> findList(SProportionCommission sProportionCommission) {
		return super.findList(sProportionCommission);
	}
	
	public Page<SProportionCommission> findPage(Page<SProportionCommission> page, SProportionCommission sProportionCommission) {
		return super.findPage(page, sProportionCommission);
	}
	
	@Transactional(readOnly = false)
	public void save(SProportionCommission sProportionCommission) {
		super.save(sProportionCommission);
	}
	
	@Transactional(readOnly = false)
	public void delete(SProportionCommission sProportionCommission) {
		super.delete(sProportionCommission);
	}
	
}