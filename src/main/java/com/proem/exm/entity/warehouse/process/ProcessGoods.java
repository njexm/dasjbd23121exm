package com.proem.exm.entity.warehouse.process;

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
 * 加工单主表
 * 
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_PROCESSGOODS")
public class ProcessGoods extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 加工单号
	 */
	private String odd;
	/**
	 * 制单人
	 */
	private CtpUser createMan;
	/**
	 * 原料使用总数
	 */
	private String useTotalNum;
	/**
	 * 成品总数
	 */
	private String goodsTotalNum;
	/**
	 * 审核状态
	 */
	private int statue;
	/**
	 * 加工仓库
	 */
	private Branch branch;
	/**
	 * 审核人
	 */
	private String checkMan;
	/**
	 * 审核日期
	 */
	private Date checkDate;
	/**
	 * 备注
	 */
	private String remark;
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

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "ODD")
	public String getOdd() {
		return odd;
	}

	public void setOdd(String odd) {
		this.odd = odd;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATEMAN_ID")
	public CtpUser getCreateMan() {
		return createMan;
	}

	public void setCreateMan(CtpUser createMan) {
		this.createMan = createMan;
	}

	@Column(name = "USETOTALNUM")
	public String getUseTotalNum() {
		return useTotalNum;
	}

	public void setUseTotalNum(String useTotalNum) {
		this.useTotalNum = useTotalNum;
	}

	@Column(name = "GOODSTOTALNUM")
	public String getGoodsTotalNum() {
		return goodsTotalNum;
	}

	public void setGoodsTotalNum(String goodsTotalNum) {
		this.goodsTotalNum = goodsTotalNum;
	}

	@Column(name = "STATUE")
	public int getStatue() {
		return statue;
	}

	public void setStatue(int statue) {
		this.statue = statue;
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

}
