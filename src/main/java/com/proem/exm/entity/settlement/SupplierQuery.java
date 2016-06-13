package com.proem.exm.entity.settlement;

import com.proem.exm.entity.basic.provider.ProviderInfo;

/**
 * 供应商往来账款实体类
 * @author myseft
 *
 */

public class SupplierQuery {
	
	/**
	 * 供应商
	 */
	private ProviderInfo providerInfo;
	
	/**
	 * 单号
	 */
	private String code ;
	
	/**
	 * 金额
	 */
	private String money;
	
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	
	public ProviderInfo getProviderInfo() {
		return providerInfo;
	}
	public void setProviderInfo(ProviderInfo providerInfo) {
		this.providerInfo = providerInfo;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
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
}
