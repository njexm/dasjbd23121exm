package com.proem.exm.entity.wholesaleGroupPurchase.wholesell;

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
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.wholesaleGroupPurchase.WholesaleGroupPurchaseOrder;
import com.proem.exm.entity.wholesaleGroupPurchase.customer.CustomerInfo;

/**
 * 团购销售单主表
 * 
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_WHOLE_SELL")
public class WholeSell extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 销售单单号
	 */
	private String wholeSellOdd;
	/**
	 * 团购订单对象
	 */
	private WholesaleGroupPurchaseOrder wholesaleGroupPurchaseOrder;
	/**
	 * 审核标致
	 */
	private int statue;
	/**
	 * 单据金额
	 */
	private String totalMoney;
	/**
	 * 销售仓库
	 */
	private Branch branch;
	/**
	 * 客户信息
	 */
	private CustomerInfo customerInfo;
	/**
	 * 制单人
	 */
	private CtpUser createMan;
	/**
	 * 审核人
	 */
	private CtpUser checkMan;
	/**
	 * 审核日期
	 */
	private Date checkDate;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 审核不通过原因
	 */
	private String reason;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "wholesaleGroupPurchaseOrder_id")
	public WholesaleGroupPurchaseOrder getWholesaleGroupPurchaseOrder() {
		return wholesaleGroupPurchaseOrder;
	}

	public void setWholesaleGroupPurchaseOrder(
			WholesaleGroupPurchaseOrder wholesaleGroupPurchaseOrder) {
		this.wholesaleGroupPurchaseOrder = wholesaleGroupPurchaseOrder;
	}

	@Column(name = "wholeSellOdd")
	public String getWholeSellOdd() {
		return wholeSellOdd;
	}

	public void setWholeSellOdd(String wholeSellOdd) {
		this.wholeSellOdd = wholeSellOdd;
	}

	@Column(name = "statue")
	public int getStatue() {
		return statue;
	}

	public void setStatue(int statue) {
		this.statue = statue;
	}

	@Column(name = "totalMoney")
	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_id")
	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "customerInfo_id")
	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "createMan_id")
	public CtpUser getCreateMan() {
		return createMan;
	}

	public void setCreateMan(CtpUser createMan) {
		this.createMan = createMan;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "checkMan_id")
	public CtpUser getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(CtpUser checkMan) {
		this.checkMan = checkMan;
	}

	@Column(name = "checkDate")
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "reason")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
