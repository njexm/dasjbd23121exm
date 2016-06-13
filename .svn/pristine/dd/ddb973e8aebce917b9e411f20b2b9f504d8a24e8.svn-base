package com.proem.exm.entity.branch;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;

/**
 * 日常收货商品表
 * 
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_DAILY_RECEIVE")
public class DailyReceiveGoods extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 类别ID
	 */
	private String classify;
	/**
	 * 分店ID
	 */
	private String branch;
	/**
	 * 货号
	 */
	private String serialnumber;
	/**
	 * 品名
	 */
	private String goodsName;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 规格
	 */
	private String specifications;
	/**
	 * 单价
	 */
	private String goodsPrice;
	/**
	 * 订单商品数量
	 */
	private String goodsOrderNums;
	/**
	 * 实际数量
	 */
	private String actNums;
	/**
	 * 订单商品金额
	 */
	private String orderMoney;
	/**
	 * 实际商品金额
	 */
	private String actMoney;
	/**
	 * 收营员编号
	 */
	private String salesman;
	/**
	 * 收货日期
	 */
	private Date receiveDate;
	/**
	 * 
	 */
	private String sortenum;
	
	@Column(name = "sortenum")
	public String getSortenum() {
		return sortenum;
	}

	public void setSortenum(String sortenum) {
		this.sortenum = sortenum;
	}

	@Column(name = "CLASSIFY_ID")
	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	@Column(name = "BRANCH_ID")
	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	@Column(name = "SERIALNUMBER")
	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	@Column(name = "GOODSNAME")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "UNIT")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "SPECIFICATIONS")
	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	@Column(name = "GOODSPRICE")
	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	@Column(name = "GOODSORDERNUMS")
	public String getGoodsOrderNums() {
		return goodsOrderNums;
	}

	public void setGoodsOrderNums(String goodsOrderNums) {
		this.goodsOrderNums = goodsOrderNums;
	}

	@Column(name = "ACTNUMS")
	public String getActNums() {
		return actNums;
	}

	public void setActNums(String actNums) {
		this.actNums = actNums;
	}

	@Column(name = "ORDERMONEY")
	public String getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}

	@Column(name = "ACTMONEY")
	public String getActMoney() {
		return actMoney;
	}

	public void setActMoney(String actMoney) {
		this.actMoney = actMoney;
	}

	@Column(name = "SALESMAN")
	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	@Column(name = "RECEIVEDATE")
	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

}
