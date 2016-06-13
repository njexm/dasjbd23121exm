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
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.system.CtpUser;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_STORECHANGE")
public class ZcStoreChange extends Root{
	/**
	 * 序列化	
	 */
	private static final long serialVersionUID = -7593818398089799059L;
	/**
	 * 单据号
	 */
	private String  storeChange_Number;
	/**
	 * 类型2出库1入库
	 */
	private String changeType;
	/**
	 * 仓库
	 */
	private Branch branch;
	/**
	 * 原因
	 */
	private String changeReason;
	/**
	 * 经手人
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
	@JoinColumn(name = "branch_id")
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "createUser_id")
	public CtpUser getCreateUser() {
		return createUser;
	}
	public void setCreateUser(CtpUser createUser) {
		this.createUser = createUser;
	}
	
	@Column(name="storeChange_Number")
	public String getStoreChange_Number() {
		return storeChange_Number;
	}

	public void setStoreChange_Number(String storeChange_Number) {
		this.storeChange_Number = storeChange_Number;
	}

	@Column(name="changeType")
	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	@Column(name="changeReason")
	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
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
