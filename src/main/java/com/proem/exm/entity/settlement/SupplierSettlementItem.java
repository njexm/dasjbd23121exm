package com.proem.exm.entity.settlement;

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
import com.proem.exm.entity.basic.provider.ProviderInfo;
/**
 * 供应商结算从表实体类
 * @author songcj
 * 2015年12月1日 下午7:11:45 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Zc_supplier_settlement_item")
public class SupplierSettlementItem extends Root{

	private static final long serialVersionUID = 1L;

	/**
	 * 金额
	 */
	private String money;
	/**
	 * 单号
	 */
	private String code;
	/**
	 * 应付金额
	 */
	private String payableMoney;
	/**
	 * 已付金额
	 */
	private String paidMoney;
	/**
	 * 未付金额
	 */
	private String unpaidMoney;
	/**
	 * 实付金额
	 */
	private String actualMoney;
	/**
	 * 优惠金额
	 */
	private String favorableMoney;
	/**
	 * 已优惠金额
	 */
	private String discountMoney;
	/**
	 * 约定付款时间
	 */
	private Date agreedTime;
	/**
	 * 单据税额
	 */
	private String tax;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 供应商
	 */
	private ProviderInfo provider;
	/**
	 * 供应商结算主表
	 */
	private SupplierSettlement supplierSettlementId;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_settlement_id")
	public SupplierSettlement getSupplierSettlementId() {
		return supplierSettlementId;
	}
	public void setSupplierSettlementId(SupplierSettlement supplierSettlementId) {
		this.supplierSettlementId = supplierSettlementId;
	}
	@Column(name = "money")
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	@Column(name = "code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name = "payable_money")
	public String getPayableMoney() {
		return payableMoney;
	}
	public void setPayableMoney(String payableMoney) {
		this.payableMoney = payableMoney;
	}
	@Column(name = "paid_money")
	public String getPaidMoney() {
		return paidMoney;
	}
	public void setPaidMoney(String paidMoney) {
		this.paidMoney = paidMoney;
	}
	@Column(name = "unpaid_money")
	public String getUnpaidMoney() {
		return unpaidMoney;
	}
	public void setUnpaidMoney(String unpaidMoney) {
		this.unpaidMoney = unpaidMoney;
	}
	@Column(name = "actual_money")
	public String getActualMoney() {
		return actualMoney;
	}
	public void setActualMoney(String actualMoney) {
		this.actualMoney = actualMoney;
	}
	@Column(name = "favorable_money")
	public String getFavorableMoney() {
		return favorableMoney;
	}
	public void setFavorableMoney(String favorableMoney) {
		this.favorableMoney = favorableMoney;
	}
	@Column(name = "discount_money")
	public String getDiscountMoney() {
		return discountMoney;
	}
	public void setDiscountMoney(String discountMoney) {
		this.discountMoney = discountMoney;
	}
	@Column(name = "agreed_time")
	public Date getAgreedTime() {
		return agreedTime;
	}
	public void setAgreedTime(Date agreedTime) {
		this.agreedTime = agreedTime;
	}
	@Column(name = "tax")
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name = "provider_id")
	public ProviderInfo getProvider() {
		return provider;
	}
	public void setProvider(ProviderInfo provider) {
		this.provider = provider;
	}
	
}
