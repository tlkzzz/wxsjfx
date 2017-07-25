/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 购物车Entity
 * @author xlc
 * @version 2017-07-21
 */
public class SShop extends DataEntity<SShop> {
	
	private static final long serialVersionUID = 1L;
	private String orderNo;		// 订单编号
	private SGoods goods;		// 商品ID
	private String specIds;		// 规格ids
	private String num;		// 数量
	private String price;		// 价格
	
	public SShop() {
		super();
	}

	public SShop(String id){
		super(id);
	}

	@Length(min=1, max=100, message="订单编号长度必须介于 1 和 100 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public SGoods getGoods() {
		return goods;
	}

	public void setGoods(SGoods goods) {
		this.goods = goods;
	}
	
	public String getSpecIds() {
		return specIds;
	}

	public void setSpecIds(String specIds) {
		this.specIds = specIds;
	}
	
	@Length(min=1, max=11, message="数量长度必须介于 1 和 11 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
}