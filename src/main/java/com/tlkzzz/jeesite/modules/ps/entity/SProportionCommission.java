/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 提成比例Entity
 * @author xlc
 * @version 2017-07-19
 */
public class SProportionCommission extends DataEntity<SProportionCommission> {
	
	private static final long serialVersionUID = 1L;
	private String commission;		// 提成比例
	private String sort;		// 排序
	
	public SProportionCommission() {
		super();
	}

	public SProportionCommission(String id){
		super(id);
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}
	
	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}