package com.proem.exm.entity.utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.cisdi.ctp.model.gen.Root;



@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ZC_AREA")
public class Area extends Root{
	private static final long serialVersionUID = 7115174968885815406L;

	private String areaName;//区域名称
	private String parentId;//父Id
	private String shortName;//简称
	private String levelType;//等级
	private String cityCode;//电话区号
	private String zipCode;//邮编
	private String quanCheng;//全称
	private String lng;//经度
	private String lat;//纬度
	private String pinyin;//拼音
	
	@Column(name="AREANAME")
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	@Column(name="PARENTID")
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Column(name="SHORTNAME")
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	@Column(name="LEVELTYPE")
	public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	
	
	@Column(name="CITYCODE")
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	@Column(name="ZIPCODE")
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Column(name="QUANCHENG")
	public String getQuanCheng() {
		return quanCheng;
	}
	
	
	public void setQuanCheng(String quanCheng) {
		this.quanCheng = quanCheng;
	}
	
	@Column(name="LNG")
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	
	@Column(name="LAT")
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	@Column(name="PINYIN")
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	
	
	
}
