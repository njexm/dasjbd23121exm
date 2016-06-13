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

/**
 * 采购退货单主表
 * 
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_PURCHASE_RETURN")
public class PurchaseReturn extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 采购收货单ID
	 */
	private String purchaseReceive;
	/**
	 * 退货单号
	 */
	private String odd;
	/**
	 * 退货单状态
	 */
	private int statue;
	/**
	 * 退货商品数
	 */
	private String returnnum;
	/**
	 * 制单人
	 */
	private String returnMan;
	/**
	 * 审核人
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
	 * 退货总金额
	 */
	private double totalMoney;
	/**
	 * 仓库
	 */
	private Branch branch;
	/**
	 * 供应商
	 */
	private ProviderInfo providerInfo;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROVIDERINFO_ID")
	public ProviderInfo getProviderInfo() {
		return providerInfo;
	}

	public void setProviderInfo(ProviderInfo providerInfo) {
		this.providerInfo = providerInfo;
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

	@Column(name = "RECEIVE_ID")
	public String getPurchaseReceive() {
		return purchaseReceive;
	}

	public void setPurchaseReceive(String purchaseReceive) {
		this.purchaseReceive = purchaseReceive;
	}

	@Column(name = "RETURNMAN")
	public String getReturnMan() {
		return returnMan;
	}

	public void setReturnMan(String returnMan) {
		this.returnMan = returnMan;
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

	@Column(name = "RETURNNUM")
	public String getReturnnum() {
		return returnnum;
	}

	public void setReturnnum(String returnnum) {
		this.returnnum = returnnum;
	}

	@Column(name = "ODD")
	public String getOdd() {
		return odd;
	}

	public void setOdd(String odd) {
		this.odd = odd;
	}

	@Column(name = "STATUE")
	public int getStatue() {
		return statue;
	}

	public void setStatue(int statue) {
		this.statue = statue;
	}

	@Column(name = "TOTALMONEY")
	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
