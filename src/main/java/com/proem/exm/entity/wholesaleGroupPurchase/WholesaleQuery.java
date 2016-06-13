package com.proem.exm.entity.wholesaleGroupPurchase;

import com.proem.exm.entity.basic.CommodityClassify.CommodityClassify;
import com.proem.exm.entity.basic.branch.Branch;
import com.proem.exm.entity.basic.branch.BranchTotal;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.basic.provider.ProviderInfo;
import com.proem.exm.entity.system.ZcZoning;
import com.proem.exm.entity.utils.AreaNanJing;
import com.proem.exm.entity.wholesaleGroupPurchase.customer.CustomerInfo;

/**
 * 团购查询实体类
 * 
 * @author Administrator
 * 
 */
public class WholesaleQuery {

	/**
	 * 类别
	 */
	private CommodityClassify classify;
	/**
	 * 品牌
	 */
	private CommodityClassify brand;
	/**
	 * 仓库
	 */
	private Branch branch;
	/**
	 * 分店
	 */
	private BranchTotal branchTotal;
	/**
	 * 供应商
	 */
	private ProviderInfo providerInfo;
	/**
	 * 商品
	 */
	private GoodsFile goodsFile;
	/**
	 * 货号
	 */
	private String serialNumber;
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 品名
	 */
	private String goodsName;
	/**
	 * 分点区域
	 */
	private AreaNanJing branchArea;
	/**
	 * 客户信息
	 */
	private CustomerInfo customerInfo;

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public AreaNanJing getBranchArea() {
		return branchArea;
	}

	public void setBranchArea(AreaNanJing branchArea) {
		this.branchArea = branchArea;
	}

	public CommodityClassify getClassify() {
		return classify;
	}

	public void setClassify(CommodityClassify classify) {
		this.classify = classify;
	}

	public CommodityClassify getBrand() {
		return brand;
	}

	public void setBrand(CommodityClassify brand) {
		this.brand = brand;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public BranchTotal getBranchTotal() {
		return branchTotal;
	}

	public void setBranchTotal(BranchTotal branchTotal) {
		this.branchTotal = branchTotal;
	}

	public ProviderInfo getProviderInfo() {
		return providerInfo;
	}

	public void setProviderInfo(ProviderInfo providerInfo) {
		this.providerInfo = providerInfo;
	}

	public GoodsFile getGoodsFile() {
		return goodsFile;
	}

	public void setGoodsFile(GoodsFile goodsFile) {
		this.goodsFile = goodsFile;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
}
