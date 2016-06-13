package com.proem.exm.entity.warehouse;

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
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.system.ZcZoning;

/**
 * 分拣从表实体类
 * 
 * @author songcj 2015年12月12日 下午3:36:42
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Zc_sorte_item")
public class SorteItem extends Root {

	private static final long serialVersionUID = 1L;
	// /**
	// * 分店编码
	// */
	// private String branchCode;
	// /**
	// * 分店名称
	// */
	// private String branchName;
	/**
	 * 分店负责人
	 */
	private ZcUserInfo customer;
	/**
	 * 分店地址
	 */
	private ZcZoning address;
	/**
	 * 主表id
	 */
	private Sorte sorteId;
	/**
	 * 分店id
	 */
	private BranchTotal branchTotalId;
	/**
	 * 分店地点与订单关联Id
	 */
	private String areaId;
	/**
	 * 分拣状态
	 */
	private int sortStatus;
	/**
	 * 备注
	 */
	private String remark;

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_total_id")
	public BranchTotal getBranchTotalId() {
		return branchTotalId;
	}

	public void setBranchTotalId(BranchTotal branchTotalId) {
		this.branchTotalId = branchTotalId;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "sorte_id")
	public Sorte getSorteId() {
		return sorteId;
	}

	public void setSorteId(Sorte sorteId) {
		this.sorteId = sorteId;
	}

	// @Column(name = "branch_code")
	// public String getBranchCode() {
	// return branchCode;
	// }
	// public void setBranchCode(String branchCode) {
	// this.branchCode = branchCode;
	// }
	// @Column(name = "branch_name")
	// public String getBranchName() {
	// return branchName;
	// }
	// public void setBranchName(String branchName) {
	// this.branchName = branchName;
	// }
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer")
	public ZcUserInfo getCustomer() {
		return customer;
	}

	public void setCustomer(ZcUserInfo customer) {
		this.customer = customer;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "address")
	public ZcZoning getAddress() {
		return address;
	}

	public void setAddress(ZcZoning address) {
		this.address = address;
	}

	@Column(name = "areaId")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Column(name = "sortStatus")
	public int getSortStatus() {
		return sortStatus;
	}

	public void setSortStatus(int sortStatus) {
		this.sortStatus = sortStatus;
	}

}
