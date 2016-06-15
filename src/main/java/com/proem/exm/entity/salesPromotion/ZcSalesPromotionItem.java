package com.proem.exm.entity.salesPromotion;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;

/**
 * 促销从表
 * @author myseft
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Zc_SalesPromotionItem")
public class ZcSalesPromotionItem extends Root{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*主表id*/
	private String salesPromotionId ;
	/*全场折扣*/
	private String allDiscount ;
	/*折扣*/
	private String discount ;
	/*买满金额*/
	private double fullBuyMoney ;
	/*买满数量*/
	private double fullBuyCount ;
	/*减少金额*/
	private double reduceMoney ;
	/*增加金额*/
	private double addMoney ;
	/*赠送商品ID*/
	private GoodsFile freeGoods ;
	/*特价*/
	private double bargainPrice ;
	/*每单限量*/
	private double limitNumber ;
	/*开始的时间段*/
	private Date beginTimeFrame ;
	/*结束的时间段*/
	private Date endTimeFrame ;
	/*商品信息*/
	private GoodsFile goodsFile ;
	
	@Column(name = "salesPromotion_Id")
	public String getSalesPromotionId() {
		return salesPromotionId;
	}
	public void setSalesPromotionId(String salesPromotionId) {
		this.salesPromotionId = salesPromotionId;
	}
	@Column(name = "all_Discount")
	public String getAllDiscount() {
		return allDiscount;
	}
	public void setAllDiscount(String allDiscount) {
		this.allDiscount = allDiscount;
	}
	@Column(name = "discount")
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	@Column(name = "full_Buy_Money")
	public double getFullBuyMoney() {
		return fullBuyMoney;
	}
	public void setFullBuyMoney(double fullBuyMoney) {
		this.fullBuyMoney = fullBuyMoney;
	}
	@Column(name = "full_Buy_Count")
	public double getFullBuyCount() {
		return fullBuyCount;
	}
	public void setFullBuyCount(double fullBuyCount) {
		this.fullBuyCount = fullBuyCount;
	}
	@Column(name = "reduce_Money")
	public double getReduceMoney() {
		return reduceMoney;
	}
	public void setReduceMoney(double reduceMoney) {
		this.reduceMoney = reduceMoney;
	}
	@Column(name = "add_Money")
	public double getAddMoney() {
		return addMoney;
	}
	public void setAddMoney(double addMoney) {
		this.addMoney = addMoney;
	}
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "free_goodsId")
	public GoodsFile getFreeGoods() {
		return freeGoods;
	}
	public void setFreeGoods(GoodsFile freeGoods) {
		this.freeGoods = freeGoods;
	}
	@Column(name = "bargain_Price")
	public double getBargainPrice() {
		return bargainPrice;
	}
	public void setBargainPrice(double bargainPrice) {
		this.bargainPrice = bargainPrice;
	}
	@Column(name = "limit_Number")
	public double getLimitNumber() {
		return limitNumber;
	}
	public void setLimitNumber(double limitNumber) {
		this.limitNumber = limitNumber;
	}
	@Column(name = "begin_Time_Frame")
	public Date getBeginTimeFrame() {
		return beginTimeFrame;
	}
	public void setBeginTimeFrame(Date beginTimeFrame) {
		this.beginTimeFrame = beginTimeFrame;
	}
	@Column(name = "end_Time_Frame")
	public Date getEndTimeFrame() {
		return endTimeFrame;
	}
	public void setEndTimeFrame(Date endTimeFrame) {
		this.endTimeFrame = endTimeFrame;
	}
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "goodsFile_id")
	public GoodsFile getGoodsFile() {
		return goodsFile;
	}
	public void setGoodsFile(GoodsFile goodsFile) {
		this.goodsFile = goodsFile;
	}
	
	
}
