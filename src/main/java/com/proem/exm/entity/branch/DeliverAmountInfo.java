package com.proem.exm.entity.branch;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;

/**
 * 交班收营员当天收入信息表
 * 
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_DELIVER_AMOUNTINFO")
public class DeliverAmountInfo extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 收营员ID
	 */
	public String salesman;
	/**
	 * 收银金额
	 */
	public String amountCashier;
	/**
	 * 分店ID
	 */
	private String branch;
	/**
	 * 换班时间
	 */
	private Date exchangeDate;

	@Column(name = "SALESMAN")
	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	@Column(name = "AMOUNTCASHIER")
	public String getAmountCashier() {
		return amountCashier;
	}

	public void setAmountCashier(String amountCashier) {
		this.amountCashier = amountCashier;
	}

	@Column(name = "BRANCH_ID")
	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	@Column(name = "EXCHANGEDATE")
	public Date getExchangeDate() {
		return exchangeDate;
	}

	public void setExchangeDate(Date exchangeDate) {
		this.exchangeDate = exchangeDate;
	}

}
