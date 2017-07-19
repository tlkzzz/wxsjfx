/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.entity;

import com.tlkzzz.jeesite.common.persistence.DataEntity;
import javax.validation.constraints.NotNull;

/**
 * 商品评论Entity
 * @author xrc
 * @version 2017-07-18
 */
public class SGoodsComment extends DataEntity<SGoodsComment> {
	
	private static final long serialVersionUID = 1L;
	private SGoods goods;		// 商品
	private SOrder order;		// 订单
	private String comment;		// 评论
	private String reply;		// 回复
	
	public SGoodsComment() {
		super();
	}

	public SGoodsComment(String id){
		super(id);
	}

	@NotNull
	public SGoods getGoods() {
		return goods;
	}

	public void setGoods(SGoods goods) {
		this.goods = goods;
	}
	
	@NotNull
	public SOrder getOrder() {
		return order;
	}

	public void setOrder(SOrder order) {
		this.order = order;
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