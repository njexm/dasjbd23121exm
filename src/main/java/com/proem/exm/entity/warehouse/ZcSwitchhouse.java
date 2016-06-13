package com.proem.exm.entity.warehouse;

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

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_SWITCHHOUSE")
public class ZcSwitchhouse extends Root{
	/**
	 * 序列化	
	 */
	private static final long serialVersionUID = -7593818398089799059L;
	/**
	 * 单据号
	 */
	private String  switch_Number;
	/**
	 * 调出仓库
	 */
	private Branch fromBranch;
	/**
	 * 调入仓库
	 */
	private Branch toBranch;
	/**
	 * 业务员
	 */
	private CtpUser operatorUser;
	/**
	 * 审核人
	 */
	private CtpUser checkUser;
	/**
	 * 审核日期
	 */
	private Date checkDate;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 单据金额
	 */
	private String changeAmount;
	/**
	 * 状态
	 */
	private int status;
	
	/**
	 * 制单人
	 */
	private CtpUser createUser;
	
	
	@Column(name="status")
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "createUser_id")
	public CtpUser getCreateUser() {
		return createUser;
	}
	
	@Column(name="switch_Number")
	public String getSwitch_Number() {
		return switch_Number;
	}

	public void setSwitch_Number(String switch_Number) {
		this.switch_Number = switch_Number;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "fromBranch_id")
	public Branch getFromBranch() {
		return fromBranch;
	}

	public void setFromBranch(Branch fromBranch) {
		this.fromBranch = fromBranch;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "toBranch_id")
	public Branch getToBranch() {
		return toBranch;
	}

	public void setToBranch(Branch toBranch) {
		this.toBranch = toBranch;
	}

	public void setCreateUser(CtpUser createUser) {
		this.createUser = createUser;
	}
	


	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "operatorUser_id")
	public CtpUser getOperatorUser() {
		return operatorUser;
	}

	public void setOperatorUser(CtpUser operatorUser) {
		this.operatorUser = operatorUser;
	}

	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "checkUser_id")
	public CtpUser getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(CtpUser checkUser) {
		this.checkUser = checkUser;
	}

	@Column(name="checkDate")
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name="changeAmount")
	public String getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(String changeAmount) {
		this.changeAmount = changeAmount;
	}
		
	
	
	
	
	
	

}
