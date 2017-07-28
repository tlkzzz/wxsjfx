/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.entity;


import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 提现记录Entity
 * @author xrc
 * @version 2017-07-28
 */
public class SWithDraw extends DataEntity<SWithDraw> {
	
	private static final long serialVersionUID = 1L;
	private String money;		// 提现金额
	private String state;		// 提现状态(1:申请,2:提现成功)

	public SWithDraw() {
		super();
	}

	public SWithDraw(String id){
		super(id);
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}