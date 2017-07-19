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
	private SMember oldMember;		// 老会员
	private SMember newMember;		// 新会员
	private Date buildDate;		// 建立关系时间
	private String sort;		// 排序
	
	public SMemberRelation() {
		super();
	}

	public SMemberRelation(String id){
		super(id);
	}

	@NotNull(message="老会员不能为空")
	public SMember getOldMember() {
		return oldMember;
	}

	public void setOldMember(SMember oldMember) {
		this.oldMember = oldMember;
	}

	@NotNull(message="新会员不能为空")
	public SMember getNewMember() {
		return newMember;
	}

	public void setNewMember(SMember newMember) {
		this.newMember = newMember;
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