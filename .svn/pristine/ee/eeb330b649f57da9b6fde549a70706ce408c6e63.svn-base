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
 * @author DeFei 分店
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "zc_branch_total")
public class BranchTotal extends Root {

	private static final long serialVersionUID = 7115174968885815406L;

	private String branch_code;// 分店编号
	private String branch_name;// 分店名称
	private String delFlag;// 删除表示
	private ZcUserInfo customer;// 负责人
	private ZcZoning zcZoning;// 分店地址
	/**
	 * 折让金额
	 */
	private String money;
	
	@Column(name = "money")
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
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

}
