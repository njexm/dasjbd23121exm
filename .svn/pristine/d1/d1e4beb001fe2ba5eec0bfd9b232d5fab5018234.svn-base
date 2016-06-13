package com.proem.exm.entity.basic.adjustment;

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
 * 商品调价单
 * @author ZuoYM
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "zc_adjustment_info")
public class AdjustmentDetail extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8824735121045337137L;
	/**
	 * 主表
	 */
	private Adjustments adjustments;
	/**
	 *  货号
	 */
	private String serialNumber;
	/**
	 * 商品名
	 */
	private String goods_name;
	/**
	 *  商品单位
	 */
	private String goods_unit;
	/**
	 *  商品规格
	 */
	private String goods_specifications;
	/**
	 *  最低售价
	 */
	private String lowest_price;
	/**
	 *  现最低售价
	 */
	private String lowest_price_2;
	/**
	 * 原进价
	 */
	private String goods_purchase_price;
	/**
	 * 现进价
	 */
	private String goods_purchase_price_2;
	/**
	 * 原批发价
	 */
	private String wholesale_price;
	/**
	 * 现批发价
	 */
	private String wholesale_price_2;
	/**
	 *  原批发价2
	 */
	private String wholesale_price2;
	/**
	 *  现批发价2
	 */
	private String wholesale_price2_2;
	/**
	 *  原批发价3
	 */
	private String wholesale_price3;
	/**
	 *  现批发价3
	 */
	private String wholesale_price3_2;
	/**
	 *  原批发价4
	 */
	private String wholesale_price4;
	/**
	 *  现批发价4
	 */
	private String wholesale_price4_2;
	/**
	 *  原批发价5
	 */
	private String wholesale_price5;
	/**
	 *  现批发价5
	 */
	private String wholesale_price5_2;
	/**
	 * 原批发价6
	 */
	private String wholesale_price6;
	/**
	 * 现批发价6
	 */
	private String wholesale_price6_2;
	/**
	 * 原批发价7
	 */
	private String wholesale_price7;
	/**
	 * 现批发价7
	 */
	private String wholesale_price7_2;
	/**
	 * 原批发价8
	 */
	private String wholesale_price8;
	/**
	 * 现批发价8
	 */
	private String wholesale_price8_2;
	/**
	 * 原零售价
	 */
	private String goods_price;
	/**
	 * 现零售价
	 */
	private String goods_price_2;
	/**
	 * 原配送价
	 */
	private String distribution_price;
	/**
	 * 现配送价
	 */
	private String distribution_price_2;
	/**
	 * 原会员价1
	 */
	private String member_price;
	/**
	 * 现会员价1
	 */
	private String member_price_2;
	/**
	 *  原会员价2
	 */
	private String member_price2;
	/**
	 *  现会员价2
	 */
	private String member_price2_2;
	/**
	 *  原会员价3
	 */
	private String member_price3;
	/**
	 *  现会员价3
	 */
	private String member_price3_2;
	/**
	 *  原会员价4
	 */
	private String member_price4;
	/**
	 *  现会员价4
	 */
	private String member_price4_2;
	/**
	 *  原会员价5
	 */
	private String member_price5;
	/**
	 *  现会员价5
	 */
	private String member_price5_2;
	/**
	 *  备注
	 */
	private String remark;
	


	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "zc_adjustments_id")
	public Adjustments getAdjustments() {
		return adjustments;
	}
	public void setAdjustments(Adjustments adjustments) {
		this.adjustments = adjustments;
	}
	
	@Column(name = "serialNumber")
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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
	
	@Column(name = "goods_specifications")
	public String getGoods_specifications() {
		return goods_specifications;
	}
	public void setGoods_specifications(String goods_specifications) {
		this.goods_specifications = goods_specifications;
	}
	
	@Column(name = "lowest_price")
	public String getLowest_price() {
		return lowest_price;
	}
	public void setLowest_price(String lowest_price) {
		this.lowest_price = lowest_price;
	}
	
	@Column(name = "lowest_price_2")
	public String getLowest_price_2() {
		return lowest_price_2;
	}
	public void setLowest_price_2(String lowest_price_2) {
		this.lowest_price_2 = lowest_price_2;
	}
	
	@Column(name = "goods_purchase_price")
	public String getGoods_purchase_price() {
		return goods_purchase_price;
	}
	public void setGoods_purchase_price(String goods_purchase_price) {
		this.goods_purchase_price = goods_purchase_price;
	}
	
	@Column(name = "goods_purchase_price_2")
	public String getGoods_purchase_price_2() {
		return goods_purchase_price_2;
	}
	public void setGoods_purchase_price_2(String goods_purchase_price_2) {
		this.goods_purchase_price_2 = goods_purchase_price_2;
	}
	
	@Column(name = "wholesale_price")
	public String getWholesale_price() {
		return wholesale_price;
	}
	public void setWholesale_price(String wholesale_price) {
		this.wholesale_price = wholesale_price;
	}
	
	@Column(name = "wholesale_price_2")
	public String getWholesale_price_2() {
		return wholesale_price_2;
	}
	public void setWholesale_price_2(String wholesale_price_2) {
		this.wholesale_price_2 = wholesale_price_2;
	}
	
	@Column(name = "wholesale_price2")
	public String getWholesale_price2() {
		return wholesale_price2;
	}
	public void setWholesale_price2(String wholesale_price2) {
		this.wholesale_price2 = wholesale_price2;
	}
	
	@Column(name = "wholesale_price2_2")
	public String getWholesale_price2_2() {
		return wholesale_price2_2;
	}
	public void setWholesale_price2_2(String wholesale_price2_2) {
		this.wholesale_price2_2 = wholesale_price2_2;
	}
	
	@Column(name = "wholesale_price3")
	public String getWholesale_price3() {
		return wholesale_price3;
	}
	public void setWholesale_price3(String wholesale_price3) {
		this.wholesale_price3 = wholesale_price3;
	}
	
	@Column(name = "wholesale_price3_2")
	public String getWholesale_price3_2() {
		return wholesale_price3_2;
	}
	public void setWholesale_price3_2(String wholesale_price3_2) {
		this.wholesale_price3_2 = wholesale_price3_2;
	}
	
	@Column(name = "wholesale_price4")
	public String getWholesale_price4() {
		return wholesale_price4;
	}
	public void setWholesale_price4(String wholesale_price4) {
		this.wholesale_price4 = wholesale_price4;
	}
	
	@Column(name = "wholesale_price4_2")
	public String getWholesale_price4_2() {
		return wholesale_price4_2;
	}
	public void setWholesale_price4_2(String wholesale_price4_2) {
		this.wholesale_price4_2 = wholesale_price4_2;
	}
	
	@Column(name = "wholesale_price5")
	public String getWholesale_price5() {
		return wholesale_price5;
	}
	public void setWholesale_price5(String wholesale_price5) {
		this.wholesale_price5 = wholesale_price5;
	}
	
	@Column(name = "wholesale_price5_2")
	public String getWholesale_price5_2() {
		return wholesale_price5_2;
	}
	public void setWholesale_price5_2(String wholesale_price5_2) {
		this.wholesale_price5_2 = wholesale_price5_2;
	}
	
	@Column(name = "wholesale_price6")
	public String getWholesale_price6() {
		return wholesale_price6;
	}
	public void setWholesale_price6(String wholesale_price6) {
		this.wholesale_price6 = wholesale_price6;
	}
	
	@Column(name = "wholesale_price6_2")
	public String getWholesale_price6_2() {
		return wholesale_price6_2;
	}
	public void setWholesale_price6_2(String wholesale_price6_2) {
		this.wholesale_price6_2 = wholesale_price6_2;
	}
	
	@Column(name = "wholesale_price7")
	public String getWholesale_price7() {
		return wholesale_price7;
	}
	public void setWholesale_price7(String wholesale_price7) {
		this.wholesale_price7 = wholesale_price7;
	}
	
	@Column(name = "wholesale_price7_2")
	public String getWholesale_price7_2() {
		return wholesale_price7_2;
	}
	public void setWholesale_price7_2(String wholesale_price7_2) {
		this.wholesale_price7_2 = wholesale_price7_2;
	}
	
	@Column(name = "wholesale_price8")
	public String getWholesale_price8() {
		return wholesale_price8;
	}
	public void setWholesale_price8(String wholesale_price8) {
		this.wholesale_price8 = wholesale_price8;
	}
	
	@Column(name = "wholesale_price8_2")
	public String getWholesale_price8_2() {
		return wholesale_price8_2;
	}
	public void setWholesale_price8_2(String wholesale_price8_2) {
		this.wholesale_price8_2 = wholesale_price8_2;
	}
	
	@Column(name = "goods_price")
	public String getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}
	
	@Column(name = "goods_price_2")
	public String getGoods_price_2() {
		return goods_price_2;
	}
	public void setGoods_price_2(String goods_price_2) {
		this.goods_price_2 = goods_price_2;
	}
	
	@Column(name = "distribution_price")
	public String getDistribution_price() {
		return distribution_price;
	}
	public void setDistribution_price(String distribution_price) {
		this.distribution_price = distribution_price;
	}
	
	@Column(name = "distribution_price_2")
	public String getDistribution_price_2() {
		return distribution_price_2;
	}
	public void setDistribution_price_2(String distribution_price_2) {
		this.distribution_price_2 = distribution_price_2;
	}
	
	@Column(name = "member_price")
	public String getMember_price() {
		return member_price;
	}
	public void setMember_price(String member_price) {
		this.member_price = member_price;
	}
	
	@Column(name = "member_price_2")
	public String getMember_price_2() {
		return member_price_2;
	}
	public void setMember_price_2(String member_price_2) {
		this.member_price_2 = member_price_2;
	}
	
	@Column(name = "member_price2")
	public String getMember_price2() {
		return member_price2;
	}
	public void setMember_price2(String member_price2) {
		this.member_price2 = member_price2;
	}

	@Column(name = "member_price3")
	public String getMember_price3() {
		return member_price3;
	}
	public void setMember_price3(String member_price3) {
		
		this.member_price3 = member_price3;
	}
	
	@Column(name = "member_price4")
	public String getMember_price4() {
		return member_price4;
	}
	public void setMember_price4(String member_price4) {
		this.member_price4 = member_price4;
	}

	@Column(name = "member_price5")
	public String getMember_price5() {
		return member_price5;
	}
	public void setMember_price5(String member_price5) {
		this.member_price5 = member_price5;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "member_price2_2")
	public String getMember_price2_2() {
		return member_price2_2;
	}
	public void setMember_price2_2(String member_price2_2) {
		this.member_price2_2 = member_price2_2;
	}

	@Column(name = "member_price3_2")
	public String getMember_price3_2() {
		return member_price3_2;
	}
	public void setMember_price3_2(String member_price3_2) {
		this.member_price3_2 = member_price3_2;
	}

	@Column(name = "member_price4_2")
	public String getMember_price4_2() {
		return member_price4_2;
	}
	public void setMember_price4_2(String member_price4_2) {
		this.member_price4_2 = member_price4_2;
	}

	@Column(name = "member_price5_2")
	public String getMember_price5_2() {
		return member_price5_2;
	}
	public void setMember_price5_2(String member_price5_2) {
		this.member_price5_2 = member_price5_2;
	}
	
}
