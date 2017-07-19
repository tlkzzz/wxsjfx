/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ps.entity.SGenre;
import com.tlkzzz.jeesite.modules.ps.dao.SGenreDao;

/**
 * 类型管理Service
 * @author szx
 * @version 2017-07-19
 */
@Service
@Transactional(readOnly = true)
public class SGenreService extends CrudService<SGenreDao, SGenre> {

	public SGenre get(String id) {
		return super.get(id);
	}
	
	public List<SGenre> findList(SGenre sGenre) {
		return super.findList(sGenre);
	}
	
	public Page<SGenre> findPage(Page<SGenre> page, SGenre sGenre) {
		return super.findPage(page, sGenre);
	}
	
	@Transactional(readOnly = false)
	public void save(SGenre sGenre) {
		super.save(sGenre);
	}
	
	@Transactional(readOnly = false)
	public void delete(SGenre sGenre) {
		super.delete(sGenre);
	}
	
}