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
import com.proem.exm.entity.basic.provider.ProviderInfo;
import com.proem.exm.entity.system.CtpUser;

/**
 * 采购订单商品明细
 * 
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_PURCHASE_ORDER_ITEMS")
public class PurchaseOrderGoodsItems extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商品ID
	 */
	private GoodsFile goodsFile;
	/**
	 * 采购订单ID
	 */
	private String purchaseId;
	/**
	 * 明细商品名称
	 */
	private String goodsName;
	/**
	 * 商品自编码
	 */
	private String goodsCode;
	/**
	 * 货号
	 */
	private String serialNumber;
	/**
	 * 订单需求数量
	 */
	private double orderNum;
	/**
	 * 商品单价
	 */
	private double goodsPrice;
	/**
	 * 商品总额
	 */
	private double goodsMoney;
	/**
	 * 规格
	 */
	private String specifications;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 库存
	 */
	private String stock;
	/**
	 * 实际需求量
	 */
	private String actualNeed;
	/**
	 * 交货日期
	 */
	private String deliveryDate;
	/**
	 * 采购人员
	 */
	private CtpUser purchaseMan;
	/**
	 * 实际总价
	 */
	private Double actualTotal;
	/**
	 * 实际收货数量
	 */
	private Double actualNum;
	/**
	 * 生产日期
	 */
	private Date produceDate;
	/**
	 * 供应商
	 */
	private ProviderInfo providerInfo;
	/**
	 * 短信发送标记
	 */
	private String sendFlag;

	@Column(name = "sendFlag")
	public String getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(String sendFlag) {
		this.sendFlag = sendFlag;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROVIDERINFO_ID")
	public ProviderInfo getProviderInfo() {
		return providerInfo;
	}

	public void setProviderInfo(ProviderInfo providerInfo) {
		this.providerInfo = providerInfo;
	}

	@Column(name = "actual_Num")
	public Double getActualNum() {
		return actualNum;
	}

	public void setActualNum(Double actualNum) {
		this.actualNum = actualNum;
	}

	@Column(name = "actualTotal")
	public Double getActualTotal() {
		return actualTotal;
	}

	public void setActualTotal(Double actualTotal) {
		this.actualTotal = actualTotal;
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

	@Column(name = "SERIALNUMBER")
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Column(name = "ORDERNUM")
	public double getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(double orderNum) {
		this.orderNum = orderNum;
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

	@Column(name = "GOODS_CODE")
	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	@Column(name = "GOODSPRICE")
	public double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	@Column(name = "GOODSMONEY")
	public double getGoodsMoney() {
		return goodsMoney;
	}

	public void setGoodsMoney(double goodsMoney) {
		this.goodsMoney = goodsMoney;
	}

	@Column(name = "STOCK")
	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	@Column(name = "ACTUALNEED")
	public String getActualNeed() {
		return actualNeed;
	}

	public void setActualNeed(String actualNeed) {
		this.actualNeed = actualNeed;
	}

	@Column(name = "DELIVERYDATE")
	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Column(name = "PURCHASEID")
	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	@Column(name = "GOODSNAME")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CTPUSER_ID")
	public CtpUser getPurchaseMan() {
		return purchaseMan;
	}

	public void setPurchaseMan(CtpUser purchaseMan) {
		this.purchaseMan = purchaseMan;
	}

	@Column(name = "produceDate")
	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

}
