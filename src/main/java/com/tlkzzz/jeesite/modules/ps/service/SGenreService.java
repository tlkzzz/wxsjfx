/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.service;

import java.util.List;

import com.tlkzzz.jeesite.modules.ps.dao.SSpecClassDao;
import com.tlkzzz.jeesite.modules.ps.dao.SSpecDao;
import com.tlkzzz.jeesite.modules.ps.entity.SSpec;
import com.tlkzzz.jeesite.modules.ps.entity.SSpecClass;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private SSpecClassDao sSpecClassDao;
	@Autowired
	private SSpecDao sSpecDao;

	public SGenre get(String id) {
		return super.get(id);
	}

	/**
	 * 查询类型中的规格分类和规格
	 * @param id 类型ID
	 * @return
	 */
	public SGenre getAll(String id) {
		SGenre genre = super.get(id);
		genre.setSpecClassList(sSpecClassDao.ggfindList(id));
		List<SSpec> specList = sSpecDao.findAllList(new SSpec());
		for(SSpecClass ss: genre.getSpecClassList()){
			for(SSpec sc: specList){
				if(ss.getId().equals(sc.getSpecClassId())){
					ss.getsSpecList().add(sc);
				}
			}
		}
		return genre;
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