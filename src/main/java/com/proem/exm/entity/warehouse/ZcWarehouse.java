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
import com.proem.exm.entity.system.CtpUser;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_WAREHOUSE")
public class ZcWarehouse extends Root{
	/**
	 * 序列化	
	 */
	private static final long serialVersionUID = -7593818398089799059L;
	/**
	 * 盘点号
	 */
	private ZcCheckNumber checkNumber;
	/**
	 * 盘点单编号
	 */
	private String warehouseNumber;
	/**
	 * 盘点状态
	 */
	private int status;
	/**
	 * 审核不通过原因
	 */
	private String reason;
	
	private CtpUser createUser;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "checkNumber_id")
	public ZcCheckNumber getCheckNumber() {
		return checkNumber;
	}
	public void setCheckNumber(ZcCheckNumber checkNumber) {
		this.checkNumber = checkNumber;
	}
	
	
	@Column(name="warehouseNumber")
	public String getWarehouseNumber() {
		return warehouseNumber;
	}
	public void setWarehouseNumber(String warehouseNumber) {
		this.warehouseNumber = warehouseNumber;
	}
	
	@Column(name="status")
	public int getStatus() {
		return status;
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
	
	@Column(name="reason")
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
		
	
	
	
	
	
	

}
