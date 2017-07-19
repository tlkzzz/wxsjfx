/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.entity;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 商品分类管理Entity
 * @author szx
 * @version 2017-07-19
 */
public class SGoodsClass extends DataEntity<SGoodsClass> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private SGoodsClass parent;		// 父级ID
	private String parentIds;		// 父级ids
	private String generId;		// 类型ID
	private String sort;		// 排序
	
	public SGoodsClass() {
		super();
	}

	public SGoodsClass(String id){
		super(id);
	}

	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonBackReference
	public SGoodsClass getParent() {
		return parent;
	}

	public void setParent(SGoodsClass parent) {
		this.parent = parent;
	}
	
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=0, max=64, message="类型ID长度必须介于 0 和 64 之间")
	public String getGenerId() {
		return generId;
	}

	public void setGenerId(String generId) {
		this.generId = generId;
	}
	
	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}