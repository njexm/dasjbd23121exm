package com.proem.exm.dao.system.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cisdi.ctp.datamng.impl.BaseDataMngImpl;
import com.proem.exm.dao.system.CtpUserDao;
import com.proem.exm.dao.system.ZcUserInfoDao;
import com.proem.exm.dao.system.ZcZoningDao;
import com.proem.exm.entity.system.CtpRole;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.system.ZcZoning;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.SharePager;

@Repository
public class ZcUserInfoDaoImpl extends BaseDataMngImpl implements ZcUserInfoDao{
@Autowired
private CtpUserDao ctpUserDao;
@Autowired
private ZcZoningDao zcZoningDao;


	@Override
	public int addUserInfo(ZcUserInfo zcuserInfo) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "insert into zc_user_info(id,createtime,updatetime,email,mobilePhone,zipCode,sexType,role_id,user_id,zoning_id,userName) values(?,?,?,?,?,?,?,?,?,?,?)";
		SQLQuery query = session.createSQLQuery(sql);
		query.setString(0, zcuserInfo.getId());
		query.setDate(1, new Date());
		query.setDate(2, new Date());
		query.setString(3, zcuserInfo.getEmail());
		query.setString(4, zcuserInfo.getMobilePhone());
		query.setString(5, zcuserInfo.getZipCode());
		query.setString(6, zcuserInfo.getSexType());
		if( zcuserInfo.getCtpRole() ==null){
			zcuserInfo.setCtpRole(new CtpRole());
		}
		if(zcuserInfo.getCtpUser() ==null){
			zcuserInfo.setCtpUser(new CtpUser());
		}
		if(zcuserInfo.getZcZoning() ==null){
			zcuserInfo.setZcZoning(new ZcZoning());
		}
		query.setString(7, zcuserInfo.getCtpRole().getId()==null?"":zcuserInfo.getCtpRole().getId());
		query.setString(8, zcuserInfo.getCtpUser().getId()==null?"":zcuserInfo.getCtpUser().getId());
		query.setString(9, zcuserInfo.getZcZoning().getId()==null?"":zcuserInfo.getZcZoning().getId());
		query.setString(10, zcuserInfo.getUserName());
		return query.executeUpdate();
	}

	@Override
	public int updateUserInfo(ZcUserInfo zcuserInfo) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "update zc_user_info set email = ?,mobilePhone = ?,zipCode = ?,sexType = ? ,role_id =?,updatetime = ?where id = ?";
		SQLQuery query = session.createSQLQuery(sql);
		query.setString(0, zcuserInfo.getEmail());
		query.setString(1, zcuserInfo.getMobilePhone());
		query.setString(2, zcuserInfo.getZipCode());
		query.setString(3, zcuserInfo.getSexType());
		query.setString(4, zcuserInfo.getCtpRole().getId());
		query.setDate(5, zcuserInfo.getUpdateTime());
		query.setString(6, zcuserInfo.getId());
		return query.executeUpdate();
	}

	@Override
	public void deleteUserInfos(String idstr) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "delete from zc_user_info where id in ("+idstr+")";
		SQLQuery query = session.createSQLQuery(sql);
		query.executeUpdate();
		
	}

	@Override
	public List<ZcZoning> getUserInfoList(ZcUserInfo zcuserInfo,
			SharePager sharePager) {
		return null;
	}

	@Override
	public long getTotalCount(ZcUserInfo zcuserInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ZcUserInfo getUserInfoById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserInfoNameById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getZUserInfoListByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getObjPagedList(Page page)
			throws Exception {
		List<Map<String, Object>> list = this.querySql(Page.getPagedSQL(page.getSql(), page.getPage(), page.getRows()), null);
		return list;
	}

	@Override
	public Long getObjListCount(Page page) throws Exception {
		Long count = 0L;
		List<Map<String, Object>> list = null;
		list = this.querySql(Page.getListCountSQL(page.getSql()), null);
		Map<String, Object> map = (Map<String, Object>) list.get(0);
		BigDecimal bd = (BigDecimal) map.get("COUNT");
		count = bd.longValue();
		return count;
	}

	

	


	
	
}
