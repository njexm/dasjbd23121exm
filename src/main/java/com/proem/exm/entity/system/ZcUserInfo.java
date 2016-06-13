package com.proem.exm.entity.system;

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
import com.proem.exm.entity.basic.branch.BranchTotal;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_USER_INFO")
public class ZcUserInfo extends Root{

	private static final long serialVersionUID = 6211940289349438235L;

	/**
	 * /用户名
	 */
	private String userName;
	/**
	 * 手机号
	 */
	private String mobilePhone;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 邮编
	 */
	private String zipCode;
	/**
	 * 性别
	 */
	private String sexType;
	/**
	 * 详细地址
	 */
	private ZcZoning zcZoning;
	/**
	 * 关联用户登录信息
	 */
	private CtpUser ctpUser;
	/**
	 * 关联角色
	 */
	private CtpRole ctpRole;
	/**
	 * 分店名称
	 */
	private BranchTotal branch_name;
	
	
	@Column(name = "USERNAME")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "MOBILEPHONE")
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Column(name = "ZIPCODE")
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Column(name = "SEXTYPE")
	public String getSexType() {
		return sexType;
	}
	public void setSexType(String sexType) {
		this.sexType = sexType;
	}
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name="ZONING_ID")
	public ZcZoning getZcZoning() {
		return zcZoning;
	}
	public void setZcZoning(ZcZoning zcZoning) {
		this.zcZoning = zcZoning;
	}
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	public CtpUser getCtpUser() {
		return ctpUser;
	}
	public void setCtpUser(CtpUser ctpUser) {
		this.ctpUser = ctpUser;
	}
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name="ROLE_ID")
	public CtpRole getCtpRole() {
			return ctpRole;
	}
	public void setCtpRole(CtpRole ctpRole) {
		this.ctpRole = ctpRole;
	}
		

	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "branch_name_id")
	public BranchTotal getBranch_name() {
		return branch_name;
	}
	
	public void setBranch_name(BranchTotal branch_name) {
		this.branch_name = branch_name;
	}
}
