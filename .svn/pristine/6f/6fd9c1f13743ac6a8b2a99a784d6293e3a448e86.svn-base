package com.proem.exm.entity.settlement;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;

/**
 * 发票管理实体类
 * 
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_SETTLEMENT_TAX")
public class TaxManager extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 发票代码
	 */
	private String taxCode;
	/**
	 * 发票号码
	 */
	private String taxNumber;
	/**
	 * 开票方税务登记号
	 */
	private String registerNum;
	/**
	 * 发票类型
	 */
	private String taxType;
	/**
	 * 描述发票
	 */
	private String detail;
	/**
	 * 开票日期
	 */
	private Date createDate;
	/**
	 * 购买方名称
	 */
	private String buyerName;
	/**
	 * 购买方纳税人识别号
	 */
	private String buyerCheckNum;
	/**
	 * 购买方地址、电话
	 */
	private String buyerLinkWay;
	/**
	 * 购买方开户行及帐号
	 */
	private String buyerRegisterNum;
	/**
	 * 密码区
	 */
	private String passwordBuyer;
	/**
	 * 货物或应税劳务、服务名称
	 */
	private String goodsName;
	/**
	 * 规格型号
	 */
	private String specification;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 数量
	 */
	private String goodsNumber;
	/**
	 * 单价
	 */
	private String goodsPrice;
	/**
	 * 金额
	 */
	private String goodsMoney;
	/**
	 * 税率
	 */
	private String taxpercent;
	/**
	 * 税额
	 */
	private String taxMoney;
	/**
	 * 价税合计
	 */
	private String totalMoney;
	/**
	 * 销售方名称
	 */
	private String sellerName;
	/**
	 * 销售方纳税人识别号
	 */
	private String sellerCheckNum;
	/**
	 * 销售方地址、电话
	 */
	private String sellerLinkWay;
	/**
	 * 销售方开户行及帐号
	 */
	private String sellerRegisterNum;
	/**
	 * 收款人
	 */
	private String receiveMan;
	/**
	 * 复核
	 */
	private String checkMan;
	/**
	 * 开票人
	 */
	private String createMan;

	@Column(name = "CREATEDATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "BUYERNAME")
	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	@Column(name = "BUYERCHECKNUM")
	public String getBuyerCheckNum() {
		return buyerCheckNum;
	}

	public void setBuyerCheckNum(String buyerCheckNum) {
		this.buyerCheckNum = buyerCheckNum;
	}

	@Column(name = "BUYERLINKWAY")
	public String getBuyerLinkWay() {
		return buyerLinkWay;
	}

	public void setBuyerLinkWay(String buyerLinkWay) {
		this.buyerLinkWay = buyerLinkWay;
	}

	@Column(name = "BUYERREGISTERNUM")
	public String getBuyerRegisterNum() {
		return buyerRegisterNum;
	}

	public void setBuyerRegisterNum(String buyerRegisterNum) {
		this.buyerRegisterNum = buyerRegisterNum;
	}

	@Column(name = "PASSWORDBUYER")
	public String getPasswordBuyer() {
		return passwordBuyer;
	}

	public void setPasswordBuyer(String passwordBuyer) {
		this.passwordBuyer = passwordBuyer;
	}

	@Column(name = "GOODSNAME")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "SPECIFICATION")
	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	@Column(name = "UNIT")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "GOODSNUMBER")
	public String getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(String goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	@Column(name = "GOODSPRICE")
	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	@Column(name = "GOODSMONEY")
	public String getGoodsMoney() {
		return goodsMoney;
	}

	public void setGoodsMoney(String goodsMoney) {
		this.goodsMoney = goodsMoney;
	}

	@Column(name = "TAXPERCENT")
	public String getTaxpercent() {
		return taxpercent;
	}

	public void setTaxpercent(String taxpercent) {
		this.taxpercent = taxpercent;
	}

	@Column(name = "TAXMONEY")
	public String getTaxMoney() {
		return taxMoney;
	}

	public void setTaxMoney(String taxMoney) {
		this.taxMoney = taxMoney;
	}

	@Column(name = "TOTALMONEY")
	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	@Column(name = "SELLERNAME")
	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	@Column(name = "SELLERCHECKNUM")
	public String getSellerCheckNum() {
		return sellerCheckNum;
	}

	public void setSellerCheckNum(String sellerCheckNum) {
		this.sellerCheckNum = sellerCheckNum;
	}

	@Column(name = "SELLERLINKWAY")
	public String getSellerLinkWay() {
		return sellerLinkWay;
	}

	public void setSellerLinkWay(String sellerLinkWay) {
		this.sellerLinkWay = sellerLinkWay;
	}

	@Column(name = "SELLERREGISTERNUM")
	public String getSellerRegisterNum() {
		return sellerRegisterNum;
	}

	public void setSellerRegisterNum(String sellerRegisterNum) {
		this.sellerRegisterNum = sellerRegisterNum;
	}

	@Column(name = "RECEIVEMAN")
	public String getReceiveMan() {
		return receiveMan;
	}

	public void setReceiveMan(String receiveMan) {
		this.receiveMan = receiveMan;
	}

	@Column(name = "CHECKMAN")
	public String getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(String checkMan) {
		this.checkMan = checkMan;
	}

	@Column(name = "CREATEMAN")
	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	@Column(name = "TAXCODE")
	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	@Column(name = "TAXNUMBER")
	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	@Column(name = "REGISTERNUM")
	public String getRegisterNum() {
		return registerNum;
	}

	public void setRegisterNum(String registerNum) {
		this.registerNum = registerNum;
	}

	@Column(name = "TAXTYPE")
	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	@Column(name = "DETAIL")
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
