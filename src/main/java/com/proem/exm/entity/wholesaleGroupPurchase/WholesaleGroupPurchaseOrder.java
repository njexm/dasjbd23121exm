package com.proem.exm.entity.wholesaleGroupPurchase;

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
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.wholesaleGroupPurchase.customer.CustomerInfo;

/**
 * 批发团购主表
 * @author xuehr
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_WHOLE_ORDER")
public class WholesaleGroupPurchaseOrder extends Root{

	private static final long serialVersionUID = 1L;

	//订单号
	private String orderNumber; 
	
	//仓库信息
	private Branch branch;
	
	//客户
	private CustomerInfo customerInfo;
	
	//操作员
	private ZcUserInfo zcUserInfo;
	
	//订单状态
	private int status;
	
	//订单金额
	private String orderAmount;
	
	//审核人
	private String checkMan;
	
	//审核日期
	private Date checkDate;
	
	//付款方式
	private String payType;
	
	//销售方式
	private String saleType;
	
	//有效日期
	private Date effectiveDate;
	
	//备注
	private String remark;
	
	//原因
	private String reason;
	
	@Column(name = "ORDERNUMBER")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name="BRANCH_ID")
	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name="CUSTOMEINFO_ID")
	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name="ZCUSERINFO_ID")
	public ZcUserInfo getZcUserInfo() {
		return zcUserInfo;
	}

	public void setZcUserInfo(ZcUserInfo zcUserInfo) {
		this.zcUserInfo = zcUserInfo;
	}
	
	@Column(name = "STATUS")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@Column(name = "ORDERAMOUNT")
	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	@Column(name = "CHECKMAN")
	public String getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(String checkMan) {
		this.checkMan = checkMan;
	}
	
	@Column(name = "CHECK_DATE")
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
	@Column(name = "PAY_TYPE")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	@Column(name = "SALE_TYPE")
	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	
	@Column(name = "EFFECTIVE_DATE")
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "REASON")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
