package com.proem.exm.entity.basic.provider;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;

/**
 * 邮箱配置信息表
 * 
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_EMAIL_SERVICE")
public class EmailService extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String emailTails;

	private String emailServices;

	@Column(name = "EMAILTAILS")
	public String getEmailTails() {
		return emailTails;
	}

	public void setEmailTails(String emailTails) {
		this.emailTails = emailTails;
	}

	@Column(name = "EMAILSERVICES")
	public String getEmailServices() {
		return emailServices;
	}

	public void setEmailServices(String emailServices) {
		this.emailServices = emailServices;
	}

}
