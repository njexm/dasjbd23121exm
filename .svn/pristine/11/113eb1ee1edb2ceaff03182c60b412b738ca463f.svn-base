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

/**
 * 分店id绑定
 * 
 * @author ZuoYM
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "zc_branchId_info")
public class BranchIdInfo extends Root {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1839487406952384309L;

	/**
	 * 分店名称
	 */
	private BranchTotal branch_name;
//	private String branch_name;
	/**
	 * 分店系统首次登录所生成的自编码
	 */
	private String branch_selfcode;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "branch_name_id")
	public BranchTotal getBranch_name() {
		return branch_name;
	}
	
	public void setBranch_name(BranchTotal branch_name) {
		this.branch_name = branch_name;
	}

//	@Column(name = "branch_name")
//	public String getBranch_name() {
//		return branch_name;
//	}
//
//	public void setBranch_name(String branch_name) {
//		this.branch_name = branch_name;
//	}
	
	@Column(name = "branch_selfcode")
	public String getBranch_selfCode() {
		return branch_selfcode;
	}

	public void setBranch_selfCode(String branch_selfcode) {
		this.branch_selfcode = branch_selfcode;
	}
	
}
