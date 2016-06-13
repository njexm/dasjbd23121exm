package com.proem.exm.entity.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_ORDER_Digits")
public class ZcOrderDigits extends Root {
	
	private static final long serialVersionUID = -7593818398089799059L;
	/**
	 * 修改精度后的金额
	 */
	private String digitsAmount;
	/**
	 * 数值具体的精度
	 */
	public String count_odsi;
	/**
	 * 金额精度
	 */
	public String moneyAccuracy;
	/**
	 * 原金额
	 */
	private String orderAmount;
	/**
	 * 类型
	 */
	private String type;
	
	@Column(name = "money_accuracy")
	public String getMoneyAccuracy() {
		return moneyAccuracy;
	}
	public void setMoneyAccuracy(String moneyAccuracy) {
		this.moneyAccuracy = moneyAccuracy;
	}
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "digitsAmount")
	public String getDigitsAmount() {
		return digitsAmount;
	}
	public void setDigitsAmount(String digitsAmount) {
		this.digitsAmount = digitsAmount;
	}

	@Column(name = "countodsi")
	public String getCount_odsi() {
		return count_odsi;
	}
	public void setCount_odsi(String count_odsi) {
		this.count_odsi = count_odsi;
	}

	@Column(name = "orderAmount")
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		 this.orderAmount = orderAmount;
	}
	
}