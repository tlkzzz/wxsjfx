/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 规格Entity
 * @author szx
 * @version 2017-07-18
 */
public class SSpec extends DataEntity<SSpec> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 规格名称
	private String code;		// 规格
	private String specClassId;		// 规格分类
	private String sort;		// 排序
	private SSpecClass sSpecClass; //规格类型

	public SSpec() {
		super();
	}

	public SSpec(String id){
		super(id);
	}

	@Length(min=0, max=100, message="规格名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=100, message="规格长度必须介于 1 和 100 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=1, max=64, message="规格分类长度必须介于 1 和 64 之间")
	public String getSpecClassId() {
		return specClassId;
	}

	public void setSpecClassId(String specClassId) {
		this.specClassId = specClassId;
	}
	
	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public SSpecClass getsSpecClass() {
		return sSpecClass;
	}

	public void setsSpecClass(SSpecClass sSpecClass) {
		this.sSpecClass = sSpecClass;
	}
}