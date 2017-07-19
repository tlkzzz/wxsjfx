/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 商品评论Entity
 * @author xrc
 * @version 2017-07-18
 */
public class SGoodsComment extends DataEntity<SGoodsComment> {
	
	private static final long serialVersionUID = 1L;
	private String goodsId;		// 商品ID
	private String orderId;		// 订单ID
	private String comment;		// 评论
	private String reply;		// 回复
	
	public SGoodsComment() {
		super();
	}

	public SGoodsComment(String id){
		super(id);
	}

	@Length(min=1, max=64, message="商品ID长度必须介于 1 和 64 之间")
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	@Length(min=0, max=64, message="订单ID长度必须介于 0 和 64 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}
	
}