/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ps.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;

/**
 * 商品Entity
 * @author xrc
 * @version 2017-07-18
 */
public class SGoods extends DataEntity<SGoods> {
	
	private static final long serialVersionUID = 1L;
	private SGoodsClass gClass;		// 商品分类
	private SGenre gener;		// 类型
	private String type;		// 商品类型
	private String name;		// 商品名称
	private String title;		// 商品副标题
	private String price;		// 商品价格
	private String marketPrice;		// 市场价
	private String costPrice;		// 成本价
	private String specIds;		// 规格ids
	private String goodsStock;		// 商品库存
	private String itemNum;		// 商品货号
	private String image;		// 商品图片
	private String images;		// 商品图片多图
	private String bandsId;		// 商品品牌
	private String supplierId;		// 供应商
	private String goodsDesc;		// 商品描述
	private String publish;		// 商品发布(1:发布，0：不发布)
	private String recommend;		// 商品推荐
	private String keywords;		// 关键字
	private String sort;		// 排序
	
	public SGoods() {
		super();
	}

	public SGoods(String id){
		super(id);
	}

	@NotNull(message = "商品分类不能为空")
	public SGoodsClass getGClass() {
		return gClass;
	}

	public void setGClass(SGoodsClass gClass) {
		this.gClass = gClass;
	}

	@NotNull(message = "类型不能为空")
	public SGenre getGener() {
		return gener;
	}

	public void setGener(SGenre gener) {
		this.gener = gener;
	}
	
	@Length(min=0, max=11, message="商品类型长度必须介于 0 和 11 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=1, max=100, message="商品名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="商品副标题长度必须介于 0 和 100 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}
	
	public String getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}
	
	public String getSpecIds() {
		return specIds;
	}

	public void setSpecIds(String specIds) {
		this.specIds = specIds;
	}
	
	public String getGoodsStock() {
		return goodsStock;
	}

	public void setGoodsStock(String goodsStock) {
		this.goodsStock = goodsStock;
	}
	
	@Length(min=0, max=100, message="商品货号长度必须介于 0 和 100 之间")
	public String getItemNum() {
		return itemNum;
	}

	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
	@Length(min=0, max=64, message="商品品牌长度必须介于 0 和 64 之间")
	public String getBandsId() {
		return bandsId;
	}

	public void setBandsId(String bandsId) {
		this.bandsId = bandsId;
	}
	
	@Length(min=0, max=64, message="供应商长度必须介于 0 和 64 之间")
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	
	@Length(min=0, max=11, message="商品发布长度必须介于 0 和 11 之间")
	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}
	
	@Length(min=0, max=11, message="商品推荐长度必须介于 0 和 11 之间")
	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}