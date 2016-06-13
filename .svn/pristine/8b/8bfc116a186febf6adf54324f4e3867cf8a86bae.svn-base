package com.proem.exm.entity.order;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;

/**
 * 退货主表实体类
 * @author songcj
 * 2016年1月25日 下午2:57:07 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "zc_order_refund")
public class ZcOrderRefund extends Root{

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
	 * 实际退货金额
	 */
	private String actual_amount;
	/**
	 * 退货状态
	 */
	private String order_refund_status;
	/**
	 * 提货日期
	 */
	private Date hand_date;
	
	@Column(name = "hand_date")
	public Date getHand_date() {
		return hand_date;
	}
	public void setHand_date(Date hand_date) {
		this.hand_date = hand_date;
	}
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
	@Column(name = "order_refund_status")
	public String getOrder_refund_status() {
		return order_refund_status;
	}
	public void setOrder_refund_status(String order_refund_status) {
		this.order_refund_status = order_refund_status;
	}
	
	
}
