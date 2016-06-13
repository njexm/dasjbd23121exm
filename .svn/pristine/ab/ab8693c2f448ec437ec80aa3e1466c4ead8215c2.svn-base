package com.proem.exm.entity.basic.associator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;

/**
 * 工位属性表
 * 
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_WORKSTATION")
public class WorkStation extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 工位编号
	 */
	private String workCode;
	/**
	 * 工位名
	 */
	private String workName;
	/**
	 * 工位IP
	 */
	private String workIp;
	/**
	 * 工位描述
	 */
	private String description;
	
	@Column(name = "WORKIP")
	public String getWorkIp() {
		return workIp;
	}

	public void setWorkIp(String workIp) {
		this.workIp = workIp;
	}

	@Column(name = "WORKCODE")
	public String getWorkCode() {
		return workCode;
	}

	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}

	@Column(name = "WORKNAME")
	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
