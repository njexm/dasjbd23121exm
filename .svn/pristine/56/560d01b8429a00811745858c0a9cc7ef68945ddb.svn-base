package com.proem.exm.dao.system.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.cisdi.ctp.dao.impl.BaseDaoImpl;
import com.proem.exm.dao.system.CtpUserDao;
import com.proem.exm.dao.userManage.UserManageDao;
import com.proem.exm.entity.IUser;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.SharePager;
import com.proem.exm.utils.StringUtil;

@Repository
public class CtpUserDaoImpl extends BaseDaoImpl<T, Serializable> implements CtpUserDao{


	public List<IUser> getIUserList(IUser iuser, SharePager sharePager) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "select u.id,u.createTime,u.updateTime,u.gender,u.name,u.password,u.email,u.position,u.phoneno,wm_concat(r.name) "
				+ "as roleName from ctp_user u left join ctp_rel_user_role rel on u.id = rel.rightid left join "
				+ "ctp_role r on rel.leftid = r.id where 1 = 1";
		if(StringUtil.isNotEmpty(iuser.getName())){
			sql+=" and name like '%"+iuser.getName()+"%'";
		}
		if(StringUtil.isNotEmpty(iuser.getGender())){
			sql+=" and gender = '"+iuser.getGender()+"'";
		}
		sql+=" group by u.id,u.createTime,u.updateTime,u.gender,u.name,u.password,u.email,u.position,u.phoneno order by u.updatetime desc";
		SQLQuery query = session.createSQLQuery(sql);
		query.setFirstResult((sharePager.getPage()-1) * (sharePager.getRows()));
		query.setMaxResults(sharePager.getRows());
		List<Object[]> arrayList = query.list();
		List<IUser> returnList = new ArrayList<IUser>();
		IUser user = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(arrayList != null && arrayList.size() > 0){
			for(int i=0;i<arrayList.size();i++){
				user = new IUser();
				Object[] obj = arrayList.get(i);
				if(obj[0] != null){
					user.setId(obj[0].toString());
				}
				if(obj[1] != null){
					try {
						user.setCreateTime(sdf.parse(obj[1].toString()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if(obj[2] != null){
					try {
						user.setUpdateTime(sdf.parse(obj[2].toString()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if(obj[3] != null){
					user.setGender(obj[3].toString());
				}
				if(obj[4] != null){
					user.setName(obj[4].toString());
				}
				if(obj[5] != null){
					user.setPassword(obj[5].toString());
				}
				if(obj[6] != null){
					user.setEmail(obj[6].toString());
				}
				if(obj[7] != null){
					user.setPosition(obj[7].toString());
				}
				if(obj[8] != null){
					user.setPhoneNo(obj[8].toString());
				}
				if(obj[9] != null){
					user.setRoleName(obj[9].toString());
				}
				returnList.add(user);
			}
		}
		return returnList;
	}

	public long getTotalCount(IUser iuser) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "select count(1) from ctp_user where 1 = 1";
		if(StringUtil.isNotEmpty(iuser.getName())){
			sql+=" and name like '%"+iuser.getName()+"%'";
		}
		if(StringUtil.isNotEmpty(iuser.getGender())){
			sql+=" and gender = '"+iuser.getGender()+"'";
		}
		SQLQuery query = session.createSQLQuery(sql);
		long i = ((BigDecimal) query.uniqueResult()).longValue();
		return i;
	}

	public IUser getIUserById(String id) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "select * from ctp_user where id = :id";
		SQLQuery query = session.createSQLQuery(sql);
		query.setString("id", id);
		List<Object[]> list = query.list();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		IUser user = new IUser();
		if(list.size() == 1){
			Object[] obj = list.get(0);
			if(obj[0] != null){
				user.setId(obj[0].toString());
			}
			if(obj[1] != null){
				try {
					user.setCreateTime(sdf.parse(obj[1].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(obj[2] != null){
				try {
					user.setUpdateTime(sdf.parse(obj[2].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(obj[3] != null){
				user.setGender(obj[3].toString());
			}
			if(obj[4] != null){
				user.setName(obj[4].toString());
			}
			if(obj[5] != null){
				user.setPassword(obj[5].toString());
			}
			if(obj[6] != null){
				user.setEmail(obj[6].toString());
			}
			if(obj[7] != null){
				user.setPosition(obj[7].toString());
			}
			if(obj[8] != null){
				user.setPhoneNo(obj[8].toString());
			}
		}
		return user;
	}

	public void deleteUsers(String idstr) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "delete from ctp_user where id in ("+idstr+")";
		SQLQuery query = session.createSQLQuery(sql);
		query.executeUpdate();
	}

	public List<String> getIUserIdListByName(String name) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "select id from ctp_user where name like '%"+name+"%'";
		SQLQuery query = session.createSQLQuery(sql);
		List<String> idList = query.list();
		return idList;
	}

	public String getIUserNameById(String id) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "select name from ctp_user where id = '"+id+"'";
		SQLQuery query = session.createSQLQuery(sql);
		List<String> list = query.list();
		if(list != null && list.size() > 0){
			return list.get(0);
		}else{
			return "";
		}
	}

	public void saveDistributeRole(String roleId, String userId) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "insert into ctp_rel_user_role(id,createtime,updatetime,leftclassid,leftid,rightclassid,rightid) values(?,?,?,?,?,?,?)";
		SQLQuery query = session.createSQLQuery(sql);
		query.setString(0, UUID.randomUUID().toString());
		Date date = new Date();
		query.setDate(1, date);
		query.setDate(2, date);
		query.setString(3, "CTP_ROLE");
		query.setString(4, roleId);
		query.setString(5, "CTP_USER");
		query.setString(6, userId);
		query.executeUpdate();
	}

	public void delteRelationById(String roleId, String userId) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "delete from ctp_rel_user_role where leftid=:roleId and rightid=:userId";
		SQLQuery query = session.createSQLQuery(sql);
		query.setString("roleId", roleId);
		query.setString("userId", userId);
		query.executeUpdate();
	}

	@Override
	public int addUser(CtpUser ctpUser) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "insert into ctp_user(id,createtime,updatetime,gender,name,password) values(?,?,?,?,?,?)";
		SQLQuery query = session.createSQLQuery(sql);
		query.setString(0, String.valueOf(UUID.randomUUID()).replace("-", ""));
		query.setDate(1, new Date());
		query.setDate(2, new Date());
		query.setString(3, ctpUser.getGender());
		query.setString(4, ctpUser.getName());
		query.setString(5, ctpUser.getPassword());
		return query.executeUpdate();
	}

	@Override
	public int updateUser(CtpUser ctpUser) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "update ctp_user set gender = ?,name = ?,password = ?,updatetime = ? where id = ?";
		SQLQuery query = session.createSQLQuery(sql);
		query.setString(0, ctpUser.getGender());
		query.setString(1, ctpUser.getName());
		query.setString(2, ctpUser.getPassword());
		query.setDate(3, ctpUser.getUpdateTime());
		query.setString(4, ctpUser.getId());
		return query.executeUpdate();
	}

	@Override
	public List<CtpUser> getCtpUserList(CtpUser ctpUser, SharePager sharePager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTotalCount(CtpUser ctpUser) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CtpUser getCtpUserById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCtpUserNameById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getCtpUserIdListByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CtpUser getCtpUserByName(String name) {
		CtpUser ctpUser=new CtpUser();
		try {
			Session session = getSession(Constant.DEFAULT_DATABASE_ID);
			String sql = "select * from ctp_user where name = '"+name+"'";
			SQLQuery query = session.createSQLQuery(sql);
			ctpUser=(CtpUser) query.uniqueResult();
			return ctpUser;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
