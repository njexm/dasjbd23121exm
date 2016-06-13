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
 * 分店费用主表
 * 
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_BRANCH_COST")
public class BranchCost extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 费用单号
	 */
	private String odd;
	/**
	 * 审核状态
	 */
	private String statue;
	/**
	 * 分店
	 */
	private Branch branch;
	/**
	 * 商品总份数
	 */
	private int goodsNum;
	/**
	 * 总金额
	 */
	private double totalMoney;
	/**
	 * 制单人
	 */
	private CtpUser makeMan;
	/**
	 * 审核人
	 */
	private CtpUser checkMan;
	/**
	 * 审核日期
	 */
	private Date checkDate;
	/**
	 * 备注信息
	 */
	private String remark;

	@Column(name = "ODD")
	public String getOdd() {
		return odd;
	}

	public void setOdd(String odd) {
		this.odd = odd;
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

	@Column(name = "GOODSNUM")
	public int getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
	}

	@Column(name = "TOTALMONEY")
	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "MAKEMAN")
	public CtpUser getMakeMan() {
		return makeMan;
	}

	public void setMakeMan(CtpUser makeMan) {
		this.makeMan = makeMan;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "CHECKMAN")
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

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
