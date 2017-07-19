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
 * 会员关系Entity
 * @author xrc
 * @version 2017-07-18
 */
public class SMemberRelation extends DataEntity<SMemberRelation> {
	
	private static final long serialVersionUID = 1L;
	private String oldMemberId;		// 老会员ID
	private String newMemberId;		// 新会员ID
	private Date buildDate;		// 建立关系时间
	private String sort;		// 排序
	
	public SMemberRelation() {
		super();
	}

	public SMemberRelation(String id){
		super(id);
	}

	@Length(min=1, max=64, message="老会员ID长度必须介于 1 和 64 之间")
	public String getOldMemberId() {
		return oldMemberId;
	}

	public void setOldMemberId(String oldMemberId) {
		this.oldMemberId = oldMemberId;
	}
	
	@Length(min=1, max=64, message="新会员ID长度必须介于 1 和 64 之间")
	public String getNewMemberId() {
		return newMemberId;
	}

	public void setNewMemberId(String newMemberId) {
		this.newMemberId = newMemberId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="建立关系时间不能为空")
	public Date getBuildDate() {
		return buildDate;
	}

	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}
	
	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}