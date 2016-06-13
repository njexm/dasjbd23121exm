package com.proem.exm.entity.wholesaleGroupPurchase.wholesaleGroupPurchaseReturn;

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
import com.proem.exm.entity.basic.provider.ProviderInfo;
import com.proem.exm.entity.wholesaleGroupPurchase.wholesell.WholeSellGoodsItems;

/**
 * 团购退货单明细
 * 
 * @author ZuoYM
 * @com proem
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_WGPURCHASE_RETURN_ITEM")
public class WGPurchaseReturnItem extends Root {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3492983833916701060L;
	/**
	 * 团购销售主表ID
	 */
	private WGPurchaseReturn wGPurchaseReturn;
	/**
	 * 商品信息
	 */
	private GoodsFile goodsFile;

	/**
	 * 销售箱数
	 */
	private String saleNums;

	/**
	 * 数量
	 */
	private String nums;

	/**
	 * 赠送数量
	 */
	private String givingNums;

	/**
	 * 箱赠量
	 */
	private String givingBoxNums;

	/**
	 * 数量合计
	 */
	private String totalNums;

	/**
	 * 单价
	 */
	private String orderPrice;

	/**
	 * 折扣
	 */
	private String discount;

	/**
	 * 售价折扣
	 */
	private String priceDiscount;

	/**
	 * 小计金额
	 */
	private String amount;

	/**
	 * 折扣金额
	 */
	private String discountAmount;

	/**
	 * 赠送金额
	 */
	private String givingAmount;

	/**
	 * 赠送比率
	 */
	private String givingRate;

	/**
	 * 原价
	 */
	private String originalMoney;

	/**
	 * 原价金额
	 */
	private String originalAmount;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 供应商
	 */
	private ProviderInfo providerInfo;

	/**
	 * 税率
	 */
	private String rate;

	/**
	 * 税额
	 */
	private String rateAmount;

	/**
	 * 不含税单价
	 */
	private String withoutRatePrice;

	/**
	 * 成本价
	 */
	private String costPrice;

	/**
	 * 成本金额
	 */
	private String costAmount;

	/**
	 * 售价
	 */
	private String salePrice;

	/**
	 * 售价金额
	 */
	private String saleAmount;

	/**
	 * 会员价
	 */
	private String memberPrice;

	/**
	 * 赠品到货数量
	 */
	private String giftNums;

	/**
	 * 不含税金额
	 */
	private String withoutAmount;

	/**
	 * 生产日期
	 */
	private Date produceDate;

	/**
	 * 保质期
	 */
	private String shelfLife;

	/**
	 * 产地
	 */
	private String origin;

	/**
	 * 第三方编码
	 */
	private String thirdCode;

	/**
	 * 退货数量
	 */
	private String returnNums;

	/**
	 * 退货金额
	 */
	private String returnAmount;
	/**
	 * 销售单商品表对象
	 */
	private WholeSellGoodsItems wholeSellGoodsItems;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "wholeSellGoodsItems_id")
	public WholeSellGoodsItems getWholeSellGoodsItems() {
		return wholeSellGoodsItems;
	}

	public void setWholeSellGoodsItems(WholeSellGoodsItems wholeSellGoodsItems) {
		this.wholeSellGoodsItems = wholeSellGoodsItems;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "wGPurchaseReturn_id")
	public WGPurchaseReturn getwGPurchaseReturn() {
		return wGPurchaseReturn;
	}

	public void setwGPurchaseReturn(WGPurchaseReturn wGPurchaseReturn) {
		this.wGPurchaseReturn = wGPurchaseReturn;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "goodsFile_ID")
	public GoodsFile getGoodsFile() {
		return goodsFile;
	}

	public void setGoodsFile(GoodsFile goodsFile) {
		this.goodsFile = goodsFile;
	}

	@Column(name = "saleNums")
	public String getSaleNums() {
		return saleNums;
	}

	public void setSaleNums(String saleNums) {
		this.saleNums = saleNums;
	}

	@Column(name = "nums")
	public String getNums() {
		return nums;
	}

	public void setNums(String nums) {
		this.nums = nums;
	}

	@Column(name = "givingNums")
	public String getGivingNums() {
		return givingNums;
	}

	public void setGivingNums(String givingNums) {
		this.givingNums = givingNums;
	}

	@Column(name = "givingBoxNums")
	public String getGivingBoxNums() {
		return givingBoxNums;
	}

	public void setGivingBoxNums(String givingBoxNums) {
		this.givingBoxNums = givingBoxNums;
	}

	@Column(name = "totalNums")
	public String getTotalNums() {
		return totalNums;
	}

	public void setTotalNums(String totalNums) {
		this.totalNums = totalNums;
	}

	@Column(name = "orderPrice")
	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	@Column(name = "discount")
	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	@Column(name = "priceDiscount")
	public String getPriceDiscount() {
		return priceDiscount;
	}

	public void setPriceDiscount(String priceDiscount) {
		this.priceDiscount = priceDiscount;
	}

	@Column(name = "amount")
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Column(name = "discountAmount")
	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	@Column(name = "givingAmount")
	public String getGivingAmount() {
		return givingAmount;
	}

	public void setGivingAmount(String givingAmount) {
		this.givingAmount = givingAmount;
	}

	@Column(name = "givingRate")
	public String getGivingRate() {
		return givingRate;
	}

	public void setGivingRate(String givingRate) {
		this.givingRate = givingRate;
	}

	@Column(name = "originalMoney")
	public String getOriginalMoney() {
		return originalMoney;
	}

	public void setOriginalMoney(String originalMoney) {
		this.originalMoney = originalMoney;
	}

	@Column(name = "originalAmount")
	public String getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(String originalAmount) {
		this.originalAmount = originalAmount;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "providerInfo_ID")
	public ProviderInfo getProviderInfo() {
		return providerInfo;
	}

	public void setProviderInfo(ProviderInfo providerInfo) {
		this.providerInfo = providerInfo;
	}

	@Column(name = "rate")
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	@Column(name = "rateAmount")
	public String getRateAmount() {
		return rateAmount;
	}

	public void setRateAmount(String rateAmount) {
		this.rateAmount = rateAmount;
	}

	@Column(name = "withoutRatePrice")
	public String getWithoutRatePrice() {
		return withoutRatePrice;
	}

	public void setWithoutRatePrice(String withoutRatePrice) {
		this.withoutRatePrice = withoutRatePrice;
	}

	@Column(name = "costPrice")
	public String getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}

	@Column(name = "costAmount")
	public String getCostAmount() {
		return costAmount;
	}

	public void setCostAmount(String costAmount) {
		this.costAmount = costAmount;
	}

	@Column(name = "salePrice")
	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	@Column(name = "saleAmount")
	public String getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(String saleAmount) {
		this.saleAmount = saleAmount;
	}

	@Column(name = "memberPrice")
	public String getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(String memberPrice) {
		this.memberPrice = memberPrice;
	}

	@Column(name = "giftNums")
	public String getGiftNums() {
		return giftNums;
	}

	public void setGiftNums(String giftNums) {
		this.giftNums = giftNums;
	}

	@Column(name = "withoutAmount")
	public String getWithoutAmount() {
		return withoutAmount;
	}

	public void setWithoutAmount(String withoutAmount) {
		this.withoutAmount = withoutAmount;
	}

	@Column(name = "produceDate")
	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	@Column(name = "shelfLife")
	public String getShelfLife() {
		return shelfLife;
	}

	public void setShelfLife(String shelfLife) {
		this.shelfLife = shelfLife;
	}

	@Column(name = "origin")
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@Column(name = "thirdCode")
	public String getThirdCode() {
		return thirdCode;
	}

	public void setThirdCode(String thirdCode) {
		this.thirdCode = thirdCode;
	}

	@Column(name = "returnNums")
	public String getReturnNums() {
		return returnNums;
	}

	public void setReturnNums(String returnNums) {
		this.returnNums = returnNums;
	}

	@Column(name = "returnAmount")
	public String getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(String returnAmount) {
		this.returnAmount = returnAmount;
	}

}
