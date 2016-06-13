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
 * 预付款从表实体类
 * @author songcj
 * 2015年11月25日 下午7:36:04 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Zc_advance_payment_item")
public class AdvancePaymentItem extends Root{

	private static final long serialVersionUID = 1L;

	/**
	 * 预付款金额
	 */
	private Double prepaymentMoney;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 代码
	 */
	private String code;
	/**
	 * 预付款主表
	 */
	private AdvancePayment advancePayment;
	/**
	 * 费用类型
	 */
	private String type;
	
	
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "prepayment_money")
	public Double getPrepaymentMoney() {
		return prepaymentMoney;
	}
	public void setPrepaymentMoney(Double prepaymentMoney) {
		this.prepaymentMoney = prepaymentMoney;
	}
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.EAGER)
	@JoinColumn(name = "advance_payment_id")
	public AdvancePayment getAdvancePayment() {
		return advancePayment;
	}
	public void setAdvancePayment(AdvancePayment advancePayment) {
		this.advancePayment = advancePayment;
	}
}
