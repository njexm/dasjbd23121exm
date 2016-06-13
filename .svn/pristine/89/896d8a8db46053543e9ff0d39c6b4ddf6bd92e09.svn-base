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
import com.proem.exm.entity.basic.goodsFile.GoodsFile;

/**
 * 成本记录表
 * 
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_ZCCOSTRECORD_ITEMS")
public class ZcCostRecordItems extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 成品ID
	 */
	private GoodsFile productGoods;
	/**
	 * 成品名称
	 */
	private String goodsName;
	/**
	 * 成品份数
	 */
	private String productCopies;
	/**
	 * 成品单价
	 */
	private String productPrice;
	/**
	 * 成品重量
	 */
	private String productWeight;
	/**
	 * 原材料ID
	 */
	private GoodsFile materialGoods;
	/**
	 * 原材料重量
	 */
	private String materialWeight;
	/**
	 * 成本金额
	 */
	private String costingMoney;
	/**
	 * 损耗率
	 */
	private String wastePercent;
	/**
	 * 加工日期
	 */
	private Date processTime;

	@Column(name = "processTime")
	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "productGoods")
	public GoodsFile getProductGoods() {
		return productGoods;
	}

	public void setProductGoods(GoodsFile productGoods) {
		this.productGoods = productGoods;
	}

	@Column(name = "goodsName")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "productCopies")
	public String getProductCopies() {
		return productCopies;
	}

	public void setProductCopies(String productCopies) {
		this.productCopies = productCopies;
	}

	@Column(name = "productPrice")
	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	@Column(name = "productWeight")
	public String getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(String productWeight) {
		this.productWeight = productWeight;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "materialGoods")
	public GoodsFile getMaterialGoods() {
		return materialGoods;
	}

	public void setMaterialGoods(GoodsFile materialGoods) {
		this.materialGoods = materialGoods;
	}

	@Column(name = "materialWeight")
	public String getMaterialWeight() {
		return materialWeight;
	}

	public void setMaterialWeight(String materialWeight) {
		this.materialWeight = materialWeight;
	}

	@Column(name = "costingMoney")
	public String getCostingMoney() {
		return costingMoney;
	}

	public void setCostingMoney(String costingMoney) {
		this.costingMoney = costingMoney;
	}

	@Column(name = "wastePercent")
	public String getWastePercent() {
		return wastePercent;
	}

	public void setWastePercent(String wastePercent) {
		this.wastePercent = wastePercent;
	}

}
