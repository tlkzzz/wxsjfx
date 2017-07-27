/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 收款表Entity
 * @author xlc
 * @version 2017-07-19
 */
public class SReceipt extends DataEntity<SReceipt> {
	
	private static final long serialVersionUID = 1L;
	private SOrder order;		// 订单
	private String receivableMoney;		// 应收金额
	private String revenueMoney;		// 实收金额
	private Date receiptDate;		// 收款时间
	
	public SReceipt() {
		super();
	}

	public SReceipt(String id){
		super(id);
	}

	public SOrder getOrder() {
		return order;
	}

	public void setOrder(SOrder order) {
		this.order = order;
	}
	
	public String getReceivableMoney() {
		return receivableMoney;
	}

	public void setReceivableMoney(String receivableMoney) {
		this.receivableMoney = receivableMoney;
	}
	
	public String getRevenueMoney() {
		return revenueMoney;
	}

	public void setRevenueMoney(String revenueMoney) {
		this.revenueMoney = revenueMoney;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="收款时间不能为空")
	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}
	
}