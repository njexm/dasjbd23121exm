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
 * 供应商结算主表实体类
 * @author songcj
 * 2015年11月25日 上午10:51:03 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Zc_supplier_settlement")
public class SupplierSettlement extends Root{

	private static final long serialVersionUID = 1L;
	/**
	 * 单据号
	 */
	private String documentCode;
	/**
	 * 账户编码
	 */
	private String accountCode;
	/**
	 * 供应商Id
	 */
	private ProviderInfo provider;
	/**
	 * 单据金额
	 */
	private Double documentsMoney;
	/**
	 * 审核状态
	 */
	private int auditStatus;
	/**
	 * 制单日期
	 */
	private Date makeTime;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 制单人
	 */
	private CtpUser makeMen;
	/**
	 * 审核人
	 */
	private CtpUser auditMen;
	/**
	 * 审核时间
	 */
	private Date auditTime;
	/**
	 * 付款方式
	 */
	private String paymentMode;
	
	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	@Column(name = "document_code")
	public String getDocumentCode() {
		return documentCode;
	}
	public void setDocumentCode(String documentCode) {
		this.documentCode = documentCode;
	}
	@Column(name = "account_code")
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.EAGER)
	@JoinColumn(name = "provider_id")
	public ProviderInfo getProvider() {
		return provider;
	}
	public void setProvider(ProviderInfo provider) {
		this.provider = provider;
	}
	@Column(name = "documents_money")
	public Double getDocumentsMoney() {
		return documentsMoney;
	}
	public void setDocumentsMoney(Double documentsMoney) {
		this.documentsMoney = documentsMoney;
	}
	@Column(name = "audit_status")
	public int getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
	@Column(name = "make_time")
	public Date getMakeTime() {
		return makeTime;
	}
	public void setMakeTime(Date makeTime) {
		this.makeTime = makeTime;
	}

}
