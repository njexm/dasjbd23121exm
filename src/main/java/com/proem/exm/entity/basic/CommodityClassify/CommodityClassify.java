package com.proem.exm.entity.basic.CommodityClassify;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;

/**
 * @author DeFei 分店
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "zc_classify_info")
public class CommodityClassify extends Root {

	private static final long serialVersionUID = 7115174968885815406L;

	/**
	 * 类别编号 品牌编号
	 */
	private String classify_code;
	/**
	 * 类别名称 品牌名称
	 */
	private String classify_name;// 分店名称
	/**
	 * 类别等级 品牌等级
	 */
	private String classify_level;
	/**
	 * 类型:1、类别;2、品牌
	 */
	private String classify_type;
	/**
	 * 营业员提成比例
	 */
	private String commission;
	private String delFlag;

	/**
	 * 一级为0
	 */
	private String parentId;
	/**
	 * 树路径
	 */
	private String parentPath;
	/**
	 * 子数目
	 */
	private String childCount;
	/**
	 * 排序
	 */
	private String order_index;
	/**
	 * 商品属性,父级为0
	 */
	private String typeId;

	@Column(name = "CLASSIFY_CODE")
	public String getClassify_code() {
		return classify_code;
	}

	public void setClassify_code(String classify_code) {
		this.classify_code = classify_code;
	}

	@Column(name = "CLASSIFY_NAME")
	public String getClassify_name() {
		return classify_name;
	}

	public void setClassify_name(String classify_name) {
		this.classify_name = classify_name;
	}

	@Column(name = "CLASSIFY_LEVEL")
	public String getClassify_level() {
		return classify_level;
	}

	public void setClassify_level(String classify_level) {
		this.classify_level = classify_level;
	}

	@Column(name = "CLASSIFY_TYPE")
	public String getClassify_type() {
		return classify_type;
	}

	public void setClassify_type(String classify_type) {
		this.classify_type = classify_type;
	}

	@Column(name = "DELFLAG")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	@Column(name = "commission")
	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	@Column(name = "parentId")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "typeId")
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	@Column(name = "parentPath")
	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	@Column(name = "childCount")
	public String getChildCount() {
		return childCount;
	}

	public void setChildCount(String childCount) {
		this.childCount = childCount;
	}

	@Column(name = "order_index")
	public String getOrder_index() {
		return order_index;
	}

	public void setOrder_index(String order_index) {
		this.order_index = order_index;
	}

}
