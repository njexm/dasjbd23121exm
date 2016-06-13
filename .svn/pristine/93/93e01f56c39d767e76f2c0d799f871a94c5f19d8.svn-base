package com.proem.exm.entity;

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
@Table(name = "TEST_CUSTOMER")
public class Customer extends Root{

	private static final long serialVersionUID = 6211940289349438235L;

	private String nickName;
	private String account;
	private String mobilePhone;
	private Set<Orders> orders;
	
	@Column(name="NICK_NAME")
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Column(name="ACCOUNT")
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	@Column(name="MOBILE_PHONE")
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	@OneToMany(mappedBy = "customer")
	public Set<Orders> getOrders() {
		return orders;
	}
	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}
}
