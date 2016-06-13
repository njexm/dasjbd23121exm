package com.proem.exm.entity.purchase;

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
import com.proem.exm.entity.basic.branch.Branch;
import com.proem.exm.entity.basic.provider.ProviderInfo;
import com.proem.exm.entity.warehouse.ZcStorehouse;

/**
 * 采购订单实体类
 * 
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_PURCHASE_ORDER")
public class PurchaseOrder extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 采购单号
	 */
	private String odd;
	/**
	 * 采购订单状态
	 */
	private int state;
	/**
	 * 单据金额
	 */
	private double money;
	/**
	 * 采购数量
	 */
	private String totalNum;
	/**
	 * 采购订单生成日期
	 */
	private String date;
	/**
	 * 采购人员
	 */
	private String purchaseMan;
	/**
	 * 审核人员
	 */
	private String checkMan;
	/**
	 * 审核日期
	 */
	private Date checkDate;
	/**
	 * 备注信息
	 */
	private String remark;
	/**
	 * 生成收货单标记
	 */
	private String delFlag;
	/**
	 * 供应商实体类
	 */
	private ProviderInfo providerInfo;
	/**
	 * 实际总额
	 */
	private double actualTotal;
	/**
	 * 仓库
	 */
	private Branch branch;
	/**
	 * 原因
	 */
	private String reason;

	@Column(name = "REASON")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCH_ID")
	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@Column(name = "CHECKMAN")
	public String getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(String checkMan) {
		this.checkMan = checkMan;
	}

	@Column(name = "CHECKDATE")
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name = "actualTotal")
	public double getActualTotal() {
		return actualTotal;
	}

	public void setActualTotal(double actualTotal) {
		this.actualTotal = actualTotal;
	}

	@Column(name = "DELFLAG")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	@Column(name = "TOTALNUM")
	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}

	@Column(name = "PURCHASE_ODD")
	public String getOdd() {
		return odd;
	}

	public void setOdd(String odd) {
		this.odd = odd;
	}

	@Column(name = "PURCHASE_STATE")
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Column(name = "PURCHASE_MONEY")
	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Column(name = "PURCHASE_DATE")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Column(name = "PURCHASE_PURCHASEMAN")
	public String getPurchaseMan() {
		return purchaseMan;
	}

	public void setPurchaseMan(String purchaseMan) {
		this.purchaseMan = purchaseMan;
	}

	@Column(name = "PURCHASE_REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROVIDER_ID")
	public ProviderInfo getProviderInfo() {
		return providerInfo;
	}

	public void setProviderInfo(ProviderInfo providerInfo) {
		this.providerInfo = providerInfo;
	}

}
