package com.proem.exm.dao.impl.roleManage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cisdi.ctp.auth.po.AuthRule;
import com.cisdi.ctp.auth.po.Role;
import com.cisdi.ctp.dao.impl.BaseDaoImpl;
import com.proem.exm.dao.roleManage.RoleManageDao;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.SharePager;
import com.proem.exm.utils.StringUtil;

@Repository
public class RoleManageDaoImpl extends BaseDaoImpl<T, Serializable> implements RoleManageDao{

	public void saveOrUpdate(Role role) {
		HibernateTemplate hibernateTemplate = getHibernateTemplate(Constant.DEFAULT_DATABASE_ID);
		hibernateTemplate.saveOrUpdate(role);
	}

	public void deleteRoles(String idstr) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "delete from ctp_role where id in ("+idstr+")";
		SQLQuery query = session.createSQLQuery(sql);
		query.executeUpdate();
	}

	public List<Role> getRoleList(Role role, SharePager sharePager, String userIdStr) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "select t.id,t.createtime,t.updatetime,u.name as creater,t.description,t.name from ctp_role t,"
				+ "ctp_user u where t.creater = u.id";
		if(StringUtil.isNotEmpty(userIdStr)){
			sql+=" and u.id in ("+userIdStr+")";
		}
		if(StringUtil.isNotEmpty(role.getName())){
			sql+=" and t.name like '%"+role.getName()+"%'";
		}
		SQLQuery query = session.createSQLQuery(sql);
		query.setFirstResult((sharePager.getPage()-1) * (sharePager.getRows()));
		query.setMaxResults(sharePager.getRows());
		List<Object[]> arrList = query.list();
		List<Role> returnList = new ArrayList<Role>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Role tempRole = null;
		for(int i = 0;i<arrList.size();i++){
			Object[] objArr = arrList.get(i);
			tempRole = new Role();
			if(objArr[0] != null){
				tempRole.setId(objArr[0].toString());
			}
			if(objArr[1] != null){
				try {
					tempRole.setCreateTime(sdf.parse(objArr[1].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(objArr[2] != null){
				try {
					tempRole.setUpdateTime(sdf.parse(objArr[2].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(objArr[3] != null){
				tempRole.setCreater(objArr[3].toString());
			}
			if(objArr[4] != null){
				tempRole.setDescription(objArr[4].toString());
			}
			if(objArr[5] != null){
				tempRole.setName(objArr[5].toString());
			}
			returnList.add(tempRole);
		}
		return returnList;
	}

	public long getTotalCount(Role role, String userIdStr) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "select count(1) from ctp_role t,"
				+ "ctp_user u where t.creater = u.id";
		if(StringUtil.isNotEmpty(userIdStr)){
			sql+=" and u.id in ("+userIdStr+")";
		}
		if(StringUtil.isNotEmpty(role.getName())){
			sql+=" and t.name like '%"+role.getName()+"%'";
		}
		SQLQuery query = session.createSQLQuery(sql);
		long i = ((BigDecimal) query.uniqueResult()).longValue();
		return i;
	}

	public Role getRoleById(String id) {
		HibernateTemplate hibernateTemplate = getHibernateTemplate(Constant.DEFAULT_DATABASE_ID);
		return hibernateTemplate.load(Role.class, id);
	}

	public List<Role> getExsitRoleListByUserId(List<String> roleIdList,
			SharePager sharePager) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		Criteria criteria = session.createCriteria(Role.class);
		if(roleIdList != null && roleIdList.size() > 0){
			criteria.add(Restrictions.in("id", roleIdList));
		}
		criteria.setFirstResult((sharePager.getPage()-1) * (sharePager.getRows()));
		criteria.setMaxResults(sharePager.getRows());
		return criteria.list();
	}

	public long getTotalCount(List<String> roleIdList, SharePager sharePager) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		Criteria criteria = session.createCriteria(Role.class);
		if(roleIdList != null && roleIdList.size() > 0){
			criteria.add(Restrictions.in("id", roleIdList));
		}
		return criteria.list().size();
	}
	
	public List<String> getRoleIdListByUserId(String userId) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "select leftId from CTP_REL_USER_ROLE where rightId=:rightId";
		SQLQuery query = session.createSQLQuery(sql);
		query.setString("rightId", userId);
		return query.list();
	}

	public void saveTheCheckedMenu(AuthRule authRule) {
		HibernateTemplate hibernateTemplate = getHibernateTemplate(Constant.DEFAULT_DATABASE_ID);
		hibernateTemplate.saveOrUpdate(authRule);
	}

	public List<String> getMenuIdListByRoleId(String roleId) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "select resourceId from CTP_AUTH_RULE where roleId=:roleId and resourceType = :resourceType";
		SQLQuery query = session.createSQLQuery(sql);
		query.setString("roleId", roleId);
		query.setString("resourceType", Constant.MODULE_TYPE);
		return query.list();
	}

	public void deleteMenuByRoleId(String roleId) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "delete from CTP_AUTH_RULE where roleId=:roleId and resourceType = :resourceType";
		SQLQuery query = session.createSQLQuery(sql);
		query.setString("roleId", roleId);
		query.setString("resourceType", Constant.MODULE_TYPE);
		query.executeUpdate();
	}


}
