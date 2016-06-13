package com.proem.exm.entity.warehouse;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;
import com.proem.exm.entity.system.CtpUser;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_CHECKDIFFERENCE")
public class ZcCheckDifference extends Root {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -7593818398089799059L;
	/**
	 * 盘点号
	 */
	private ZcCheckNumber checkNumber;
	/**
	 * 盘点单（与盘点单一对一对应）
	 */
	private ZcWarehouse zcWarehouse;
	/**
	 * 状态
	 */
	private int status;

	private CtpUser createUser;

	/**
	 * 审核人
	 */
	private CtpUser checkUser;

	/**
	 * 审核时间
	 */
	private Date checkTime;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "zcWarehouse_id")
	public ZcWarehouse getZcWarehouse() {
		return zcWarehouse;
	}

	public void setZcWarehouse(ZcWarehouse zcWarehouse) {
		this.zcWarehouse = zcWarehouse;
	}

	@Column(name = "status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "checkNumber_id")
	public ZcCheckNumber getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(ZcCheckNumber checkNumber) {
		this.checkNumber = checkNumber;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "checkUser_id")
	public CtpUser getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(CtpUser checkUser) {
		this.checkUser = checkUser;
	}

	@Column(name = "checkUser")
	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
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
