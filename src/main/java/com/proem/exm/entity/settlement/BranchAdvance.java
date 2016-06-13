package com.proem.exm.entity.settlement;

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

/**
 * 分店预收款实体类
 * 
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_BRANCH_ADVANCE")
public class BranchAdvance extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 预收款
	 */
	private double totalAdvance;
	/**
	 * 审核人
	 */
	private CtpUser checkMan;
	/**
	 * 审核日期
	 */
	private Date checkDate;
	/**
	 * 审核状态
	 */
	private String statue;
	/**
	 * 分店对象
	 */
	private Branch branch;
	/**
	 * 制单人
	 */
	private CtpUser advanceMan;
	/**
	 * 收支方式
	 */
	private String gatheringWay;
	/**
	 * 收款单号
	 */
	private String odd;
	/**
	 * 收款日期
	 */
	private Date gatheringDate;
	/**
	 * 备注信息
	 */
	private String remark;

	@Column(name = "TOTALADVANCE")
	public double getTotalAdvance() {
		return totalAdvance;
	}

	public void setTotalAdvance(double totalAdvance) {
		this.totalAdvance = totalAdvance;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CHECKMAN_ID")
	public CtpUser getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(CtpUser checkMan) {
		this.checkMan = checkMan;
	}

	@Column(name = "CHECKDATE")
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name = "STATUE")
	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "BRANCH_ID")
	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "ADVANCEMAN_ID")
	public CtpUser getAdvanceMan() {
		return advanceMan;
	}

	public void setAdvanceMan(CtpUser advanceMan) {
		this.advanceMan = advanceMan;
	}

	@Column(name = "GATHERINGWAY")
	public String getGatheringWay() {
		return gatheringWay;
	}

	public void setGatheringWay(String gatheringWay) {
		this.gatheringWay = gatheringWay;
	}

	@Column(name = "ODD")
	public String getOdd() {
		return odd;
	}

	public void setOdd(String odd) {
		this.odd = odd;
	}

	@Column(name = "GATHERINGDATE")
	public Date getGatheringDate() {
		return gatheringDate;
	}

	public void setGatheringDate(Date gatheringDate) {
		this.gatheringDate = gatheringDate;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
