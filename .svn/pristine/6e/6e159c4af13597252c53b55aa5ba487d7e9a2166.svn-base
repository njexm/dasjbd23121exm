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
@Table(name = "ZC_SWITCH_ITEMS")
public class SwitchGoodsItems extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* 商品ID */
	private GoodsFile goodsFile;
	/**
	 * 箱数
	 */
	private String boxNumber;
	/**
	 *  数量 */
	private String changeNumber;
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
	private ZcSwitchhouse zcSwitchhouse;
	/**
	 * 创建人
	 */
	private CtpUser createUser;
	/**
	 * 删除标记
	 */
	private String delflag;
	
	@Column(name="delflag")
	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODSFILE_ID")
	public GoodsFile getGoodsFile() {
		return goodsFile;
	}

	public void setGoodsFile(GoodsFile goodsFile) {
		this.goodsFile = goodsFile;
	}

	@Column(name="changeNumber")
	public String getChangeNumber() {
		return changeNumber;
	}

	public void setChangeNumber(String changeNumber) {
		this.changeNumber = changeNumber;
	}

	@Column(name="changeAmount")
	public String getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(String changeAmount) {
		this.changeAmount = changeAmount;
	}
	
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "createUser_id")
	public CtpUser getCreateUser() {
		return createUser;
	}

	@Column(name="boxNumber")
	public String getBoxNumber() {
		return boxNumber;
	}

	public void setBoxNumber(String boxNumber) {
		this.boxNumber = boxNumber;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "zcSwitchhouse_id")
	public ZcSwitchhouse getZcSwitchhouse() {
		return zcSwitchhouse;
	}

	public void setZcSwitchhouse(ZcSwitchhouse zcSwitchhouse) {
		this.zcSwitchhouse = zcSwitchhouse;
	}

	public void setCreateUser(CtpUser createUser) {
		this.createUser = createUser;
	}


	

}
