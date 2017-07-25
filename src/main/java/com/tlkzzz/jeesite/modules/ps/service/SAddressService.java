/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ps.entity.SAddress;
import com.tlkzzz.jeesite.modules.ps.dao.SAddressDao;

/**
 * 送货地址Service
 * @author xrc
 * @version 2017-07-18
 */
@Service
@Transactional(readOnly = true)
public class SAddressService extends CrudService<SAddressDao, SAddress> {

	public SAddress get(String id) {
		return super.get(id);
	}
	
	public List<SAddress> findList(SAddress sAddress) {
		return super.findList(sAddress);
	}

	public List<SAddress> idsFindList(SAddress sAddress) {
		return dao.idsFindList(sAddress);
	}

	public Page<SAddress> findPage(Page<SAddress> page, SAddress sAddress) {
		return super.findPage(page, sAddress);
	}
	
	@Transactional(readOnly = false)
	public void save(SAddress sAddress) {
		super.save(sAddress);
	}

	@Transactional(readOnly = false)
	public void updatess(SAddress sAddress){
		dao.updatess(sAddress);
	}

	@Transactional(readOnly = false)
	public void updateOne(SAddress sAddress){
		dao.updateOne(sAddress);
	}
	@Transactional(readOnly = false)
	public void updateTwo(SAddress sAddress){
		dao.updateTwo(sAddress);
	}

	@Transactional(readOnly = false)
	public void delete(SAddress sAddress) {
		super.delete(sAddress);
	}
	
}