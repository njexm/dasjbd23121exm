package com.proem.exm.entity.branch;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;

/**
 * 支付信息表
 * 
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_PAYINFO")
public class PayInfo extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 付款金額
	 */
	private String payAmount;
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 业务员
	 */
	private String salesMan;
	/**
	 * 付款日期
	 */
	private Date payDate;
	/**
	 * 支付方式
	 */
	private String payMode;
	/**
	 * 分店ID
	 */
	private String branch;
	/**
	 * 会员卡号
	 */
	private String memberId;

	@Column(name = "PAYAMOUNT")
	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	@Column(name = "ORDERID")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "SALESMAN")
	public String getSalesMan() {
		return salesMan;
	}

	public void setSalesMan(String salesMan) {
		this.salesMan = salesMan;
	}

	@Column(name = "PAYDATE")
	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	@Column(name = "PAYMODE")
	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	@Column(name = "BRANCH_ID")
	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	@Column(name = "MEMBERID")
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

}
