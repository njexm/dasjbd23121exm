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
import com.proem.exm.entity.basic.CommodityClassify.CommodityClassify;
import com.proem.exm.entity.basic.branch.Branch;
import com.proem.exm.entity.system.CtpUser;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_CHECKNUMBER")
public class ZcCheckNumber extends Root{

	private static final long serialVersionUID = -7593818398089799059L;
	/**
	 * 盘点号
	 */
	private String check_number;
	/**
	 * 盘点仓库编号
	 */
	private  Branch  branch;
	/**
	 * 盘点范围
	 */
	private String checkType;
	/**
	 * 盘点时间
	 */
	private Date check_time;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 审核标志 0未审核 1审核通过
	 */
	private int deFlag;
	/**
	 * 审核状态
	 */
	private int status;
	/**
	 * 操作人员
	 */
	private CtpUser User;
	/**
	 * 商品品牌类别
	 */
	private String goods_classify;
	
	@Column(name = "check_number")
	public String getCheck_number() {
		return check_number;
	}
	public void setCheck_number(String check_number) {
		this.check_number = check_number;
	}
	
	@Column(name = "check_time")
	public Date getCheck_time() {
		return check_time;
	}
	public void setCheck_time(Date check_time) {
		this.check_time = check_time;
	}
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "deFlag")
	public int getDeFlag() {
		return deFlag;
	}
	public void setDeFlag(int deFlag) {
		this.deFlag = deFlag;
	}
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public CtpUser getUser() {
		return User;
	}
	public void setUser(CtpUser user) {
		User = user;
	}
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.EAGER)
	@JoinColumn(name = "branch_id")
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	@Column(name = "checkType")
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	
	@Column(name = "goods_classify")
	public String getGoods_classify() {
		return goods_classify;
	}
	public void setGoods_classify(String goods_classify) {
		this.goods_classify = goods_classify;
	}
	
	@Column(name = "status")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
	
	

}
