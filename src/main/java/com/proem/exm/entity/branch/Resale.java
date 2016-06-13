package com.proem.exm.entity.branch;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;

/**
 * 流水信息
 * @author xuehr
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_RESALE")
public class Resale extends Root{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//数量
	private String nums;
	
	//金额
	private String money;
	
	//折扣金额
	private String discountMoney;
	
	//折让金额
	private String preferentialMoney;
	
	//实付金额
	private String actualMoney;
	
	//分店id
	private String branchId;
	
	//销售员
	private String salemanId;
	
	//会员Id
	private String memberId;
	
	//订单id
	private String orderId;
	
	//流水号
	private String waterNumber;
	
	//支付id
	private String payInfoId;

	@Column(name ="nums")
	public String getNums() {
		return nums;
	}

	public void setNums(String nums) {
		this.nums = nums;
	}

	@Column(name = "money")
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	@Column(name = "discount_money")
	public String getDiscountMoney() {
		return discountMoney;
	}

	public void setDiscountMoney(String discountMoney) {
		this.discountMoney = discountMoney;
	}

	@Column(name = "preferential_money")
	public String getPreferentialMoney() {
		return preferentialMoney;
	}

	public void setPreferentialMoney(String preferentialMoney) {
		this.preferentialMoney = preferentialMoney;
	}

	@Column(name = "actual_money")
	public String getActualMoney() {
		return actualMoney;
	}

	public void setActualMoney(String actualMoney) {
		this.actualMoney = actualMoney;
	}

	@Column(name = "branch_id")
	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	
	@Column(name = "saleman_id")
	public String getSalemanId() {
		return salemanId;
	}

	public void setSalemanId(String salemanId) {
		this.salemanId = salemanId;
	}

	@Column(name = "member_id")
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Column(name = "order_id")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "water_number")
	public String getWaterNumber() {
		return waterNumber;
	}
	
	public void setWaterNumber(String waterNumber) {
		this.waterNumber = waterNumber;
	}

	@Column(name = "payInfo_id")
	public String getPayInfoId() {
		return payInfoId;
	}

	public void setPayInfoId(String payInfoId) {
		this.payInfoId = payInfoId;
	}
	
	
}
