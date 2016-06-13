package com.proem.exm.entity.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "zc_order_refuse")
public class ZcOrderRefuse extends Root{

	private static final long serialVersionUID = 1L;
	/**
	 * 订单ID
	 */
	private String order_id;
	/**
	 * 订单金额
	 */
	private String order_amount;
	/**
	 * 实际金额
	 */
	private String actual_amount;
	
	@Column(name = "order_id")
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	@Column(name = "order_amount")
	public String getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(String order_amount) {
		this.order_amount = order_amount;
	}
	@Column(name = "actual_amount")
	public String getActual_amount() {
		return actual_amount;
	}
	public void setActual_amount(String actual_amount) {
		this.actual_amount = actual_amount;
	}
}
