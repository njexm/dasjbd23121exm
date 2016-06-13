package com.proem.exm.entity.wholesaleGroupPurchase.customer;

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
import com.proem.exm.entity.system.ZcZoning;

/**
 * 客户档案
 * @author ZuoYM 
 * @com proem
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_CUSTOMER_INFO")
public class CustomerInfo extends Root {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7792483914609175017L;
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 公司名称
	 */
	private String name;
	/**
	 * 助记码
	 */
	private String  mnemonicCode ;
	/**
	 * 默认价格
	 */
	private String defaultPrice;
	/**
	 * 折扣
	 */
	private String discount;
	/**
	 * 所属门店
	 */
	private String ownedStores;
	/**
	 * 优惠方式
	 */
	private String preferentialWay;
	/**
	 * 结账周期
	 */
	private String settlementcycle;
	/**
	 * 月结账日期
	 */
	private String settlementdate;
	/**
	 * 结算方式
	 */
	private String settlementway;
	/**
	 * 信誉额度
	 */
	private String creditLimit;
	/**
	 * 业务员
	 */
	private String saleman;
	/**
	 * 区域
	 */
	private String area;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 联系人
	 */
	private String linkman;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 邮件
	 */
	private String mail;//邮箱
	/**
	 * 电话
	 */
	private String telephone;
	/**
	 * 开户行
	 */
	private String bank;
	/**
	 * 手机号码
	 */
	private String mobilephone;
	/**
	 * 税务登记号
	 */
	private String taxregistration;
	/**
	 * 传真
	 */
	private String fax;
	/**
	 * 营业执照号
	 */
	private String license;
	/**
	 * 邮编
	 */
	private String postcode;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 冻结业务（黑名单）
	 */
	private String frozen;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 *  删除标志
	 */
	private String delFlag;
	/**
	 * 地址信息
	 */
	private ZcZoning zcZoning;
	
	@Column(name = "code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "mnemonicCode")
	public String getMnemonicCode() {
		return mnemonicCode;
	}
	public void setMnemonicCode(String mnemonicCode) {
		this.mnemonicCode = mnemonicCode;
	}
	
	@Column(name = "defaultPrice")
	public String getDefaultPrice() {
		return defaultPrice;
	}
	public void setDefaultPrice(String defaultPrice) {
		this.defaultPrice = defaultPrice;
	}
	
	@Column(name = "discount")
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	@Column(name = "ownedStores")
	public String getOwnedStores() {
		return ownedStores;
	}
	public void setOwnedStores(String ownedStores) {
		this.ownedStores = ownedStores;
	}
	
	@Column(name = "preferentialWay")
	public String getPreferentialWay() {
		return preferentialWay;
	}
	public void setPreferentialWay(String preferentialWay) {
		this.preferentialWay = preferentialWay;
	}
	
	@Column(name = "settlementcycle")
	public String getSettlementcycle() {
		return settlementcycle;
	}
	public void setSettlementcycle(String settlementcycle) {
		this.settlementcycle = settlementcycle;
	}
	
	@Column(name = "settlementdate")
	public String getSettlementdate() {
		return settlementdate;
	}
	public void setSettlementdate(String settlementdate) {
		this.settlementdate = settlementdate;
	}
	
	@Column(name = "settlementway")
	public String getSettlementway() {
		return settlementway;
	}
	public void setSettlementway(String settlementway) {
		this.settlementway = settlementway;
	}
	
	@Column(name = "creditLimit")
	public String getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}
	
	@Column(name = "saleman")
	public String getSaleman() {
		return saleman;
	}
	public void setSaleman(String saleman) {
		this.saleman = saleman;
	}
	
	@Column(name = "area")
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "linkman")
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "mail")
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@Column(name = "telephone")
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Column(name = "bank")
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	@Column(name = "mobilephone")
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	
	@Column(name = "taxregistration")
	public String getTaxregistration() {
		return taxregistration;
	}
	public void setTaxregistration(String taxregistration) {
		this.taxregistration = taxregistration;
	}
	
	@Column(name = "fax")
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	@Column(name = "license")
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	
	@Column(name = "postcode")
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	@Column(name = "account")
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	@Column(name = "frozen")
	public String getFrozen() {
		return frozen;
	}
	public void setFrozen(String frozen) {
		this.frozen = frozen;
	}
	
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "delFlag")
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "ZONING_ID")
	public ZcZoning getZcZoning() {
		return zcZoning;
	}
	public void setZcZoning(ZcZoning zcZoning) {
		this.zcZoning = zcZoning;
	}


}
