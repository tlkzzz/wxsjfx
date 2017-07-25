/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.service;

import java.util.Date;
import java.util.List;

import com.tlkzzz.jeesite.modules.ps.dao.SShopDao;
import com.tlkzzz.jeesite.modules.ps.entity.SGoods;
import com.tlkzzz.jeesite.modules.ps.entity.SShop;
import org.h2.util.New;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private SShopDao shopDao;

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

	public List<SShop> findList(SShop sShop) {
		return shopDao.findList(sShop);
	}

	public List<SShop> findConfirmOrderList(SShop sShop){
		return shopDao.findConfirmOrderList(sShop);
	}

	@Transactional(readOnly = false)
	public void savelist(SGoods sGoods) {
        SShop sShop=new SShop();
        sShop.setGoods(sGoods);
        sShop.setOrderNo("M"+ new Date().getTime());
        sShop.setSpecIds(sGoods.getSpecIds());
        sShop.setCreateDate(sGoods.getCreateDate());
        sShop.setCreateBy(sGoods.getCreateBy());
		sShop.setNum("1");
		sShop.setPrice(sGoods.getPrice());
		sShop.setId("C"+new Date().getTime()+"1");
		shopDao.insert(sShop);
	}

}