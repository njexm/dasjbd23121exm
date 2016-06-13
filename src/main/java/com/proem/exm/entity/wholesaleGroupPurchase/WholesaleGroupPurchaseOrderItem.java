package com.proem.exm.entity.wholesaleGroupPurchase;

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
 * 批发团购订单明细
 * @author xuehr
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_WHOLE_ORDER_ITEM")
public class WholesaleGroupPurchaseOrderItem extends Root {

	private static final long serialVersionUID = 2711973822283662030L;
	
	//订单Id
	private String orderId;
	
	//商品信息
	private GoodsFile goodsFile;
	
	//销售箱数
	private String saleNums; 
	
	//数量
	private String nums;
	
	//赠送数量
	private String givingNums;
	
	//箱赠量
	private String givingBoxNums;
	
	//数量合计
	private String totalNums;
	
	//单价
	private String orderPrice;
	
	//折扣
	private String discount;
	
	//售价折扣
	private String priceDiscount;
	
	//金额
	private String amount;
	
	//折扣金额
	private String discountAmount;
	
	//赠送金额
	private String givingAmount;
	
	//赠送比率
	private String givingRate;
	
	//原价金额
	private String originalAmount;
	
	//备注
	private String remark;
	
	//税率
	private String rate;
	
	//税额
	private String rateAmount;
	
	//不含税单价
	private String withoutRatePrice;
	
	//成本价
	private String costPrice;
	
	//成本金额
	private String costAmount;
	
	//售价
	private String salePrice;
	
	//售价金额
	private String saleAmount;
	
	//会员价
	private String memberPrice;
	
	//赠品到货数量
	private String giftNums;
	
	//不含税金额
	private String withoutAmount;
	
	//生产日期
	private Date produceDate;
	
	//保质期
	private String shelfLife;
	
	//第三方编码
	private String thirdCode;
	
	@Column(name = "ORDER_ID")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "GOODS_FILE_ID")
	public GoodsFile getGoodsFile() {
		return goodsFile;
	}

	public void setGoodsFile(GoodsFile goodsFile) {
		this.goodsFile = goodsFile;
	}
	
	@Column(name = "SALE_NUMS")
	public String getSaleNums() {
		return saleNums;
	}

	public void setSaleNums(String saleNums) {
		this.saleNums = saleNums;
	}
	
	@Column(name = "NUMS")
	public String getNums() {
		return nums;
	}
	
	public void setNums(String nums) {
		this.nums = nums;
	}
	
	@Column(name = "GIVING_NUMS")
	public String getGivingNums() {
		return givingNums;
	}

	public void setGivingNums(String givingNums) {
		this.givingNums = givingNums;
	}
	
	@Column(name = "GIVING_BOX_NUMS")
	public String getGivingBoxNums() {
		return givingBoxNums;
	}

	public void setGivingBoxNums(String givingBoxNums) {
		this.givingBoxNums = givingBoxNums;
	}
	
	@Column(name = "TOTAL_NUMS")
	public String getTotalNums() {
		return totalNums;
	}

	public void setTotalNums(String totalNums) {
		this.totalNums = totalNums;
	}
	
	@Column(name = "ORDER_PRICE")
	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	@Column(name = "DISCOUNT")
	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	@Column(name = "PRICE_DISCOUNT")
	public String getPriceDiscount() {
		return priceDiscount;
	}

	public void setPriceDiscount(String priceDiscount) {
		this.priceDiscount = priceDiscount;
	}
	
	@Column(name = "AMOUNT")
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@Column(name = "DISCOUNT_AMOUNT")
	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}
	
	@Column(name = "GIVING_AMOUNT")
	public String getGivingAmount() {
		return givingAmount;
	}

	public void setGivingAmount(String givingAmount) {
		this.givingAmount = givingAmount;
	}
	
	@Column(name = "GIVING_RATE")
	public String getGivingRate() {
		return givingRate;
	}

	public void setGivingRate(String givingRate) {
		this.givingRate = givingRate;
	}
	
	@Column(name = "ORIGINAL_AMOUNT")
	public String getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(String originalAmount) {
		this.originalAmount = originalAmount;
	}
	
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "RATE")
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	
	@Column(name = "RATE_AMOUNT")
	public String getRateAmount() {
		return rateAmount;
	}

	public void setRateAmount(String rateAmount) {
		this.rateAmount = rateAmount;
	}
	
	@Column(name = "WITHOUT_RATE_PRICE")
	public String getWithoutRatePrice() {
		return withoutRatePrice;
	}

	public void setWithoutRatePrice(String withoutRatePrice) {
		this.withoutRatePrice = withoutRatePrice;
	}

	@Column(name = "COST_PRICE")
	public String getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}
	
	@Column(name = "COST_AMOUNT")
	public String getCostAmount() {
		return costAmount;
	}

	public void setCostAmount(String costAmount) {
		this.costAmount = costAmount;
	}
	
	@Column(name = "SALE_PRICE")
	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}
	
	@Column(name = "SALE_AMOUNT")
	public String getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(String saleAmount) {
		this.saleAmount = saleAmount;
	}
	
	@Column(name = "MEMBER_PRICE")
	public String getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(String memberPrice) {
		this.memberPrice = memberPrice;
	}
	
	@Column(name = "GIFT_NUMS")
	public String getGiftNums() {
		return giftNums;
	}

	public void setGiftNums(String giftNums) {
		this.giftNums = giftNums;
	}
	
	@Column(name = "WITHOUT_AMOUNT")
	public String getWithoutAmount() {
		return withoutAmount;
	}

	public void setWithoutAmount(String withoutAmount) {
		this.withoutAmount = withoutAmount;
	}
	
	@Column(name = "PRODUCE_DATE")
	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}
	
	@Column(name = "SHELF_LIFE")
	public String getShelfLife() {
		return shelfLife;
	}

	public void setShelfLife(String shelfLife) {
		this.shelfLife = shelfLife;
	}
	
	@Column(name = "THIRD_CODE")
	public String getThirdCode() {
		return thirdCode;
	}

	public void setThirdCode(String thirdCode) {
		this.thirdCode = thirdCode;
	}
	
	
}
