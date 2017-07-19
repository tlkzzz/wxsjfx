/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 规格类型中间表Entity
 * @author szx
 * @version 2017-07-19
 */
public class SSpecGener extends DataEntity<SSpecGener> {
	
	private static final long serialVersionUID = 1L;
	private String generId;		// 类型ID
	private String specId;		// 规格分类ID
	
	public SSpecGener() {
		super();
	}

	public SSpecGener(String id){
		super(id);
	}

	@Length(min=0, max=64, message="类型ID长度必须介于 0 和 64 之间")
	public String getGenerId() {
		return generId;
	}

	public void setGenerId(String generId) {
		this.generId = generId;
	}
	
	@Length(min=0, max=64, message="规格分类ID长度必须介于 0 和 64 之间")
	public String getSpecId() {
		return specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}
	
}