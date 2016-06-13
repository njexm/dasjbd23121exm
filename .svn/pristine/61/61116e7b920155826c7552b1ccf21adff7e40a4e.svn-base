package com.proem.exm.entity.warehouse;

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
 * 采购订单商品明细
 * 
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_CHECK_ITEMS")
public class CheckGoodsItems extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* 商品ID */
	private GoodsFile goodsFile;
	/* 采购盘点单ID */
	private ZcWarehouse warehouse;
	/* 实际盘点量 */
	private String actualCheckNumber;
	/**
	 * 库存数
	 */
	private String store;
	/**
	 * 差异原因
	 */
	private String differenceReason;
	/**
	 * 删除标记
	 */
	private String delflag;
	
	@Column(name = "delflag")
	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODSFILE_ID")
	public GoodsFile getGoodsFile() {
		return goodsFile;
	}

	public void setGoodsFile(GoodsFile goodsFile) {
		this.goodsFile = goodsFile;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "warehouse_id")
	public ZcWarehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(ZcWarehouse warehouse) {
		this.warehouse = warehouse;
	}

	@Column(name = "actualCheckNumber")
	public String getActualCheckNumber() {
		return actualCheckNumber;
	}

	public void setActualCheckNumber(String actualCheckNumber) {
		this.actualCheckNumber = actualCheckNumber;
	}

	@Column(name = "differenceReason")
	public String getDifferenceReason() {
		return differenceReason;
	}

	public void setDifferenceReason(String differenceReason) {
		this.differenceReason = differenceReason;
	}

	
	@Column(name = "store")
	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	

}
