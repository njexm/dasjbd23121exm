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
import com.proem.exm.entity.basic.associator.Associator;
import com.proem.exm.entity.system.ZcZoning;

/**
 * 订单处理是实体类
 * @author acer
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_ORDER_Process")
public class ZcProcessOrder extends Root {

	private static final long serialVersionUID = -7593818398089799059L;

	/**
	 * 订单编号
	 */
	private String orderNum;
	/**
	 * 订单总额
	 */
	private double orderTotalValue;
	/**
	 * 订单状态
	 */
	private String orderStatus;
	/**
	 * 订单日期
	 */
	private Date orderDate;
	/**
	 * 订单来源
	 */
	private String orderCome;
	/**
	 * 收货人
	 */
	private String consignee;
	/**
	 * 收货电话
	 */
	private String cansignPhone;
	/**
	 * 收货地址
	 */
	private ZcZoning zcZoning;
	/**
	 * 订单金额
	 */
	private double orderAmount;
	/**
	 * 优惠金额
	 */
	private double orderReduceAmount;
	/**
	 * 有无赠品
	 */
	private String isGift;
	/**
	 * 会员卡号
	 */
	private String memberCardNumber;
	/**
	 * 商品数量
	 */
	private int goodsNum;
	/**
	 * 拉取标志
	 */
	private String pullFlag;
	/**
	 */
	private Associator associator;
	/**
	 * 对应亭点ID
	 */
	private String branchId;
	@Column(name = "orderNum")
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	@Column(name = "orderTotalValue")
	public double getOrderTotalValue() {
		return orderTotalValue;
	}

	public void setOrderTotalValue(double orderTotalValue) {
		this.orderTotalValue = orderTotalValue;
	}

	@Column(name = "orderStatus")
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Column(name = "orderDate")
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Column(name = "orderCome")
	public String getOrderCome() {
		return orderCome;
	}

	public void setOrderCome(String orderCome) {
		this.orderCome = orderCome;
	}

	@Column(name = "consignee")
	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	@Column(name = "cansignPhone")
	public String getCansignPhone() {
		return cansignPhone;
	}

	public void setCansignPhone(String cansignPhone) {
		this.cansignPhone = cansignPhone;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ZCZONING_ID")
	public ZcZoning getZcZoning() {
		return zcZoning;
	}

	public void setZcZoning(ZcZoning zcZoning) {
		this.zcZoning = zcZoning;
	}

	@Column(name = "orderAmount")
	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	@Column(name = "orderReduceAmount")
	public double getOrderReduceAmount() {
		return orderReduceAmount;
	}

	public void setOrderReduceAmount(double orderReduceAmount) {
		this.orderReduceAmount = orderReduceAmount;
	}

	@Column(name = "isGift")
	public String getIsGift() {
		return isGift;
	}

	public void setIsGift(String isGift) {
		this.isGift = isGift;
	}

	@Column(name = "memberCardNumber")
	public String getMemberCardNumber() {
		return memberCardNumber;
	}

	public void setMemberCardNumber(String memberCardNumber) {
		this.memberCardNumber = memberCardNumber;
	}

	@Column(name = "goodsNum")
	public int getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
	}

	@Column(name = "PULLFLAG")
	public String getPullFlag() {
		return pullFlag;
	}

	public void setPullFlag(String pullFlag) {
		this.pullFlag = pullFlag;
	}
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	public Associator getAssociator() {
		return associator;
	}

	public void setAssociator(Associator associator) {
		this.associator = associator;
	}
	
	@Column(name = "branchId")
	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
}
