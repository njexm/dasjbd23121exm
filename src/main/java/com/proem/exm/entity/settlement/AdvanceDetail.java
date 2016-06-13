package com.proem.exm.entity.settlement;

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

/**
 * 预收款明细表
 * 
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_BRANCH_ADVANCE_DETAIL")
public class AdvanceDetail extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主表ID
	 */
	private BranchAdvance branchAdvance;
	/**
	 * 预收款
	 */
	private double advance;
	/**
	 * 预收款代码
	 */
	private String odd;
	/**
	 * 费用类型
	 */
	private String moneyType;
	/**
	 * 备注信息
	 */
	private String remark;

	@Column(name = "ADVANCE")
	public double getAdvance() {
		return advance;
	}

	public void setAdvance(double advance) {
		this.advance = advance;
	}

	@Column(name = "ODD")
	public String getOdd() {
		return odd;
	}

	public void setOdd(String odd) {
		this.odd = odd;
	}

	@Column(name = "MONEYTYPE")
	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "BRANCHADVANCE_ID")
	public BranchAdvance getBranchAdvance() {
		return branchAdvance;
	}

	public void setBranchAdvance(BranchAdvance branchAdvance) {
		this.branchAdvance = branchAdvance;
	}

}
