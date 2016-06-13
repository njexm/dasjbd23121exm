package com.proem.exm.entity.system;

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
@Table(name = "ZC_NOTICE")
public class Notice extends Root{

	private static final long serialVersionUID = 6211940289349438235L;

	
	/**
	 * 操作人
	 */
	private CtpUser user;
	/**
	 * 公告标题
	 */
	private String title;
	/**
	 * 公告描述
	 */
	private String description;
	/**
	 * Ip地址
	 */
	private String ipAddress;
	/**
	 * 有效性标识位
	 */
	private String delflag;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	public CtpUser getUser() {
		return user;
	}
	public void setUser(CtpUser user) {
		this.user = user;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="ipAddress")
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="delflag")
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	
	
	
	
	
	
}
