/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.entity;

import org.hibernate.validator.constraints.Length;
import com.tlkzzz.jeesite.modules.sys.entity.Area;
import javax.validation.constraints.NotNull;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 送货地址Entity
 * @author xrc
 * @version 2017-07-18
 */
public class SAddress extends DataEntity<SAddress> {
	
	private static final long serialVersionUID = 1L;
	private String memberId;		// 会员ID
	private Area area;		// 区域ID
	private String address;		// 详细地址
	private String isDefault;		// 默认地址
	private String sort;		// 排序
	
	public SAddress() {
		super();
	}

	public SAddress(String id){
		super(id);
	}

	@Length(min=1, max=64, message="会员ID长度必须介于 1 和 64 之间")
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@NotNull(message="区域ID不能为空")
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=11, message="默认地址长度必须介于 0 和 11 之间")
	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	
	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}