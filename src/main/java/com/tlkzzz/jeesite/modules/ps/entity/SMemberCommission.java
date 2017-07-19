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
	private String oldMemberId;		// 老会员ID
	private String newMemberId;		// 被提成会员ID
	private SOrder orderId;		// 订单ID
	private String total;		// 提成金额
	private String sort;		// 排序
	
	public SMemberCommission() {
		super();
	}

	public SMemberCommission(String id){
		super(id);
	}

	@Length(min=1, max=64, message="老会员ID长度必须介于 1 和 64 之间")
	public String getOldMemberId() {
		return oldMemberId;
	}

	public void setOldMemberId(String oldMemberId) {
		this.oldMemberId = oldMemberId;
	}
	
	@Length(min=1, max=64, message="被提成会员ID长度必须介于 1 和 64 之间")
	public String getNewMemberId() {
		return newMemberId;
	}

	public void setNewMemberId(String newMemberId) {
		this.newMemberId = newMemberId;
	}
	
	@Length(min=1, max=64, message="订单ID长度必须介于 1 和 64 之间")
	public SOrder getOrderId() {
		return orderId;
	}

	public void setOrderId(SOrder orderId) {
		this.orderId = orderId;
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