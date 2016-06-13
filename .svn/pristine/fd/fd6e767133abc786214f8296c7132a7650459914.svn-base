package com.proem.exm.entity.warehouse;

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
import com.proem.exm.entity.basic.branch.BranchTotal;
import com.proem.exm.entity.system.CtpUser;

/**
 * 分拣主表实体类
 * @author songcj
 * 2015年12月12日 上午10:35:01
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Zc_sorte")
public class Sorte extends Root{

	private static final long serialVersionUID = 1L;
	/**
	 * 分拣单号
	 */
	private String code;
	/**	
	 * 分拣方式
	 */
	private String sortingMethod;
	/**
	 * 制单人
	 */
	private CtpUser makeMen;
	/**
	 * 制单时间
	 */
	private Date makeTime;
	/**
	 * 审核人
	 */
	private CtpUser auditMen;
	/**
	 * 审核时间
	 */
	private Date auditTime;
	/**
	 * 审核状态
	 */
	private int auditStatus;
	/**
	 * 备注
	 */
	private String remrks;
	
	
	@Column(name = "remrks")
	public String getRemrks() {
		return remrks;
	}
	public void setRemrks(String remrks) {
		this.remrks = remrks;
	}
	@Column(name = "audits_tatus")
	public int getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
	@Column(name = "code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name = "sorting_method")
	public String getSortingMethod() {
		return sortingMethod;
	}
	public void setSortingMethod(String sortingMethod) {
		this.sortingMethod = sortingMethod;
	}
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name = "make_men")
	public CtpUser getMakeMen() {
		return makeMen;
	}
	public void setMakeMen(CtpUser makeMen) {
		this.makeMen = makeMen;
	}
	@Column(name = "make_time")
	public Date getMakeTime() {
		return makeTime;
	}
	public void setMakeTime(Date makeTime) {
		this.makeTime = makeTime;
	}
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name = "audit_men")
	public CtpUser getAuditMen() {
		return auditMen;
	}
	public void setAuditMen(CtpUser auditMen) {
		this.auditMen = auditMen;
	}
	@Column(name = "audit_time")
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
}
