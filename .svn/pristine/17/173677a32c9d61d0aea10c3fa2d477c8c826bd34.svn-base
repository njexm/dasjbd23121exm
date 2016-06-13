package com.proem.exm.entity.basic.branch;

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
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.system.ZcZoning;

/**
 * @author DeFei 仓库
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "zc_branch_info")
public class Branch extends Root {

	private static final long serialVersionUID = 7115174968885815406L;

	private String branch_code;// 仓库编号
	private String branch_name;// 仓库名称
	private String delFlag;// 删除表示
	private ZcUserInfo customer;// 负责人
	private ZcZoning zcZoning;// 仓库地址
	/**
	 * 所属分店
	 */
	private BranchTotal branchTotal;
	/**
	 * 默认配货仓库(标记仓库)
	 */
	private String approveHouse;

	@Column(name = "APPROVEHOUSE")
	public String getApproveHouse() {
		return approveHouse;
	}

	public void setApproveHouse(String approveHouse) {
		this.approveHouse = approveHouse;
	}

	@Column(name = "BRANCH_CODE")
	public String getBranch_code() {
		return branch_code;
	}

	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}

	@Column(name = "BRANCH_NAME")
	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	@Column(name = "DELFLAG")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "CUSTOMER_ID")
	public ZcUserInfo getCustomer() {
		return customer;
	}

	public void setCustomer(ZcUserInfo customer) {
		this.customer = customer;
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

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "branchTotal_id")
	public BranchTotal getBranchTotal() {
		return branchTotal;
	}

	public void setBranchTotal(BranchTotal branchTotal) {
		this.branchTotal = branchTotal;
	}

}
