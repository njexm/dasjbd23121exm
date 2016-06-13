package com.proem.exm.entity.branch;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;

/**
 * 支付信息表
 * 
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_PAYINFO")
public class PayInfo extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 会员
	private String memberId;

	// 金额
	private String money;

	// 分店
	private String branchId;

	// 销售员
	private String user;

	@Column(name = "member_id")
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Column(name = "money")
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	@Column(name = "branch_id")
	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	@Column(name = "saleman_id")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
