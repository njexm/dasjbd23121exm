package com.proem.exm.entity.settlement;

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

/**
 * 供应商费用从表实体类
 * @author songcj
 * 2015年11月30日 上午10:56:37 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Zc_supplier_cost_item")
public class SupplierCostItem extends Root{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 金额
	 */
	private String money;
	/**
	 * 费用名称
	 */
	private String costName;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 费用主表
	 */
	private SupplierCost costId;
	
	@Column(name = "money")
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	@Column(name = "cost_name")
	public String getCostName() {
		return costName;
	}
	public void setCostName(String costName) {
		this.costName = costName;
	}
	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.EAGER)
	@JoinColumn(name = "supplier_cost_id")
	public SupplierCost getCostId() {
		return costId;
	}
	public void setCostId(SupplierCost costId) {
		this.costId = costId;
	}
}
