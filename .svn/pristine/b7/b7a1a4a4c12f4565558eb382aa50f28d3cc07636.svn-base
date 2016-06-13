package com.proem.exm.entity.basic.goodsFile;

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
import com.proem.exm.entity.basic.CommodityClassify.CommodityClassify;
import com.proem.exm.entity.basic.CommodityClassify.GoodsType;
import com.proem.exm.entity.basic.provider.ProviderInfo;
import com.proem.exm.entity.system.CtpUser;

/**
 * @author songcj
 * 
 *         2015年10月20日上午10:48:54
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Zc_goods_master")
public class GoodsFile extends Root {

	private static final long serialVersionUID = 1L;

	// 商品编码
	private String goods_code;
	// 商品价格
	private Float goods_price;
	// 供应商
	private ProviderInfo provider;
	// //计价方式
	// private ProviderInfo goods_operation_mode;
	// 商品规格
	private String goods_specifications;
	// 删除标记
	private String delflag;
	// 商品类别
	private CommodityClassify goods_class;
	// 商品品牌
	private CommodityClassify goods_brand;
	// 商品名
	private String goods_name;
	// 商品单位
	private String goods_unit;
	// 产地
	private String goods_origin;
	// 进价
	private Float goods_purchase_price;
	// 最低售价
	private Float lowest_price;
	// 批发价
	private Float wholesale_price;
	// 配送价
	private Float distribution_price;
	// 会员价
	private Float member_price;
	// 积分否
	private String point_or_not;
	// 积分值
	private Float points_value;
	// 进项税
	private Float input_tax;
	// 销项税
	private Float out_tax;
	// 毛利率
	private Float gross_margin;
	// 进货规格
	private String purchase_specifications;
	// 批发价2
	private Float wholesale_price2;
	// 批发价3
	private Float wholesale_price3;
	// 批发价4
	private Float wholesale_price4;
	// 批发价5
	private Float wholesale_price5;
	// 批发价6
	private Float wholesale_price6;
	// 批发价7
	private Float wholesale_price7;
	// 批发价8
	private Float wholesale_price8;
	// 会员价2
	private Float member_price2;
	// 会员价3
	private Float member_price3;
	// 会员价4
	private Float member_price4;
	// 会员价5
	private Float member_price5;
	// 管理库存
	private String management_inventory;
	// 商品类型
	private String goods_type;
	// 采购状态
	private String procurement_status;
	// 计价方式
	private String valuation_method;
	// 有效期
	private Float validity_period;
	// 一级预警天数
	private Float early_warning_days;
	// 二级预警天数
	private Float early_warning_days2;
	// 备注
	private String remark;
	// 联营扣率
	private Float joint_rate;
	// 商品属性
	private String goods_property;
	// 商品状态
	private String goods_state;
	// 货号
	private String serialNumber;
	/**
	 * 库存
	 */
	private String store;
	/**
	 * 拼音码
	 */
	private String goods_PY_code;
	/**
	 * 业务员
	 */
	private CtpUser ctpUser_goodsfile;
	/**
	 * 折扣比例
	 */
	private Double goods_Discount_rate;
	/**
	 * 工位属性
	 */
	private String zcUserInfo;
	/**
	 * 截单类型
	 */
	private GoodsType goodsType;
	/**
	 * 商品属性(成品/原材料)
	 */
	private String goodsAttribute;
	/**
	 * 成品ID
	 */
	private String productGoodsId;
	/**
	 * 损耗率
	 */
	private String wasteRate;

	@Column(name = "GOODSATTRIBUTE")
	public String getGoodsAttribute() {
		return goodsAttribute;
	}

	public void setGoodsAttribute(String goodsAttribute) {
		this.goodsAttribute = goodsAttribute;
	}

	@Column(name = "PRODUCTGOODSID")
	public String getProductGoodsId() {
		return productGoodsId;
	}

	public void setProductGoodsId(String productGoodsId) {
		this.productGoodsId = productGoodsId;
	}

	@Column(name = "WASTERATE")
	public String getWasteRate() {
		return wasteRate;
	}

	public void setWasteRate(String wasteRate) {
		this.wasteRate = wasteRate;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODSTYPE_ID")
	public GoodsType getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(GoodsType goodsType) {
		this.goodsType = goodsType;
	}

	@Column(name = "ZCUSERINFO")
	public String getZcUserInfo() {
		return zcUserInfo;
	}

	public void setZcUserInfo(String zcUserInfo) {
		this.zcUserInfo = zcUserInfo;
	}

	@Column(name = "goods_state")
	public String getGoods_state() {
		return goods_state;
	}

	public void setGoods_state(String goods_state) {
		this.goods_state = goods_state;
	}

	@Column(name = "goods_property")
	public String getGoods_property() {
		return goods_property;
	}

	public void setGoods_property(String goods_property) {
		this.goods_property = goods_property;
	}

	@Column(name = "goods_code")
	public String getGoods_code() {
		return goods_code;
	}

	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}

	@Column(name = "goods_price")
	public Float getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(Float goods_price) {
		this.goods_price = goods_price;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "goods_supplier_id")
	public ProviderInfo getProvider() {
		return provider;
	}

	public void setProvider(ProviderInfo provider) {
		this.provider = provider;
	}

	// @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
	// CascadeType.REFRESH},fetch = FetchType.EAGER)
	// @JoinColumn(name = "getGoods_operation_mode_id")
	// public ProviderInfo getGoods_operation_mode() {
	// return goods_operation_mode;
	// }
	// public void setGoods_operation_mode(ProviderInfo goods_operation_mode) {
	// this.goods_operation_mode = goods_operation_mode;
	// }
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "goods_class_id")
	public CommodityClassify getGoods_class() {
		return goods_class;
	}

	public void setGoods_class(CommodityClassify classify) {
		this.goods_class = classify;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "goods_brand_id")
	public CommodityClassify getGoods_brand() {
		return goods_brand;
	}

	public void setGoods_brand(CommodityClassify goods_brand) {
		this.goods_brand = goods_brand;
	}

	@Column(name = "goods_specifications")
	public String getGoods_specifications() {
		return goods_specifications;
	}

	public void setGoods_specifications(String goods_specifications) {
		this.goods_specifications = goods_specifications;
	}

	@Column(name = "delflag")
	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	@Column(name = "goods_name")
	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	@Column(name = "goods_unit")
	public String getGoods_unit() {
		return goods_unit;
	}

	public void setGoods_unit(String goods_unit) {
		this.goods_unit = goods_unit;
	}

	@Column(name = "goods_origin")
	public String getGoods_origin() {
		return goods_origin;
	}

	public void setGoods_origin(String goods_origin) {
		this.goods_origin = goods_origin;
	}

	@Column(name = "goods_purchase_price")
	public Float getGoods_purchase_price() {
		return goods_purchase_price;
	}

	public void setGoods_purchase_price(Float goods_purchase_price) {
		this.goods_purchase_price = goods_purchase_price;
	}

	@Column(name = "lowest_price")
	public Float getLowest_price() {
		return lowest_price;
	}

	public void setLowest_price(Float lowest_price) {
		this.lowest_price = lowest_price;
	}

	@Column(name = "wholesale_price")
	public Float getWholesale_price() {
		return wholesale_price;
	}

	public void setWholesale_price(Float wholesale_price) {
		this.wholesale_price = wholesale_price;
	}

	@Column(name = "distribution_price")
	public Float getDistribution_price() {
		return distribution_price;
	}

	public void setDistribution_price(Float distribution_price) {
		this.distribution_price = distribution_price;
	}

	@Column(name = "member_price")
	public Float getMember_price() {
		return member_price;
	}

	public void setMember_price(Float member_price) {
		this.member_price = member_price;
	}

	@Column(name = "point_or_not")
	public String getPoint_or_not() {
		return point_or_not;
	}

	public void setPoint_or_not(String point_or_not) {
		this.point_or_not = point_or_not;
	}

	@Column(name = "points_value")
	public Float getPoints_value() {
		return points_value;
	}

	public void setPoints_value(Float points_value) {
		this.points_value = points_value;
	}

	@Column(name = "input_tax")
	public Float getInput_tax() {
		return input_tax;
	}

	public void setInput_tax(Float input_tax) {
		this.input_tax = input_tax;
	}

	@Column(name = "out_tax")
	public Float getOut_tax() {
		return out_tax;
	}

	public void setOut_tax(Float out_tax) {
		this.out_tax = out_tax;
	}

	@Column(name = "gross_margin")
	public Float getGross_margin() {
		return gross_margin;
	}

	public void setGross_margin(Float gross_margin) {
		this.gross_margin = gross_margin;
	}

	@Column(name = "purchase_specifications")
	public String getPurchase_specifications() {
		return purchase_specifications;
	}

	public void setPurchase_specifications(String purchase_specifications) {
		this.purchase_specifications = purchase_specifications;
	}

	@Column(name = "wholesale_price2")
	public Float getWholesale_price2() {
		return wholesale_price2;
	}

	public void setWholesale_price2(Float wholesale_price2) {
		this.wholesale_price2 = wholesale_price2;
	}

	@Column(name = "wholesale_price3")
	public Float getWholesale_price3() {
		return wholesale_price3;
	}

	public void setWholesale_price3(Float wholesale_price3) {
		this.wholesale_price3 = wholesale_price3;
	}

	@Column(name = "wholesale_price4")
	public Float getWholesale_price4() {
		return wholesale_price4;
	}

	public void setWholesale_price4(Float wholesale_price4) {
		this.wholesale_price4 = wholesale_price4;
	}

	@Column(name = "wholesale_price5")
	public Float getWholesale_price5() {
		return wholesale_price5;
	}

	public void setWholesale_price5(Float wholesale_price5) {
		this.wholesale_price5 = wholesale_price5;
	}

	@Column(name = "wholesale_price6")
	public Float getWholesale_price6() {
		return wholesale_price6;
	}

	public void setWholesale_price6(Float wholesale_price6) {
		this.wholesale_price6 = wholesale_price6;
	}

	@Column(name = "wholesale_price7")
	public Float getWholesale_price7() {
		return wholesale_price7;
	}

	public void setWholesale_price7(Float wholesale_price7) {
		this.wholesale_price7 = wholesale_price7;
	}

	@Column(name = "wholesale_price8")
	public Float getWholesale_price8() {
		return wholesale_price8;
	}

	public void setWholesale_price8(Float wholesale_price8) {
		this.wholesale_price8 = wholesale_price8;
	}

	@Column(name = "member_price2")
	public Float getMember_price2() {
		return member_price2;
	}

	public void setMember_price2(Float member_price2) {
		this.member_price2 = member_price2;
	}

	@Column(name = "member_price3")
	public Float getMember_price3() {
		return member_price3;
	}

	public void setMember_price3(Float member_price3) {
		this.member_price3 = member_price3;
	}

	@Column(name = "member_price4")
	public Float getMember_price4() {
		return member_price4;
	}

	public void setMember_price4(Float member_price4) {
		this.member_price4 = member_price4;
	}

	@Column(name = "member_price5")
	public Float getMember_price5() {
		return member_price5;
	}

	public void setMember_price5(Float member_price5) {
		this.member_price5 = member_price5;
	}

	@Column(name = "management_inventory")
	public String getManagement_inventory() {
		return management_inventory;
	}

	public void setManagement_inventory(String management_inventory) {
		this.management_inventory = management_inventory;
	}

	@Column(name = "goods_type")
	public String getGoods_type() {
		return goods_type;
	}

	public void setGoods_type(String goods_type) {
		this.goods_type = goods_type;
	}

	@Column(name = "procurement_status")
	public String getProcurement_status() {
		return procurement_status;
	}

	public void setProcurement_status(String procurement_status) {
		this.procurement_status = procurement_status;
	}

	@Column(name = "valuation_method")
	public String getValuation_method() {
		return valuation_method;
	}

	public void setValuation_method(String valuation_method) {
		this.valuation_method = valuation_method;
	}

	@Column(name = "validity_period")
	public Float getValidity_period() {
		return validity_period;
	}

	public void setValidity_period(Float validity_period) {
		this.validity_period = validity_period;
	}

	@Column(name = "early_warning_days")
	public Float getEarly_warning_days() {
		return early_warning_days;
	}

	public void setEarly_warning_days(Float early_warning_days) {
		this.early_warning_days = early_warning_days;
	}

	@Column(name = "early_warning_days2")
	public Float getEarly_warning_days2() {
		return early_warning_days2;
	}

	public void setEarly_warning_days2(Float early_warning_days2) {
		this.early_warning_days2 = early_warning_days2;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "joint_rate")
	public Float getJoint_rate() {
		return joint_rate;
	}

	public void setJoint_rate(Float joint_rate) {
		this.joint_rate = joint_rate;
	}

	@Column(name = "serialNumber")
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Column(name = "store")
	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	/**
	 * 拼音码
	 * 
	 * @return
	 */
	@Column(name = "goods_PY_code")
	public String getGoods_PY_code() {
		return goods_PY_code;
	}

	public void setGoods_PY_code(String goods_PY_code) {
		this.goods_PY_code = goods_PY_code;
	}

	/**
	 * 业务员（CtpUser）
	 * 
	 * @return
	 */
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "goodsfile_userid")
	public CtpUser getCtpUser_goodsfile() {
		return ctpUser_goodsfile;
	}

	public void setCtpUser_goodsfile(CtpUser ctpUser_goodsfile) {
		this.ctpUser_goodsfile = ctpUser_goodsfile;
	}

	/**
	 * 折扣比例
	 * 
	 * @return
	 */
	@Column(name = "goods_Discount_rate")
	public Double getGoods_Discount_rate() {
		return goods_Discount_rate;
	}

	public void setGoods_Discount_rate(Double goods_Discount_rate) {
		this.goods_Discount_rate = goods_Discount_rate;
	}

}
