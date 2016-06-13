package com.proem.exm.entity.system;

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

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_TimeQuartz")
public class TimeQuartz extends Root{

	private static final long serialVersionUID = 6211940289349438235L;
	@Column(name="orderCome")
	private String orderCome;
	/**
	 * 定时类型
	 */
	@Column(name="pushType")
	private String pushType;
	/**
	 * 频率
	 */
	@Column(name="pushHz")
	private String pushHz;
	/**
	 * 拉取时间
	 */
	@Column(name="pushDate")
	private Date pushDate;
	public String getPushType() {
		return pushType;
	}

	public void setPushType(String pushType) {
		this.pushType = pushType;
	}

	public String getPushHz() {
		return pushHz;
	}

	public void setPushHz(String pushHz) {
		this.pushHz = pushHz;
	}

	public Date getPushDate() {
		return pushDate;
	}

	public void setPushDate(Date pushDate) {
		this.pushDate = pushDate;
	}

	public Date getPushStartDate() {
		return pushStartDate;
	}

	public void setPushStartDate(Date pushStartDate) {
		this.pushStartDate = pushStartDate;
	}

	public Date getPushEndDate() {
		return pushEndDate;
	}

	public void setPushEndDate(Date pushEndDate) {
		this.pushEndDate = pushEndDate;
	}

	/**
	 * 拉取开始时间
	 */
	@Column(name="pushStartDate")
	private Date pushStartDate;
	
	@Column(name="pushEndDate")
	private Date pushEndDate;
	public String getOrderCome() {
		return orderCome;
	}

	public void setOrderCome(String orderCome) {
		this.orderCome = orderCome;
	}
	
	
	
}
