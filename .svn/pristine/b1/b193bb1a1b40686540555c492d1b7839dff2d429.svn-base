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
import com.proem.exm.entity.basic.branch.Branch;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.system.CtpUser;

/**
 * 商品库存表
 * @author admin
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_STOREHOUSE")
public class ZcStorehouse extends Root {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -7593818398089799059L;
	/**
	 * 分店
	 */
	private Branch branch;
	/**
	 * 商品
	 */
	private GoodsFile goodsFile;
	/**
	 * 库存
	 */
	private String store;
	/**
	 * 库存商品价值总额
	 */
	private String storeMoney;
	/**
	 * 状态
	 */
	private int status;

	private CtpUser createUser;

	@Column(name = "STOREMONEY")
	public String getStoreMoney() {
		return storeMoney;
	}

	public void setStoreMoney(String storeMoney) {
		this.storeMoney = storeMoney;
	}

	@Column(name = "status")
	public int getStatus() {
		return status;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_id")
	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "goodsFile_id")
	public GoodsFile getGoodsFile() {
		return goodsFile;
	}

	public void setGoodsFile(GoodsFile goodsFile) {
		this.goodsFile = goodsFile;
	}

	@Column(name = "STORE")
	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "createUser_id")
	public CtpUser getCreateUser() {
		return createUser;
	}

	public void setCreateUser(CtpUser createUser) {
		this.createUser = createUser;
	}

}
