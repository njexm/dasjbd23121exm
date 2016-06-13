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
 * 预付款主表实体类
 * 
 * @author songcj 2015年11月25日 上午11:32:20
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Zc_advance_payment")
public class AdvancePayment extends Root {

	private static final long serialVersionUID = 1L;

	/**
	 * 供应商
	 */
	private ProviderInfo provider;
	/**
	 * 预付款金额
	 */
	private Double prepayment;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 审核状态
	 */
	private int auditStatus;
	/**
	 * 付款时间
	 */
	private Date paymentTime;
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
	 * 付款单号
	 */
	private String paymentOrder;

	/**
	 * 制单日期
	 */
	private Date makeDate;

	private String reason;

	@Column(name = "reason")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "make_date")
	public Date getMakeDate() {
		return makeDate;
	}

	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}

	@Column(name = "payment_order")
	public String getPaymentOrder() {
		return paymentOrder;
	}

	public void setPaymentOrder(String paymentOrder) {
		this.paymentOrder = paymentOrder;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "make_men_id")
	public CtpUser getMakeMen() {
		return makeMen;
	}

	public void setMakeMen(CtpUser makeMen) {
		this.makeMen = makeMen;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "audit_men_id")
	public CtpUser getAuditMen() {
		return auditMen;
	}

	public void setAuditMen(CtpUser auditMen) {
		this.auditMen = auditMen;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "provider_id")
	public ProviderInfo getProvider() {
		return provider;
	}

	public void setProvider(ProviderInfo provider) {
		this.provider = provider;
	}

	@Column(name = "prepayment")
	public Double getPrepayment() {
		return prepayment;
	}

	public void setPrepayment(Double prepayment) {
		this.prepayment = prepayment;
	}

	@Column(name = "remark")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "audit_status")
	public int getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	@Column(name = "payment_time")
	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	@Column(name = "audit_time")
	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

}
