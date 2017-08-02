/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ps.entity.SReceipt;
import com.tlkzzz.jeesite.modules.ps.dao.SReceiptDao;

/**
 * 收款表Service
 * @author xlc
 * @version 2017-07-19
 */
@Service
@Transactional(readOnly = true)
public class SReceiptService extends CrudService<SReceiptDao, SReceipt> {

	public SReceipt get(String id) {
		return super.get(id);
	}
	
	public List<SReceipt> findList(SReceipt sReceipt) {
		return super.findList(sReceipt);
	}
	
	public Page<SReceipt> findPage(Page<SReceipt> page, SReceipt sReceipt) {
		return super.findPage(page, sReceipt);
	}
	
	@Transactional(readOnly = false)
	public void save(SReceipt sReceipt) {
		super.save(sReceipt);
	}

	@Transactional(readOnly = false)
	public SReceipt insertByTotal(String total){
		SReceipt receipt = new SReceipt();
		receipt.preInsert();
		receipt.setReceivableMoney(total);
		receipt.setZfState("0");
		receipt.setTcState("0");
		dao.insert(receipt);
		return receipt;
	}

	@Transactional(readOnly = false)
	public SReceipt updateByTotal(SReceipt receipt,String snKey,String total){
		if(receipt==null)return null;
		receipt.preUpdate();
		receipt.setSnKey(snKey);
		receipt.setRevenueMoney(total);
		receipt.setReceiptDate(new Date());
		receipt.setZfState("1");
		receipt.setTcState("1");
		dao.update(receipt);
		return receipt;
	}
	
	@Transactional(readOnly = false)
	public void delete(SReceipt sReceipt) {
		super.delete(sReceipt);
	}

	@Transactional(readOnly = false)
	public void updateTc(SReceipt sReceipt){ dao.updateTc(sReceipt);}
	
}