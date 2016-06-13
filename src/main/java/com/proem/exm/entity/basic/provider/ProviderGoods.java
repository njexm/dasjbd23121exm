package com.proem.exm.entity.basic.provider;
//package com.proem.exm.entity.basic;
//
//import java.util.Date;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.Inheritance;
//import javax.persistence.InheritanceType;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
///**
// * 供应商商品实体类
// * 
// * @author jingbc
// * 
// * @com proem
// */
//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Table(name = "ZC_PROVIDER_GOODS")
//public class ProviderGoods {
//	/* 供应商ID */
//	private ProviderInfo providerId;
//	/* 商品ID */
//	private int id;
//	/* 商品名 */
//	private String name;
//	/* 商品编号 */
//	private String code;
//	/* 品牌 */
//	private String brand;
//	/* 规格 */
//	private String standard;
//	/* 单位 */
//	private String unit;
//	/* 约定价格 */
//	private double promisedprice;
//	/* 最高价 */
//	private double maxprice;
//	/* 最低价 */
//	private double minprice;
//	/* 售价 */
//	private double saleprice;
//	/* 商品类型 */
//	private String type;
//	/* 是否允许采购 */
//	private String allowpurchase;
//	/* 进项税 */
//	private double inputtax;
//	/* 销项税 */
//	private double outputtax;
//	/* 进货规格 */
//	private String purchasestandard;
//	/* 引进时间 */
//	private Date date;
//
//	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
//			CascadeType.REFRESH }, fetch = FetchType.LAZY)
//	@JoinColumn(name = "PROVIDER_ID")
//	public ProviderInfo getProviderId() {
//		return providerId;
//	}
//
//	public void setProviderId(ProviderInfo providerId) {
//		this.providerId = providerId;
//	}
//
//	@Column(name = "GOODS_ID")
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	@Column(name = "GOODS_NAME")
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	@Column(name = "GOODS_CODE")
//	public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}
//
//	@Column(name = "GOODS_BRAND")
//	public String getBrand() {
//		return brand;
//	}
//
//	public void setBrand(String brand) {
//		this.brand = brand;
//	}
//
//	@Column(name = "GOODS_STANDARD")
//	public String getStandard() {
//		return standard;
//	}
//
//	public void setStandard(String standard) {
//		this.standard = standard;
//	}
//
//	@Column(name = "GOODS_UNIT")
//	public String getUnit() {
//		return unit;
//	}
//
//	public void setUnit(String unit) {
//		this.unit = unit;
//	}
//
//	@Column(name = "GOODS_PROMISEDPRICE")
//	public double getPromisedprice() {
//		return promisedprice;
//	}
//
//	public void setPromisedprice(double promisedprice) {
//		this.promisedprice = promisedprice;
//	}
//
//	@Column(name = "GOODS_MAXPRICE")
//	public double getMaxprice() {
//		return maxprice;
//	}
//
//	public void setMaxprice(double maxprice) {
//		this.maxprice = maxprice;
//	}
//
//	@Column(name = "GOODS_MINPRICE")
//	public double getMinprice() {
//		return minprice;
//	}
//
//	public void setMinprice(double minprice) {
//		this.minprice = minprice;
//	}
//
//	@Column(name = "GOODS_SALEPRICE")
//	public double getSaleprice() {
//		return saleprice;
//	}
//
//	public void setSaleprice(double saleprice) {
//		this.saleprice = saleprice;
//	}
//
//	@Column(name = "GOODS_TYPE")
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	@Column(name = "GOODS_ALLOWPURCHASE")
//	public String getAllowpurchase() {
//		return allowpurchase;
//	}
//
//	public void setAllowpurchase(String allowpurchase) {
//		this.allowpurchase = allowpurchase;
//	}
//
//	@Column(name = "GOODS_INPUTTAX")
//	public double getInputtax() {
//		return inputtax;
//	}
//
//	public void setInputtax(double inputtax) {
//		this.inputtax = inputtax;
//	}
//
//	@Column(name = "GOODS_OUTPUTTAX")
//	public double getOutputtax() {
//		return outputtax;
//	}
//
//	public void setOutputtax(double outputtax) {
//		this.outputtax = outputtax;
//	}
//
//	@Column(name = "GOODS_PURCHASESTANDARD")
//	public String getPurchasestandard() {
//		return purchasestandard;
//	}
//
//	public void setPurchasestandard(String purchasestandard) {
//		this.purchasestandard = purchasestandard;
//	}
//
//	@Column(name = "GOODS_DATE")
//	public Date getDate() {
//		return date;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}
//
// }
