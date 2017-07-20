/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.service.TreeService;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ps.entity.SGoodsClass;
import com.tlkzzz.jeesite.modules.ps.dao.SGoodsClassDao;

/**
 * 商品分类管理Service
 * @author szx
 * @version 2017-07-19
 */
@Service
@Transactional(readOnly = true)
public class SGoodsClassService extends TreeService<SGoodsClassDao, SGoodsClass> {

	public SGoodsClass get(String id) {
		return super.get(id);
	}
	
	public List<SGoodsClass> findList(SGoodsClass sGoodsClass) {
		if (StringUtils.isNotBlank(sGoodsClass.getParentIds())){
			sGoodsClass.setParentIds(","+sGoodsClass.getParentIds()+",");
		}
		return super.findList(sGoodsClass);
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