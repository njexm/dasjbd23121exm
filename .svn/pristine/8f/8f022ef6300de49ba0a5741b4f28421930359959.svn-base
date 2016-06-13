package com.proem.exm.entity.order;

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

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "zc_order_refuse_item")
public class ZcOrderRefuseItem extends Root{

	private static final long serialVersionUID = 1L;
	/**
	 * 货号
	 */
	private String serialNumber;
	/**
	 * 订单ID
	 */
	private String order_id;
	/**
	 * 订单数量
	 */
	private String order_nums;
	/**
	 * 拒收数量
	 */
	private String refuse_nums;
	/**
	 * 价格
	 */
	private String price;
	/**
	 * 拒收金额
	 */
	private String refuse_amount;
	/**
	 * 收货员ID
	 */
	private String salesman_id;
	/**
	 * 收货日期
	 */
	private String hand_date;
	/**
	 * 地址
	 */
	private String street;
	/**
	 * 商品ID
	 */
	private GoodsFile goodsFile_id;
	/**
	 * 拒收ID
	 */
	private ZcOrderRefuse refuse_id;
	/**
	 * 拒收原因
	 */
	private String refuse_reason;
	
	
	@Column(name = "serialNumber")
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	@Column(name = "order_id")
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	@Column(name = "order_nums")
	public String getOrder_nums() {
		return order_nums;
	}
	public void setOrder_nums(String order_nums) {
		this.order_nums = order_nums;
	}
	@Column(name = "refuse_nums")
	public String getRefuse_nums() {
		return refuse_nums;
	}
	public void setRefuse_nums(String refuse_nums) {
		this.refuse_nums = refuse_nums;
	}
	@Column(name = "price")
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Column(name = "refuse_amount")
	public String getRefuse_amount() {
		return refuse_amount;
	}
	public void setRefuse_amount(String refuse_amount) {
		this.refuse_amount = refuse_amount;
	}
	@Column(name = "salesman_id")
	public String getSalesman_id() {
		return salesman_id;
	}
	public void setSalesman_id(String salesman_id) {
		this.salesman_id = salesman_id;
	}
	@Column(name = "hand_date")
	public String getHand_date() {
		return hand_date;
	}
	public void setHand_date(String hand_date) {
		this.hand_date = hand_date;
	}
	@Column(name = "street")
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "goodsfile_id")
	public GoodsFile getGoodsFile_id() {
		return goodsFile_id;
	}
	public void setGoodsFile_id(GoodsFile goodsFile_id) {
		this.goodsFile_id = goodsFile_id;
	}
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "refuse_id")
	public ZcOrderRefuse getrefuse_id() {
		return refuse_id;
	}
	public void setRefuse_id(ZcOrderRefuse refuse_id) {
		this.refuse_id = refuse_id;
	}
	@Column(name = "refuse_reason")
	public String getRefuse_reason() {
		return refuse_reason;
	}
	public void setRefuse_reason(String refuse_reason) {
		this.refuse_reason = refuse_reason;
	}
}
