package com.proem.exm.entity.order;

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
import com.proem.exm.entity.basic.goodsFile.GoodsFile;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "zc_customer_service")
public class CustomerService extends Root{

	private static final long serialVersionUID = 1L;
	/**
	 * 商品ID
	 */
	private GoodsFile goodsFile;
	/**
	 * 份数
	 */
	private String nums;
	/**
	 * 数量
	 */
	private String numbers;
	/**
	 * 处理信息来源
	 */
	private String come;
	/**
	 * 拒收金额
	 */
	private String refuse_amount;
	
	
	@Column(name = "refuse_amount")
	public String getRefuse_amount() {
		return refuse_amount;
	}

	public void setRefuse_amount(String refuse_amount) {
		this.refuse_amount = refuse_amount;
	}

	@Column(name = "come")
	public String getCome() {
		return come;
	}

	public void setCome(String come) {
		this.come = come;
	}

	@Column(name = "nums")
	public String getNums() {
		return nums;
	}
	
	public void setNums(String nums) {
		this.nums = nums;
	}
	
	@Column(name = "numbers")
	public String getNumbers() {
		return numbers;
	}
	
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "goodsFile_id")
	public GoodsFile getGoodsFile() {
		return goodsFile;
	}


	public void setGoodsFile(GoodsFile goodsFile) {
		this.goodsFile = goodsFile;
	}

}
