/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ps.entity.SGoodsClass;
import com.tlkzzz.jeesite.modules.ps.dao.SGoodsClassDao;

/**
 * 商品分类管理Service
 * @author szx
 * @version 2017-07-19
 */
@Service
@Transactional(readOnly = true)
public class SGoodsClassService extends CrudService<SGoodsClassDao, SGoodsClass> {

	public SGoodsClass get(String id) {
		return super.get(id);
	}
	
	public List<SGoodsClass> findList(SGoodsClass sGoodsClass) {
		return super.findList(sGoodsClass);
	}
	
	public Page<SGoodsClass> findPage(Page<SGoodsClass> page, SGoodsClass sGoodsClass) {
		return super.findPage(page, sGoodsClass);
	}
	
	@Transactional(readOnly = false)
	public void save(SGoodsClass sGoodsClass) {
		super.save(sGoodsClass);
	}
	
	@Transactional(readOnly = false)
	public void delete(SGoodsClass sGoodsClass) {
		super.delete(sGoodsClass);
	}
	
}