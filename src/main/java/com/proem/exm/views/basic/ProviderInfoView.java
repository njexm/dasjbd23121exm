package com.proem.exm.views.basic;

import com.proem.exm.entity.system.ZcZoning;
import com.proem.exm.views.BaseView;

/**
 * 页面信息展示实体类
 * 
 * @author jingbc
 * 
 * @com proem
 */
public class ProviderInfoView extends BaseView {
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

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getEnterprisetype() {
		return enterprisetype;
	}

	public void setEnterprisetype(String enterprisetype) {
		this.enterprisetype = enterprisetype;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTaxregistration() {
		return taxregistration;
	}

	public void setTaxregistration(String taxregistration) {
		this.taxregistration = taxregistration;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getFrozen() {
		return frozen;
	}

	public void setFrozen(String frozen) {
		this.frozen = frozen;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPractice() {
		return practice;
	}

	public void setPractice(String practice) {
		this.practice = practice;
	}

	public String getSettlement() {
		return settlement;
	}

	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}

	public String getSettlementway() {
		return settlementway;
	}

	public void setSettlementway(String settlementway) {
		this.settlementway = settlementway;
	}

	public String getSaleman() {
		return saleman;
	}

	public void setSaleman(String saleman) {
		this.saleman = saleman;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDeliverycycle() {
		return deliverycycle;
	}

	public void setDeliverycycle(String deliverycycle) {
		this.deliverycycle = deliverycycle;
	}

	public String getSettlementcycle() {
		return settlementcycle;
	}

	public void setSettlementcycle(String settlementcycle) {
		this.settlementcycle = settlementcycle;
	}

	public String getSettlementdate() {
		return settlementdate;
	}

	public void setSettlementdate(String settlementdate) {
		this.settlementdate = settlementdate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public ZcZoning getZcZoning() {
		return zcZoning;
	}

	public void setZcZoning(ZcZoning zcZoning) {
		this.zcZoning = zcZoning;
	}

}
