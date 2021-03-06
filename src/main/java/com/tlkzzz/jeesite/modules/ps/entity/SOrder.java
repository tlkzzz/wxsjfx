/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;

/**
 * 订单Entity
 * @author xlc
 * @version 2017-07-19
 */
public class SOrder extends DataEntity<SOrder> {
	
	private static final long serialVersionUID = 1L;
	private String orderNo;			// 订单编号
	private SReceipt receipt;		// 收款信息
	private SAddress address;			// 收货地址
	private SGoods goods;			// 商品
	private String specIds;			// 规格ids
	private String num;				// 数量
	private String price;			// 价格
	private String costPrice;		// 商品成本价
	private String sort;			// 排序
	private String ddbs;        	//订单标识    6取消订单7已评价8申请退货9申请成功0退货成功
	private String kddh;			//快递单号
	private String thkddh;			//退货快递单号

	public SOrder() {
		super();
	}

	public SOrder(String id){
		super(id);
	}

	@Length(min=1, max=100, message="订单编号长度必须介于 1 和 100 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public SReceipt getReceipt() {
		return receipt;
	}

	public void setReceipt(SReceipt receipt) {
		this.receipt = receipt;
	}

	public SAddress getAddress() {
		return address;
	}

	public void setAddress(SAddress address) {
		this.address = address;
	}

	@NotNull
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
	
	public String getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}
	
	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDdbs() {
		return ddbs;
	}

	public void setDdbs(String ddbs) {
		this.ddbs = ddbs;
	}

	public String getKddh() {
		return kddh;
	}

	public void setKddh(String kddh) {
		this.kddh = kddh;
	}

	public String getThkddh() {
		return thkddh;
	}

	public void setThkddh(String thkddh) {
		this.thkddh = thkddh;
	}
}