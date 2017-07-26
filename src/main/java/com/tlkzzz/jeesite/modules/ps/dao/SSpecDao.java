/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ps.entity.SSpec;

import java.util.List;

/**
 * 规格DAO接口
 * @author szx
 * @version 2017-07-18
 */
@MyBatisDao
public interface SSpecDao extends CrudDao<SSpec> {

    /**
     * 通过ids查询ids中的规格列表
     * ids字符串存放在id中
     * @param sSpec
     * @return
     */
	List<SSpec> findListByIds(SSpec sSpec);
}