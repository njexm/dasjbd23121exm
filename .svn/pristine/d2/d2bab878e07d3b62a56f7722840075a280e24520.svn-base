package com.proem.exm.entity.system;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_LOG")
public class LogManage extends Root{

	private static final long serialVersionUID = 6211940289349438235L;

	
	/**
	 * 操作人
	 */
	private CtpUser user;
	/**
	 * 事件描述
	 */
	private String description;
	/**
	 * 操作模块
	 */
	private String moduleName;
	/**
	 * Ip地址
	 */
	private String ipAddress;
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
	
	@Column(name="moduleName")
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	@Column(name="ipAddress")
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	
	
	
	
	
}
