package com.proem.exm.entity.system;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "CTP_ROLE")
public class CtpRole extends Root{

	private static final long serialVersionUID = 6211940289349438235L;

	private int  noteditable ;
	private String bpmlocked ;
	private String creater ;
	private String description ;
	private String name;
	
	@Column(name="NOTEDITABLE")
	public int getNoteditable() {
		return noteditable;
	}
	public void setNoteditable(int noteditable) {
		this.noteditable = noteditable;
	}
	
	@Column(name="BPMLOCKED")
	public String getBpmlocked() {
		return bpmlocked;
	}
	public void setBpmlocked(String bpmlocked) {
		this.bpmlocked = bpmlocked;
	}
	
	@Column(name="CREATER")
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	
	
	
	
	
}
