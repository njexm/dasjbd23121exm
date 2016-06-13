package com.proem.exm.entity.branch.require;

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
import com.proem.exm.entity.basic.goodsFile.GoodsFile;

/**
 * 要货单明细
 * @author xuehr
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "zc_require_item")
public class BranchRequireItem extends Root {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 要货单id
	 */
	private BranchRequire branchRequire;

	/**
	 * 商品信息
	 */
	private GoodsFile goodsFile;
	
	/**
	 * 数量
	 */
	private String nums;
	
	/**
	 * 金额
	 */
	private String money;
	
	/**
	 * 备注
	 */
	private String remark;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name="goods_file_id")
	public GoodsFile getGoodsFile() {
		return goodsFile;
	}
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name="require_id")
	public BranchRequire getBranchRequire() {
		return branchRequire;
	}

	public void setBranchRequire(BranchRequire branchRequire) {
		this.branchRequire = branchRequire;
	}

	public void setGoodsFile(GoodsFile goodsFile) {
		this.goodsFile = goodsFile;
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
	
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
