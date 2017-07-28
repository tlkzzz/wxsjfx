/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ps.entity.SWithDraw;

/**
 * 提现记录DAO接口
 * @author xrc
 * @version 2017-07-28
 */
@MyBatisDao
public interface SWithDrawDao extends CrudDao<SWithDraw> {
	
}