package com.proem.exm.entity.order;

import java.util.Date;

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

/**
 * 退货从表实体类
 * @author songcj
 * 2016年1月25日 下午2:56:47 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "zc_order_refund_item")
public class ZcOrderRefundItem extends Root{

	private static final long serialVersionUID = 1L;

	/**
	 * 商品编号
	 */
	private String serialnumber;
	/**
	 * 订单ID
	 */
	private String order_id;
	/**
	 * 订单数量
	 */
	private String order_nums;
	/**
	 * 退货数量
	 */
	private String refund_nums;
	/**
	 * 价格
	 */
	private String price;
	/**
	 * 退货金额
	 */
	private String refund_amount;
	/**
	 * 销售员ID
	 */
	private String salesman_id;
//	/**
//	 * 提货日期
//	 */
//	private Date hand_date;
	/**
	 * 地址
	 */
	private String street;
	/**
	 * 商品ID
	 */
	private GoodsFile goodsfile_id;
	/**
	 * 退货主表
	 */
	private ZcOrderRefund refund_id;
	/**
	 * 退货原因
	 */
	private String refund_reason;
	
	@Column(name = "serialnumber")
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
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
	@Column(name = "refund_nums")
	public String getRefund_nums() {
		return refund_nums;
	}
	public void setRefund_nums(String refund_nums) {
		this.refund_nums = refund_nums;
	}
	@Column(name = "price")
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Column(name = "refund_amount")
	public String getRefund_amount() {
		return refund_amount;
	}
	public void setRefund_amount(String refund_amount) {
		this.refund_amount = refund_amount;
	}
	@Column(name = "salesman_id")
	public String getSalesman_id() {
		return salesman_id;
	}
	public void setSalesman_id(String salesman_id) {
		this.salesman_id = salesman_id;
	}
//	@Column(name = "hand_date")
//	public Date getHand_date() {
//		return hand_date;
//	}
//	public void setHand_date(Date hand_date) {
//		this.hand_date = hand_date;
//	}
	@Column(name = "street")
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "goodsfile_id")
	public GoodsFile getGoodsfile_id() {
		return goodsfile_id;
	}
	public void setGoodsfile_id(GoodsFile goodsfile_id) {
		this.goodsfile_id = goodsfile_id;
	}
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "refund_id")
	public ZcOrderRefund getRefund_id() {
		return refund_id;
	}
	public void setRefund_id(ZcOrderRefund refund_id) {
		this.refund_id = refund_id;
	}
	@Column(name = "refund_reason")
	public String getRefund_reason() {
		return refund_reason;
	}
	public void setRefund_reason(String refund_reason) {
		this.refund_reason = refund_reason;
	}
	
	
	
}
