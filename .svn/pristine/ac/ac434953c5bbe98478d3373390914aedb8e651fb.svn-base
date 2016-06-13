package com.proem.exm.entity.purchase;

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
 * 采购退货单商品明细实体类
 * 
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_PURCHASE_RETURN_ITEMS")
public class PurchaseReturnItems extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 采购订单商品对象
	 */
	private PurchaseOrderGoodsItems purchaseOrderGoodsItems;
	/**
	 * 退货单ID
	 */
	private String returnId;
	/**
	 * 商品名
	 */
	private String goodsName;
	/**
	 * 自编码
	 */
	private String goodsCode;
	/**
	 * 货号
	 */
	private String serialnumber;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 规格
	 */
	private String specifications;
	/**
	 * 单价
	 */
	private double goodsPrice;
	/**
	 * 收货数量
	 */
	private String receiveNum;
	/**
	 * 退货数量
	 */
	private String returnNum;
	/**
	 * 商品对象
	 */
	private GoodsFile goodsFile;
	/**
	 * 采购收货商品对象
	 */
	private PurchaseReceiveItem purchaseReceiveItem;
	/**
	 * 单品总价
	 */
	private String totalMoney;

	@Column(name = "TOTALMONEY")
	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PURCHASERECEIVEITEM_ID")
	public PurchaseReceiveItem getPurchaseReceiveItem() {
		return purchaseReceiveItem;
	}

	public void setPurchaseReceiveItem(PurchaseReceiveItem purchaseReceiveItem) {
		this.purchaseReceiveItem = purchaseReceiveItem;
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
	@JoinColumn(name = "PURCHASEITEM_ID")
	public PurchaseOrderGoodsItems getPurchaseOrderGoodsItems() {
		return purchaseOrderGoodsItems;
	}

	public void setPurchaseOrderGoodsItems(
			PurchaseOrderGoodsItems purchaseOrderGoodsItems) {
		this.purchaseOrderGoodsItems = purchaseOrderGoodsItems;
	}

	@Column(name = "RETURNID")
	public String getReturnId() {
		return returnId;
	}

	public void setReturnId(String returnId) {
		this.returnId = returnId;
	}

	@Column(name = "GOODSNAME")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "SERIALNUMBER")
	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	@Column(name = "GOODSCODE")
	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	@Column(name = "UNIT")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "SPECIFICATIONS")
	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	@Column(name = "GOODSPRICE")
	public double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	@Column(name = "RECEIVENUM")
	public String getReceiveNum() {
		return receiveNum;
	}

	public void setReceiveNum(String receiveNum) {
		this.receiveNum = receiveNum;
	}

	@Column(name = "RETURNNUM")
	public String getReturnNum() {
		return returnNum;
	}

	public void setReturnNum(String returnNum) {
		this.returnNum = returnNum;
	}

}
