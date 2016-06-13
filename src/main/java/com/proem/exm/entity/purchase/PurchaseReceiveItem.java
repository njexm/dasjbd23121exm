package com.proem.exm.entity.purchase;

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
import com.proem.exm.entity.system.CtpUser;

/**
 * @author songcj
 * 
 *         2015年11月10日下午6:28:23
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "zc_purchase_receive_item")
public class PurchaseReceiveItem extends Root {

	private static final long serialVersionUID = 1L;
	// 采购订单id
	private String purchaseOrderId;
	// 实际收货数量
	// private int actualNum;
	// 采购收货ID
	private String purchaseReceiveId;
	/**
	 * 单价
	 */
	private String goodsPrice;

	private PurchaseOrderGoodsItems purchaseOrderGoodsItems;
	/**
	 * 商品对象
	 */
	private GoodsFile goodsFile;
	/**
	 * 实际收货数量
	 */
	private double actualNum;
	/**
	 * 退货标记
	 */
	private String createFlag;
	/**
	 * 单品总额
	 */
	private double goodsMoney;
	/**
	 * 用户标记
	 */
	private CtpUser ctpUser;
	/**
	 * 生产日期
	 */
	private Date productDate;

	@Column(name = "GOODSPRICE")
	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	@Column(name = "PRODUCTDATE")
	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CTPUSER_ID")
	public CtpUser getCtpUser() {
		return ctpUser;
	}

	public void setCtpUser(CtpUser ctpUser) {
		this.ctpUser = ctpUser;
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

	@Column(name = "CREATEFLAG")
	public String getCreateFlag() {
		return createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}

	@Column(name = "GOODSMONEY")
	public double getGoodsMoney() {
		return goodsMoney;
	}

	public void setGoodsMoney(double goodsMoney) {
		this.goodsMoney = goodsMoney;
	}

	@Column(name = "ACTUAL_NUM")
	public double getActualNum() {
		return actualNum;
	}

	public void setActualNum(double actualNum) {
		this.actualNum = actualNum;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDERGOODS_ID")
	public PurchaseOrderGoodsItems getPurchaseOrderGoodsItems() {
		return purchaseOrderGoodsItems;
	}

	public void setPurchaseOrderGoodsItems(
			PurchaseOrderGoodsItems purchaseOrderGoodsItems) {
		this.purchaseOrderGoodsItems = purchaseOrderGoodsItems;
	}

	@Column(name = "purchase_order_id")
	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	// @Column(name="actual_num")
	// public int getActualNum() {
	// return actualNum;
	// }
	// public void setActualNum(int actualNum) {
	// this.actualNum = actualNum;
	// }
	@Column(name = "purchase_receive_id")
	public String getPurchaseReceiveId() {
		return purchaseReceiveId;
	}

	public void setPurchaseReceiveId(String purchaseReceiveId) {
		this.purchaseReceiveId = purchaseReceiveId;
	}

}
