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
import com.proem.exm.entity.system.CtpUser;

/**
 * 供应商费用单主表实体类
 * @author songcj
 * 2015年11月30日 上午10:36:50 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Zc_supplier_cost")
public class SupplierCost extends Root{

	private static final long serialVersionUID = 1L;

	/**
	 * 单据号
	 */
	private String receiptNumber;
	/**
	 * 审核标志
	 */
	private int auditStatus;
	/**
	 * 供应商
	 */
	private ProviderInfo provider; 
	/**
	 * 金额
	 */
	private Double totalMoney; 
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 制单人
	 */
	private CtpUser makeMen;
	/**
	 * 制单时间
	 */
	private Date  makeTime;
	/**
	 * 审核人
	 */
	private CtpUser auditMen;
	/**
	 * 审核时间
	 */
	private Date auditTime;
	/**
	 * 收支方式
	 */
	private String paymentMode;
	/**
	 * 付款日期
	 */
	private Date  paymentTime;
	/**
	 * 原因
	 */
	private String reason;
	
	@Column(name = "reason")
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Column(name = "receipt_number")
	public String getReceiptNumber() {
		return receiptNumber;
	}
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	@Column(name = "audit_status")
	public int getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name = "provider_id")
	public ProviderInfo getProvider() {
		return provider;
	}
	public void setProvider(ProviderInfo provider) {
		this.provider = provider;
	}
	@Column(name = "total_money")
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name = "make_time")
	public Date getMakeTime() {
		return makeTime;
	}
	public void setMakeTime(Date makeTime) {
		this.makeTime = makeTime;
	}
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name = "make_men")
	public CtpUser getMakeMen() {
		return makeMen;
	}
	public void setMakeMen(CtpUser makeMen) {
		this.makeMen = makeMen;
	}
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name = "audit_men")
	public CtpUser getAuditMen() {
		return auditMen;
	}
	public void setAuditMen(CtpUser auditMen) {
		this.auditMen = auditMen;
	}
	@Column(name = "audit_time")
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	@Column(name = "payment_mode")
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	@Column(name = "payment_time")
	public Date getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}
	
	
}
