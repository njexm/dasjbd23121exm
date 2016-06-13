package com.proem.exm.entity.branch;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;

/**
 * 支付明细
 * @author xuehr
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_PAYINFO_ITEM")
public class PayInfoItem extends Root{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//支付id
	private String payInfoId;
	
	//支付模式
	private String payMode;
	
	//金额
	private String money;
	
	@Column(name = "payInfo_id")
	public String getPayInfoId() {
		return payInfoId;
	}

	public void setPayInfoId(String payInfoId) {
		this.payInfoId = payInfoId;
	}
	
	@Column(name = "pay_mode")
	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	@Column(name = "money")
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
	
}
