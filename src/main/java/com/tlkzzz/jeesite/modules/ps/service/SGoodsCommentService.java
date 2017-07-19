/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.service;

import java.util.Date;
import java.util.List;

import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ps.entity.SGoodsComment;
import com.tlkzzz.jeesite.modules.ps.dao.SGoodsCommentDao;

/**
 * 商品评论Service
 * @author xrc
 * @version 2017-07-18
 */
@Service
@Transactional(readOnly = true)
public class SGoodsCommentService extends CrudService<SGoodsCommentDao, SGoodsComment> {

	public SGoodsComment get(String id) {
		return super.get(id);
	}
	
	public List<SGoodsComment> findList(SGoodsComment sGoodsComment) {
		return super.findList(sGoodsComment);
	}
	
	public Page<SGoodsComment> findPage(Page<SGoodsComment> page, SGoodsComment sGoodsComment) {
		return super.findPage(page, sGoodsComment);
	}
	
	@Transactional(readOnly = false)
	public void save(SGoodsComment sGoodsComment) {
		super.save(sGoodsComment);
	}

	@Transactional(readOnly = false)
	public void addReply(SGoodsComment sGoodsComment) {
		sGoodsComment.setUpdateDate(new Date());
		sGoodsComment.setUpdateBy(UserUtils.getUser());
		dao.addReply(sGoodsComment);
	}
	
	@Transactional(readOnly = false)
	public void delete(SGoodsComment sGoodsComment) {
		super.delete(sGoodsComment);
	}
	
}