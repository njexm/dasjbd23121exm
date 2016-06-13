package com.proem.exm.dao.system.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.cisdi.ctp.dao.impl.BaseDaoImpl;
import com.proem.exm.dao.system.ZcZoningDao;
import com.proem.exm.entity.system.ZcZoning;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.SharePager;

@Repository
public class ZcZoningDaoImpl extends BaseDaoImpl<T, Serializable> implements ZcZoningDao{
	@Override
	public int addZoning(ZcZoning zcZoning) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "insert into zc_zoning(id,createtime,updatetime,province,city,county,street) values(?,?,?,?,?,?,?)";
		SQLQuery query = session.createSQLQuery(sql);
		query.setString(0, zcZoning.getId());
		query.setDate(1, new Date());
		query.setDate(2, new Date());
		query.setString(3, zcZoning.getProvince());
		query.setString(4, zcZoning.getCity());
		query.setString(5, zcZoning.getCounty());
		query.setString(6, zcZoning.getStreet());
		return query.executeUpdate();
	}

	@Override
	public int updateZoning(ZcZoning zcZoning) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "update zc_zoning set province = ?,city = ?,county = ?,street = ? ,updatetime = ?where id = ?";
		SQLQuery query = session.createSQLQuery(sql);
		query.setString(0, zcZoning.getProvince());
		query.setString(1, zcZoning.getCity());
		query.setString(2, zcZoning.getCounty());
		query.setString(3, zcZoning.getStreet());
		query.setDate(4, zcZoning.getUpdateTime());
		query.setString(5, zcZoning.getId());
		return query.executeUpdate();
	}

	@Override
	public void deleteZonings(String idstr) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "delete from zc_zoning where id in ("+idstr+")";
		SQLQuery query = session.createSQLQuery(sql);
		query.executeUpdate();
		
	}

	@Override
	public List<ZcZoning> getZoningList(ZcZoning zcZoning, SharePager sharePager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTotalCount(ZcZoning zcZoning) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ZcZoning getZoningById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getZoningNameById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getZoningListByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
