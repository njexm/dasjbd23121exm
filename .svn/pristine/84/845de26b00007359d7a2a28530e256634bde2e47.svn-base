package com.proem.exm.entity.branch.require;

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
import com.proem.exm.entity.basic.branch.BranchTotal;
import com.proem.exm.entity.system.ZcUserInfo;

/**
 * 要货单
 * @author xuehr
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "zc_require")
public class BranchRequire extends Root {

	private static final long serialVersionUID = -7593818398089799059L;
	
	/**
	 * 要货单号
	 */
	private String YHDNumber;
	
	/**
	 * 要货单状态
	 */
	private int status; 
	
	/**
	 * 制单人
	 */
	private ZcUserInfo zcUserInfo;
	
	/**
	 * 数量
	 */
	private String nums;
	
	/**
	 * 金额
	 */
	private String money;
	
	/**
	 * 审核人
	 */
	private ZcUserInfo checkMan;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 审核日期
	 */
	private Date checkDate;
	
	/**
	 * 要货分店
	 */
	private BranchTotal branchTotal;
	
	/**
	 * 发货分店
	 */
	private BranchTotal deliverBranchTotal;
	
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

	@Column(name = "YHD_NUMBER")
	public String getYHDNumber() {
		return YHDNumber;
	}

	public void setYHDNumber(String yHDNumber) {
		YHDNumber = yHDNumber;
	}
	
	@Column(name = "status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	public ZcUserInfo getZcUserInfo() {
		return zcUserInfo;
	}

	public void setZcUserInfo(ZcUserInfo zcUserInfo) {
		this.zcUserInfo = zcUserInfo;
	}
	
	@Column(name = "nums")
	public String getNums() {
		return nums;
	}

	public void setNums(String nums) {
		this.nums = nums;
	}
	
	@Column(name = "money")
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name="check_man")
	public ZcUserInfo getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(ZcUserInfo checkMan) {
		this.checkMan = checkMan;
	}
	
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "check_date")
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name="branch_id")
	public BranchTotal getBranchTotal() {
		return branchTotal;
	}

	public void setBranchTotal(BranchTotal branchTotal) {
		this.branchTotal = branchTotal;
	}
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name="callout_branch_id")
	public BranchTotal getDeliverBranchTotal() {
		return deliverBranchTotal;
	}

	public void setDeliverBranchTotal(BranchTotal deliverBranchTotal) {
		this.deliverBranchTotal = deliverBranchTotal;
	}
	
	
	
}
