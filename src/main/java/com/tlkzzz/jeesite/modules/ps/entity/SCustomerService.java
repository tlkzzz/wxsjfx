/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 客服表Entity
 * @author xlc
 * @version 2017-07-19
 */
public class SCustomerService extends DataEntity<SCustomerService> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 客服名称
	private String state;		// 启用状态
	private String sort;		// 排序
	
	public SCustomerService() {
		super();
	}

	public SCustomerService(String id){
		super(id);
	}

	@Length(min=0, max=64, message="客服名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=11, message="启用状态长度必须介于 0 和 11 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}