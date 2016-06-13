package com.proem.exm.entity.basic.provider;

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
 * 供应商基本信息实体类
 * 
 * @author jingbc
 * 
 * @com proem
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_PROVIDER_INFO")
public class ProviderInfo extends Root {
	private static final long serialVersionUID = -7465167609477547893L;
	/* 联系人 */
	private String linkman;
	/* 联系人电话 */
	private String telephone;
	/* 手机 */
	private String mobilephone;
	/* 传真 */
	private String fax;
	/* 邮箱 */
	private String mail;
	/* 邮编 */
	private String postcode;
	/* 地址 */
	private String address;
	/* 区域 */
	private String area;
	/* 开户行 */
	private String bank;
	/* 企业类型 */
	private String enterprisetype;
	/* 帐号 */
	private String account;
	/* 税务登记号 */
	private String taxregistration;
	/* 营业执照 */
	private String license;
	/* 冻结业务 */
	private String frozen;
	/* 供应商名 */
	private String nickname;
	/* 送货周期 */
	private String deliverycycle;
	/* 经营方式 */
	private String practice;
	/* 结算机构 */
	private String settlement;
	/* 结算方式 */
	private String settlementway;
	/* 业务员 */
	private String saleman;
	/* 结算周期 */
	private String settlementcycle;
	/* 月结日期 */
	private String settlementdate;
	/* 备注信息 */
	private String remark;
	/* 删除标志 */
	private String delFlag;
	/* 地址信息 */
	private ZcZoning zcZoning;

	@Column(name = "PROVIDER_LINKMAN")
	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	@Column(name = "PROVIDER_TELEPHONE")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "PROVIDER_MOBILEPHONE")
	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	@Column(name = "PROVIDER_FAX")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "PROVIDER_MAIL")
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Column(name = "PROVIDER_POSTCODE")
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name = "PROVIDER_ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "PROVIDER_AREA")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "PROVIDER_BANK")
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Column(name = "PROVIDER_ENTERPRISETYPE")
	public String getEnterprisetype() {
		return enterprisetype;
	}

	public void setEnterprisetype(String enterprisetype) {
		this.enterprisetype = enterprisetype;
	}

	@Column(name = "PROVIDER_ACCOUNT")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "PROVIDER_TAXREGISTRATION")
	public String getTaxregistration() {
		return taxregistration;
	}

	public void setTaxregistration(String taxregistration) {
		this.taxregistration = taxregistration;
	}

	@Column(name = "PROVIDER_LICENSE")
	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	@Column(name = "PROVIDER_FROZEN")
	public String getFrozen() {
		return frozen;
	}

	public void setFrozen(String frozen) {
		this.frozen = frozen;
	}

	@Column(name = "PROVIDER_NICKNAME")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "PROVIDER_DELIVERYCYCLE")
	public String getDeliverycycle() {
		return deliverycycle;
	}

	public void setDeliverycycle(String deliverycycle) {
		this.deliverycycle = deliverycycle;
	}

	@Column(name = "PROVIDER_PRACTICE")
	public String getPractice() {
		return practice;
	}

	public void setPractice(String practice) {
		this.practice = practice;
	}

	@Column(name = "PROVIDER_SETTLEMENT")
	public String getSettlement() {
		return settlement;
	}

	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}

	@Column(name = "PROVIDER_SETTLEMENTWAY")
	public String getSettlementway() {
		return settlementway;
	}

	public void setSettlementway(String settlementway) {
		this.settlementway = settlementway;
	}

	@Column(name = "PROVIDER_SALEMAN")
	public String getSaleman() {
		return saleman;
	}

	public void setSaleman(String saleman) {
		this.saleman = saleman;
	}

	@Column(name = "PROVIDER_SETTLEMENTCYCLE")
	public String getSettlementcycle() {
		return settlementcycle;
	}

	public void setSettlementcycle(String settlementcycle) {
		this.settlementcycle = settlementcycle;
	}

	@Column(name = "PROVIDER_SETTLEMENTDATE")
	public String getSettlementdate() {
		return settlementdate;
	}

	public void setSettlementdate(String settlementdate) {
		this.settlementdate = settlementdate;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "DELFLAG")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	// @OneToMany(mappedBy = "provider")
	// public Set<GoodsFile> getGoodsFiles() {
	// return goodsFiles;
	// }
	//
	// public void setGoodsFiles(Set<GoodsFile> goodsFiles) {
	// this.goodsFiles = goodsFiles;
	// }

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
