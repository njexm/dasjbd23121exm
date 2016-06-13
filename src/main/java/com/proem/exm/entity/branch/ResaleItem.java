package com.proem.exm.entity.branch;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;
/**
 * 流水明细
 * @author xuehr
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_RESALE_ITEM")
public class ResaleItem  extends Root{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//流水id
	private String resaleId;
	
	//商品id
	private String goodsFileId;
	
	//数量
	private String nums;
	
	//重量
	private String weight;
	
	//金额
	private String money;
	
	//优惠金额
	private String discountAmount;
	
	//实付金额
	private String actualMoney;
	
	//条形码
	private String barCode;
	
	//单价
	private String price;
	
	@Column(name = "resale_id")
	public String getResaleId() {
		return resaleId;
	}

	public void setResaleId(String resaleId) {
		this.resaleId = resaleId;
	}

	@Column(name = "goodsFile_id")
	public String getGoodsFileId() {
		return goodsFileId;
	}

	public void setGoodsFileId(String goodsFileId) {
		this.goodsFileId = goodsFileId;
	}

	@Column(name = "nums")
	public String getNums() {
		return nums;
	}

	public void setNums(String nums) {
		this.nums = nums;
	}

	@Column(name = "weight")
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	@Column(name = "money")
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	@Column(name = "discount_amount")
	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	@Column(name = "actual_money")
	public String getActualMoney() {
		return actualMoney;
	}

	public void setActualMoney(String actualMoney) {
		this.actualMoney = actualMoney;
	}

	@Column(name = "bar_code")
	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	@Column(name = "price")
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	
	
}
