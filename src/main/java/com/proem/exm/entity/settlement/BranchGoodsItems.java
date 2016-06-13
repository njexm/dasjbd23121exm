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
import com.proem.exm.entity.basic.goodsFile.GoodsFile;

/**
 * 分店商品费用明细表
 * 
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_BRANCH_GOODS_ITEMS")
public class BranchGoodsItems extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商品
	 */
	private GoodsFile goodsFile;
	/**
	 * 主表ID
	 */
	private BranchCost branchCost;
	/**
	 * 发货数量(手动填写)
	 */
	private int shipmentsNum;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "GOODSFILE_ID")
	public GoodsFile getGoodsFile() {
		return goodsFile;
	}

	public void setGoodsFile(GoodsFile goodsFile) {
		this.goodsFile = goodsFile;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "BRANCHCOST_ID")
	public BranchCost getBranchCost() {
		return branchCost;
	}

	public void setBranchCost(BranchCost branchCost) {
		this.branchCost = branchCost;
	}

	@Column(name = "SHIPMENTSNUM")
	public int getShipmentsNum() {
		return shipmentsNum;
	}

	public void setShipmentsNum(int shipmentsNum) {
		this.shipmentsNum = shipmentsNum;
	}

}
