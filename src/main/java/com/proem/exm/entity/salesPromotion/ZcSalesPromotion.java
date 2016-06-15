package com.proem.exm.entity.salesPromotion;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;
import com.proem.exm.entity.basic.branch.BranchTotal;
import com.proem.exm.entity.basic.code.Code;

/**
 * 促销主表
 * @author myseft
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Zc_SalesPromotion")
public class ZcSalesPromotion extends Root{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*促销编号*/
	private String promotionNumber ;
	/*促销标题*/
	private String promotionTitle ;  
	/*促销说明*/
	private String promotionRemark ;
	/*促销开始时间*/
	private Date promotionBeginDate ;
	/*促销结束时间*/
	private Date promotionEndDate ;
	/*促销中止时间*/
	private Date stopDate  ;
	/*促销中止人*/
	private String stopMan  ;
	/*促销时段（周一,周二...）*/
	private String promotionDays ;
	/*制单人*/
	private String createMan;
	/*审核人*/
	private String checkMan;
	/*审核时间*/
	private Date checkDate;
	/*会员等级*/
	private String memberLevel ;
	/*分店*/
	private List<BranchTotal> branchTotalList ;
	/*促销范围*/
	private Code ZcCodeScope ;
	/*促销模式*/
	private Code ZcCodeMode ;
	
	@Column(name = "PROMOTION_NUMBER")
	public String getPromotionNumber() {
		return promotionNumber;
	}
	public void setPromotionNumber(String promotionNumber) {
		this.promotionNumber = promotionNumber;
	}
	@Column(name = "PROMOTION_TITLE")
	public String getPromotionTitle() {
		return promotionTitle;
	}
	public void setPromotionTitle(String promotionTitle) {
		this.promotionTitle = promotionTitle;
	}
	@Column(name = "PROMOTION_REMARK")
	public String getPromotionRemark() {
		return promotionRemark;
	}
	public void setPromotionRemark(String promotionRemark) {
		this.promotionRemark = promotionRemark;
	}
	@Column(name = "PROMOTION_BEGIN_DATE")
	public Date getPromotionBeginDate() {
		return promotionBeginDate;
	}
	public void setPromotionBeginDate(Date promotionBeginDate) {
		this.promotionBeginDate = promotionBeginDate;
	}
	@Column(name = "PROMOTION_END_DATE")
	public Date getPromotionEndDate() {
		return promotionEndDate;
	}
	public void setPromotionEndDate(Date promotionEndDate) {
		this.promotionEndDate = promotionEndDate;
	}
	@Column(name = "STOP_DATE")
	public Date getStopDate() {
		return stopDate;
	}
	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
	@Column(name = "STOP_MAN")
	public String getStopMan() {
		return stopMan;
	}
	public void setStopMan(String stopMan) {
		this.stopMan = stopMan;
	}
	@Column(name = "PROMOTION_DAYS")
	public String getPromotionDays() {
		return promotionDays;
	}
	public void setPromotionDays(String promotionDays) {
		this.promotionDays = promotionDays;
	}
	@Column(name = "CREATE_MAN")
	public String getCreateMan() {
		return createMan;
	}
	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}
	@Column(name = "CHECK_MAN")
	public String getCheckMan() {
		return checkMan;
	}
	public void setCheckMan(String checkMan) {
		this.checkMan = checkMan;
	}
	@Column(name = "CHECK_DATE")
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	@Column(name = "MEMBER_LEVEL")
	public String getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "zc_promotion_rel", 
	joinColumns={@JoinColumn(table = "Zc_SalesPromotion", name= "promotion_id", referencedColumnName = "id")},
	inverseJoinColumns={@JoinColumn(table = "zc_branch_total", name= "branch_id", referencedColumnName= "id")})
	public List<BranchTotal> getBranchTotalList() {
		return branchTotalList;
	}
	public void setBranchTotalList(List<BranchTotal> branchTotalList) {
		this.branchTotalList = branchTotalList;
	}
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ZCCODE_SCOPEID")
	public Code getZcCodeScope() {
		return ZcCodeScope;
	}
	public void setZcCodeScope(Code zcCodeScope) {
		ZcCodeScope = zcCodeScope;
	}
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ZCCODE_MODEID")
	public Code getZcCodeMode() {
		return ZcCodeMode;
	}
	public void setZcCodeMode(Code zcCodeMode) {
		ZcCodeMode = zcCodeMode;
	}
	
	
}
