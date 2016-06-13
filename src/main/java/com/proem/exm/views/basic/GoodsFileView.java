package com.proem.exm.views.basic;

import com.proem.exm.views.BaseView;

public class GoodsFileView extends BaseView{
	
	private String goods_code;
	//商品编码
	private String goods_class_id ;
	//商品类别
	private Float goods_price;
	//商品价格
	private String goods_supplier_id;
	//供应商
	private String goods_specifications;
	//商品规格
	private Integer delflag;
	//删除标记
	private String goods_brand_id ;
	//商品品牌
	private String goods_name;
	//品名
	private String goods_unit;
	//商品单位
	private String goods_origin;
	//产地
	private String goods_operation_mode;
	//经营方式
	private Float goods_purchase_price;
	//进价
	private Float lowest_price;
	//最低售价
	private Float wholesale_price;
	//批发价
	private Float distribution_price;
	//配送价
	private Float member_price;
	//会员价
	private String point_or_not;
	//积分否
	private Float points_value;
	//积分值
	private Float input_tax;
	//进项税
	private Float out_tax;
	//销项税
	private Float gross_margin;
	//毛利率
	private String purchase_specifications;
	//进货规格
	private Float wholesale_price2;
	//批发价2
	private Float wholesale_price3;
	//批发价3
	private Float wholesale_price4;
	//批发价4
	private Float wholesale_price5;
	//批发价5
	private Float wholesale_price6;
	//批发价6
	private Float wholesale_price7;
	//批发价7
	private Float wholesale_price8;
	//批发价8
	private Float member_price2;
	//会员价2
	private Float member_price3;
	//会员价3
	private Float member_price4;
	//会员价4
	private Float member_price5;
	//会员价5
	private String management_inventory;
	//管理库存
	private String goods_type;
	//商品类型
	private String procurement_status;
	//采购状态
	private String valuation_method;
	//计价方式
	private Float validity_period;
	//有效期
	private Float early_warning_days;
	//一级预警天数
	private Float early_warning_days2;
	//二级预警天数
	private String remark;
	//备注
	private String goods_property;
	//商品属性
	private Float joint_rate;
	//联营扣率
	public Float getJoint_rate() {
		return joint_rate;
	}
	public void setJoint_rate(Float joint_rate) {
		this.joint_rate = joint_rate;
	}
	public String getGoods_property() {
		return goods_property;
	}
	public void setGoods_property(String goods_property) {
		this.goods_property = goods_property;
	}
	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	public String getGoods_class_id () {
		return goods_class_id ;
	}
	public void setGoods_class_id (String goods_class_id ) {
		this.goods_class_id  = goods_class_id ;
	}
	public Float getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(Float goods_price) {
		this.goods_price = goods_price;
	}
	public String getGoods_supplier_id() {
		return goods_supplier_id;
	}
	public void setGoods_supplier_id(String goods_supplier_id) {
		this.goods_supplier_id = goods_supplier_id;
	}
	public String getGoods_specifications() {
		return goods_specifications;
	}
	public void setGoods_specifications(String goods_specifications) {
		this.goods_specifications = goods_specifications;
	}
	public Integer getDelflag() {
		return delflag;
	}
	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}
	public String getGoods_brand_id () {
		return goods_brand_id ;
	}
	public void setGoods_brand_id (String goods_brand_id ) {
		this.goods_brand_id  = goods_brand_id ;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_unit() {
		return goods_unit;
	}
	public void setGoods_unit(String goods_unit) {
		this.goods_unit = goods_unit;
	}
	public String getGoods_origin() {
		return goods_origin;
	}
	public void setGoods_origin(String goods_origin) {
		this.goods_origin = goods_origin;
	}
	public String getGoods_operation_mode() {
		return goods_operation_mode;
	}
	public void setGoods_operation_mode(String goods_operation_mode) {
		this.goods_operation_mode = goods_operation_mode;
	}
	public Float getGoods_purchase_price() {
		return goods_purchase_price;
	}
	public void setGoods_purchase_price(Float goods_purchase_price) {
		this.goods_purchase_price = goods_purchase_price;
	}
	public Float getLowest_price() {
		return lowest_price;
	}
	public void setLowest_price(Float lowest_price) {
		this.lowest_price = lowest_price;
	}
	public Float getWholesale_price() {
		return wholesale_price;
	}
	public void setWholesale_price(Float wholesale_price) {
		this.wholesale_price = wholesale_price;
	}
	public Float getDistribution_price() {
		return distribution_price;
	}
	public void setDistribution_price(Float distribution_price) {
		this.distribution_price = distribution_price;
	}
	public Float getMember_price() {
		return member_price;
	}
	public void setMember_price(Float member_price) {
		this.member_price = member_price;
	}
	public String getPoint_or_not() {
		return point_or_not;
	}
	public void setPoint_or_not(String point_or_not) {
		this.point_or_not = point_or_not;
	}
	public Float getPoints_value() {
		return points_value;
	}
	public void setPoints_value(Float points_value) {
		this.points_value = points_value;
	}
	public Float getInput_tax() {
		return input_tax;
	}
	public void setInput_tax(Float input_tax) {
		this.input_tax = input_tax;
	}
	public Float getOut_tax() {
		return out_tax;
	}
	public void setOut_tax(Float out_tax) {
		this.out_tax = out_tax;
	}
	public Float getGross_margin() {
		return gross_margin;
	}
	public void setGross_margin(Float gross_margin) {
		this.gross_margin = gross_margin;
	}
	public String getPurchase_specifications() {
		return purchase_specifications;
	}
	public void setPurchase_specifications(String purchase_specifications) {
		this.purchase_specifications = purchase_specifications;
	}
	public Float getWholesale_price2() {
		return wholesale_price2;
	}
	public void setWholesale_price2(Float wholesale_price2) {
		this.wholesale_price2 = wholesale_price2;
	}
	public Float getWholesale_price3() {
		return wholesale_price3;
	}
	public void setWholesale_price3(Float wholesale_price3) {
		this.wholesale_price3 = wholesale_price3;
	}
	public Float getWholesale_price4() {
		return wholesale_price4;
	}
	public void setWholesale_price4(Float wholesale_price4) {
		this.wholesale_price4 = wholesale_price4;
	}
	public Float getWholesale_price5() {
		return wholesale_price5;
	}
	public void setWholesale_price5(Float wholesale_price5) {
		this.wholesale_price5 = wholesale_price5;
	}
	public Float getWholesale_price6() {
		return wholesale_price6;
	}
	public void setWholesale_price6(Float wholesale_price6) {
		this.wholesale_price6 = wholesale_price6;
	}
	public Float getWholesale_price7() {
		return wholesale_price7;
	}
	public void setWholesale_price7(Float wholesale_price7) {
		this.wholesale_price7 = wholesale_price7;
	}
	public Float getWholesale_price8() {
		return wholesale_price8;
	}
	public void setWholesale_price8(Float wholesale_price8) {
		this.wholesale_price8 = wholesale_price8;
	}
	public Float getMember_price2() {
		return member_price2;
	}
	public void setMember_price2(Float member_price2) {
		this.member_price2 = member_price2;
	}
	public Float getMember_price3() {
		return member_price3;
	}
	public void setMember_price3(Float member_price3) {
		this.member_price3 = member_price3;
	}
	public Float getMember_price4() {
		return member_price4;
	}
	public void setMember_price4(Float member_price4) {
		this.member_price4 = member_price4;
	}
	public Float getMember_price5() {
		return member_price5;
	}
	public void setMember_price5(Float member_price5) {
		this.member_price5 = member_price5;
	}
	public String getManagement_inventory() {
		return management_inventory;
	}
	public void setManagement_inventory(String management_inventory) {
		this.management_inventory = management_inventory;
	}
	public String getGoods_type() {
		return goods_type;
	}
	public void setGoods_type(String goods_type) {
		this.goods_type = goods_type;
	}
	public String getProcurement_status() {
		return procurement_status;
	}
	public void setProcurement_status(String procurement_status) {
		this.procurement_status = procurement_status;
	}
	public String getValuation_method() {
		return valuation_method;
	}
	public void setValuation_method(String valuation_method) {
		this.valuation_method = valuation_method;
	}
	public Float getValidity_period() {
		return validity_period;
	}
	public void setValidity_period(Float validity_period) {
		this.validity_period = validity_period;
	}
	public Float getEarly_warning_days() {
		return early_warning_days;
	}
	public void setEarly_warning_days(Float early_warning_days) {
		this.early_warning_days = early_warning_days;
	}
	public Float getEarly_warning_days2() {
		return early_warning_days2;
	}
	public void setEarly_warning_days2(Float early_warning_days2) {
		this.early_warning_days2 = early_warning_days2;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
