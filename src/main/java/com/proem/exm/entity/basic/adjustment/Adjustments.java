package com.proem.exm.entity.basic.adjustment;

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
import com.proem.exm.entity.system.CtpUser;

/**
 * 商品调价单
 * @author ZuoYM
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "zc_adjustments")
public class Adjustments extends Root {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5799217609553512694L;
	/**
	 * 调价单单号
	 */
	private String adjustment_id;
	/**
	 * 制单人
	 */
	private CtpUser maker;
	/**
	 * 审核人
	 */
	private String auditor;
	/**
	 *  删除标记
	 */
	private String delflag;
	/**
	 *  审核状态
	 */
	private int audflag;
	/**
	 *  建表时间
	 */
	private String createtime_adj;
	/**
	 * 生效日期 
	 */
	private String effecttime;

	@Column(name = "delflag")
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	@Column(name = "createtime_adj")
	public String getCreatetime_adj() {
		return createtime_adj;
	}
	public void setCreatetime_adj(String createtime_adj) {
		this.createtime_adj = createtime_adj;
	}

	@Column(name = "adjustment_id")
	public String getAdjustment_id() {
		return adjustment_id;
	}
	public void setAdjustment_id(String adjustment_id) {
		this.adjustment_id = adjustment_id;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "CTP_USER_ID")
	public CtpUser getMaker() {
		return maker;
	}
	public void setMaker(CtpUser maker) {
		this.maker = maker;
	}

	@Column(name = "auditor")
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	@Column(name = "audflag")
	public int getAudflag() {
		return audflag;
	}
	public void setAudflag(int audflag) {
		this.audflag = audflag;
	}

	@Column(name = "effecttime")
	public String getEffecttime() {
		return effecttime;
	}
	public void setEffecttime(String effecttime) {
		this.effecttime = effecttime;
	}
}
