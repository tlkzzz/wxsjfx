/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 会员提成Entity
 * @author xlc
 * @version 2017-07-19
 */
public class SMemberCommission extends DataEntity<SMemberCommission> {
	
	private static final long serialVersionUID = 1L;
	private SMember oldMember;		// 老会员
	private SMember newMember;		// 被提成会员
	private SReceipt receipt;		// 收款
	private String total;		// 提成金额
	private String sort;		// 排序
	
	public SMemberCommission() {
		super();
	}

	public SMemberCommission(String id){
		super(id);
	}

	public SMember getOldMember() {
		return oldMember;
	}

	public void setOldMember(SMember oldMember) {
		this.oldMember = oldMember;
	}
	
	public SMember getNewMember() {
		return newMember;
	}

	public void setNewMember(SMember newMember) {
		this.newMember = newMember;
	}

	public SReceipt getReceipt() {
		return receipt;
	}

	public void setReceipt(SReceipt receipt) {
		this.receipt = receipt;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}