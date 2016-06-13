package com.proem.exm.entity.warehouse.process;

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
import com.proem.exm.entity.system.CtpUser;

/**
 * 加工单商品表
 * 
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_PROCESSGOODS_ITEMS")
public class ProcessGoodsItems extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主表ID
	 */
	private String processGoodsId;
	/**
	 * 商品对象
	 */
	private GoodsFile goodsFile;
	/**
	 * 品名
	 */
	private String goodsName;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 货号
	 */
	private String serialNumber;
	/**
	 * 规格
	 */
	private String specifications;
	/**
	 * 原料使用量
	 */
	private String useNum;
	/**
	 * 成品数量
	 */
	private String goodsNum;
	/**
	 * 成品重量
	 */
	private String goodsWeight;
	/**
	 * 进出库标记
	 */
	private String typeFlag;
	/**
	 * 登录用户判断
	 */
	private CtpUser ctpUser;
	/**
	 * 成品扫码删除标记
	 */
	private String delFlag;

	@Column(name = "GOODSWEIGHT")
	public String getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(String goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	@Column(name = "DELFLAG")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CTPUSER_ID")
	public CtpUser getCtpUser() {
		return ctpUser;
	}

	public void setCtpUser(CtpUser ctpUser) {
		this.ctpUser = ctpUser;
	}

	@Column(name = "PROCESSGOODSID")
	public String getProcessGoodsId() {
		return processGoodsId;
	}

	public void setProcessGoodsId(String processGoodsId) {
		this.processGoodsId = processGoodsId;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODSFILE_ID")
	public GoodsFile getGoodsFile() {
		return goodsFile;
	}

	public void setGoodsFile(GoodsFile goodsFile) {
		this.goodsFile = goodsFile;
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

	@Column(name = "SERIALNUMBER")
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Column(name = "SPECIFICATIONS")
	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	@Column(name = "USENUM")
	public String getUseNum() {
		return useNum;
	}

	public void setUseNum(String useNum) {
		this.useNum = useNum;
	}

	@Column(name = "GOODSNUM")
	public String getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	@Column(name = "TYPEFLAG")
	public String getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(String typeFlag) {
		this.typeFlag = typeFlag;
	}

}
