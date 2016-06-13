package com.proem.exm.views.branch;

import com.proem.exm.views.BaseView;

public class BranchsView extends BaseView {

	private String branch_code;// 分店编号
	private String branch_name;// 分店名称
	private String branch_address;// 分店地址
	private String branch_person_name;// 分店负责人
	private String branch_person_tel;// 分店负责人电话
	private String statue;// 审核状态
	private String advance;// 分店预收款
	private String branchTotal;//所属分店
	private String money;//折让金额

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getStatue() {
		return statue;
	}

	public String getBranchTotal() {
		return branchTotal;
	}

	public void setBranchTotal(String branchTotal) {
		this.branchTotal = branchTotal;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public String getAdvance() {
		return advance;
	}

	public void setAdvance(String advance) {
		this.advance = advance;
	}

	public String getBranch_code() {
		return branch_code;
	}

	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getBranch_address() {
		return branch_address;
	}

	public void setBranch_address(String branch_address) {
		this.branch_address = branch_address;
	}

	public String getBranch_person_name() {
		return branch_person_name;
	}

	public void setBranch_person_name(String branch_person_name) {
		this.branch_person_name = branch_person_name;
	}

	public String getBranch_person_tel() {
		return branch_person_tel;
	}

	public void setBranch_person_tel(String branch_person_tel) {
		this.branch_person_tel = branch_person_tel;
	}
}
