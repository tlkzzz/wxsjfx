/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ps.entity.SReceipt;

/**
 * 收款表DAO接口
 * @author xlc
 * @version 2017-07-19
 */
@MyBatisDao
public interface SReceiptDao extends CrudDao<SReceipt> {
	
}