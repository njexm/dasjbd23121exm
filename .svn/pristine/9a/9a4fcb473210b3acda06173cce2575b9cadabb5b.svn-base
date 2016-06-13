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
import com.proem.exm.entity.basic.provider.ProviderInfo;
import com.proem.exm.entity.basic.branch.Branch;

/**
 * @author songcj
 * 
 *         2015年11月2日下午2:11:42
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "zc_purchase_receive")
public class PurchaseReceive extends Root {

	private static final long serialVersionUID = 1L;
	/**
	 * 审核状态
	 */
	private int auditStatus;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 采购订单
	 */
	private String purchaseOrder;
	/**
	 * 总价
	 */
	private double total;
	/**
	 * 采购收货单号
	 */
	private String purchaseReceiveOdd;
	/**
	 * 制单人
	 */
	private String purchaseMan;
	/**
	 * 审核日期
	 */
	private Date checkDate;
	/**
	 * 审核人
	 */
	private String checkMan;
	/**
	 * 订单金额
	 */
	private double purchaseMoney;
	/**
	 * 供应商
	 */
	private ProviderInfo providerInfo;
	/**
	 * 采购订单单号
	 */
	private String purchaseOrderOdd;
	/**
	 * 仓库
	 */
	private Branch branch;
	/**
	 * 收货单单据类型(1代表自己手动新建)
	 */
	private String resType;
	/**
	 * 审核不通过原因
	 */
	private String reason;

	@Column(name = "REASON")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "PURCHASEORDERODD")
	public String getPurchaseOrderOdd() {
		return purchaseOrderOdd;
	}

	public void setPurchaseOrderOdd(String purchaseOrderOdd) {
		this.purchaseOrderOdd = purchaseOrderOdd;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "provider_id")
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

	@Column(name = "purchase_money")
	public double getPurchaseMoney() {
		return purchaseMoney;
	}

	public void setPurchaseMoney(double purchaseMoney) {
		this.purchaseMoney = purchaseMoney;
	}

	@Column(name = "purchase_man")
	public String getPurchaseMan() {
		return purchaseMan;
	}

	public void setPurchaseMan(String purchaseMan) {
		this.purchaseMan = purchaseMan;
	}

	@Column(name = "check_date")
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name = "check_man")
	public String getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(String checkMan) {
		this.checkMan = checkMan;
	}

	@Column(name = "purchase_receive_odd")
	public String getPurchaseReceiveOdd() {
		return purchaseReceiveOdd;
	}

	public void setPurchaseReceiveOdd(String purchaseReceiveOdd) {
		this.purchaseReceiveOdd = purchaseReceiveOdd;
	}

	@Column(name = "purchase_order_id")
	public String getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(String purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	@Column(name = "audit_status")
	public int getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "total")
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Column(name = "resType")
	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

}
