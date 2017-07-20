/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 类型管理Entity
 * @author szx
 * @version 2017-07-19
 */
public class SGenre extends DataEntity<SGenre> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String sort;		// 排序
	private String specClass;  //规格类型
	
	public SGenre() {
		super();
	}

	public SGenre(String id){
		super(id);
	}

	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSpecClass() {
		return specClass;
	}

	public void setSpecClass(String specClass) {
		this.specClass = specClass;
	}
}