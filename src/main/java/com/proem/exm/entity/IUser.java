package com.proem.exm.entity;

import com.cisdi.ctp.model.gen.User;

public class IUser extends User{

	private static final long serialVersionUID = 1L;
	private String email;
	private String position;
	private String phoneNo;
	private String roleName;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
