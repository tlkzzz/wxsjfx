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
	private SMember member;		// 会员
	private Area area;			// 区域
	private String address;		// 详细地址
	private String isDefault;	// 默认地址
	private String code;		// 邮编
	private String tel;			// 电话
	private String sort;		// 排序
	private String shr;    		//收货人
	
	public SAddress() {
		super();
	}

	public SAddress(String id){
		super(id);
	}

	@NotNull(message = "会员不能为空")
	public SMember getMember() {
		return member;
	}

	public void setMember(SMember member) {
		this.member = member;
	}
	
	@NotNull(message="区域不能为空")
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}
}