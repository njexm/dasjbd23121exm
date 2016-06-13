package com.proem.exm.entity.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "zc_orders_sorte")
public class OrdersSorte extends Root {

	private static final long serialVersionUID = 1L;
	/**
	 * 分拣单ID
	 */
	private String sorteId;
	/**
	 * 商品名
	 */
	private String goodsName;
	/**
	 * 商品ID
	 */
	private String goodsId;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 订单份数
	 */
	private String ordersNum;
	/**
	 * 分拣份数
	 */
	private String sorteNum;
	/**
	 * 商品重量
	 */
	private String weight;

	@Column(name = "sorteId")
	public String getSorteId() {
		return sorteId;
	}

	public void setSorteId(String sorteId) {
		this.sorteId = sorteId;
	}

	@Column(name = "weight")
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	@Column(name = "goods_name")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "goods_id")
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "ordersNum")
	public String getOrdersNum() {
		return ordersNum;
	}

	public void setOrdersNum(String ordersNum) {
		this.ordersNum = ordersNum;
	}

	@Column(name = "sorteNum")
	public String getSorteNum() {
		return sorteNum;
	}

	public void setSorteNum(String sorteNum) {
		this.sorteNum = sorteNum;
	}

}
