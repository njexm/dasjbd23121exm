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
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.system.ZcUserInfo;

/**
 * 采购订单商品明细
 * 
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_CHANGE_ITEMS")
public class ChangeGoodsItems extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* 商品ID */
	private GoodsFile goodsFile;
	/**
	 * 数量
	 */
	private String changeNumber;
	/**
	 * 库存量
	 */
	private String store;
	/**
	 * 金额
	 */
	private String changeAmount;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 所对应调整单主表
	 */
	private ZcStoreChange storeChange;
	/**
	 * 创建人
	 */
	private CtpUser createUser;
	/**
	 * 临时添加标志
	 */
	private String editFlag;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODSFILE_ID")
	public GoodsFile getGoodsFile() {
		return goodsFile;
	}

	public void setGoodsFile(GoodsFile goodsFile) {
		this.goodsFile = goodsFile;
	}

	@Column(name = "editFlag")
	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	@Column(name = "changeNumber")
	public String getChangeNumber() {
		return changeNumber;
	}

	public void setChangeNumber(String changeNumber) {
		this.changeNumber = changeNumber;
	}

	@Column(name = "changeAmount")
	public String getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(String changeAmount) {
		this.changeAmount = changeAmount;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "storeChange_id")
	public ZcStoreChange getStoreChange() {
		return storeChange;
	}

	public void setStoreChange(ZcStoreChange storeChange) {
		this.storeChange = storeChange;
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

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

}
