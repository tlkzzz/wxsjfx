/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 会员Entity
 * @author xrc
 * @version 2017-07-18
 */
public class SMember extends DataEntity<SMember> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 会员名称
	private String loginName;		// 登录名称
	private String password;		// 密码
	private String trueName;		// 真实姓名
	private String loginKey;		// 三方登录凭证
	private String memberType;		// 会员类型
	private String photo;		// 头像
	private String email;		// 邮箱
	private String tel;		// 电话
	private String mobile;		// 手机
	private String loginIp;		// 最后登录IP
	private Date loginDate;		// 最后登录时间
	private String loginFlag;		// 是否允许登录标志
	private String sort;		// 排序
	
	public SMember() {
		super();
	}

	public SMember(String id){
		super(id);
	}

	@Length(min=1, max=100, message="会员名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="登录名称长度必须介于 0 和 100 之间")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Length(min=0, max=200, message="密码长度必须介于 0 和 200 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=100, message="真实姓名长度必须介于 0 和 100 之间")
	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	@Length(min=0, max=200, message="三方登录凭证长度必须介于 0 和 200 之间")
	public String getLoginKey() {
		return loginKey;
	}

	public void setLoginKey(String loginKey) {
		this.loginKey = loginKey;
	}
	
	@Length(min=0, max=11, message="会员类型长度必须介于 0 和 11 之间")
	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Length(min=0, max=100, message="邮箱长度必须介于 0 和 100 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=100, message="电话长度必须介于 0 和 100 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=100, message="手机长度必须介于 0 和 100 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=100, message="最后登录IP长度必须介于 0 和 100 之间")
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
	@Length(min=1, max=11, message="是否允许登录标志长度必须介于 1 和 11 之间")
	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}
	
	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}