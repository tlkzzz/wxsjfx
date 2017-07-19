/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ps.entity.SGoods;
import com.tlkzzz.jeesite.modules.ps.dao.SGoodsDao;

/**
 * 商品Service
 * @author xrc
 * @version 2017-07-18
 */
@Service
@Transactional(readOnly = true)
public class SGoodsService extends CrudService<SGoodsDao, SGoods> {

	public SGoods get(String id) {
		return super.get(id);
	}
	
	public List<SGoods> findList(SGoods sGoods) {
		return super.findList(sGoods);
	}
	
	public Page<SGoods> findPage(Page<SGoods> page, SGoods sGoods) {
		return super.findPage(page, sGoods);
	}
	
	@Transactional(readOnly = false)
	public void save(SGoods sGoods) {
		super.save(sGoods);
	}
	
	@Transactional(readOnly = false)
	public void delete(SGoods sGoods) {
		super.delete(sGoods);
	}
	
}