package com.proem.exm.entity.basic.code;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "zc_code")
public class Code extends Root{

	private static final long serialVersionUID = 8252424063806879210L;
	
	/**  
	 * 名称
	 */   
	private String codeName;
	
	/**  
	 * 值       
	 */   
	private String codeValue;
	
	/**  
	 * 类型       
	 */   
	private String codeType;
	
	/**
	 * 上级单位
	 */
	private String parent;
	
	/**
	 * 数据描述
	 */
	private String codeDescription;
	
	/**
	 * 状态默认关闭
	 */
	private String state = "closed";
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name="codeDescription")
	public String getCodeDescription() {
		return codeDescription;
	}

	public void setCodeDescription(String codeDescription) {
		this.codeDescription = codeDescription;
	}

	@Column(name="parent")
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	@Column(name="codeName")
	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	@Column(name="codeValue")
	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	@Column(name="codeType")
	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

}
