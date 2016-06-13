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
import com.proem.exm.entity.basic.provider.ProviderInfo;
/**
 * 订单拉取详情实体类
 * @author acer
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_ORDER_ITEM")
public class ZcOrderItem extends Root {

	private static final long serialVersionUID = -7593818398089799059L;

	/**
	 * 订单ID
	 */
	private String order_id;
	/**
	 * 订单明细对应商品对象ID
	 */
	private String obj_id;
	/**
	 * 货品ID
	 */
	private String product_id;
	/**
	 * 商品ID
	 */
	private GoodsFile goodsFile;
	/**
	 * 商品类型
	 */
	private String type_id;
	/**
	 * 明细商品的品牌名
	 */
	private String bn;
	/**
	 * 明细商品的名称
	 */
	private String name;
	/**
	 * 明细商品的成本
	 */
	private String cost;
	/**
	 * 明细商品的销售价
	 */
	private String price;
	/**
	 * 明细商品的会员价
	 */
	private String g_price;
	/**
	 * 明细商品的总额
	 */
	private String amount;
	/**
	 * 明细商品积分
	 */
	private String score;
	/**
	 * 明细商品重量
	 */
	private String weight;
	/**
	 * 明细商品购买数量
	 */
	private String nums;
	/**
	 * 明细商品发货数量
	 */
	private String sendNum;

	/**
	 * 规格属性
	 */
	private Float addon;
	/**
	 * 商品类型
	 */
	private String item_type;

	/**
	 * 供应商ID
	 */
	private ProviderInfo providerInfo;
	/**
	 * 商品状态
	 */
	private String goodsState;

	@Column(name = "goods_state")
	public String getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(String goodsState) {
		this.goodsState = goodsState;
	}

	@Column(name = "order_id")
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	@Column(name = "obj_id")
	public String getObj_id() {
		return obj_id;
	}

	public void setObj_id(String obj_id) {
		this.obj_id = obj_id;
	}

	@Column(name = "product_id")
	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "goodsFile_id")
	public GoodsFile getGoodsFile() {
		return goodsFile;
	}

	public void setGoodsFile(GoodsFile goodsFile) {
		this.goodsFile = goodsFile;
	}

	@Column(name = "type_id")
	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	@Column(name = "bn")
	public String getBn() {
		return bn;
	}

	public void setBn(String bn) {
		this.bn = bn;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "cost")
	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	@Column(name = "price")
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Column(name = "g_price")
	public String getG_price() {
		return g_price;
	}

	public void setG_price(String g_price) {
		this.g_price = g_price;
	}

	@Column(name = "amount")
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Column(name = "score")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@Column(name = "weight")
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	@Column(name = "nums")
	public String getNums() {
		return nums;
	}

	public void setNums(String nums) {
		this.nums = nums;
	}

	@Column(name = "sendNum")
	public String getSendNum() {
		return sendNum;
	}

	public void setSendNum(String sendNum) {
		this.sendNum = sendNum;
	}

	@Column(name = "addon")
	public Float getAddon() {
		return addon;
	}

	public void setAddon(Float addon) {
		this.addon = addon;
	}

	@Column(name = "item_type")
	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROVIDER_ID")
	public ProviderInfo getProviderInfo() {
		return providerInfo;
	}

	public void setProviderInfo(ProviderInfo providerInfo) {
		this.providerInfo = providerInfo;
	}

}
