package com.proem.exm.dao.impl.menuManage;

import java.io.Serializable;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cisdi.ctp.dao.impl.BaseDaoImpl;
import com.proem.exm.dao.menuManage.MenuManageDao;
import com.proem.exm.utils.Constant;

@Repository
public class MenuManageDaoImpl extends BaseDaoImpl<T, Serializable> implements MenuManageDao{

	public void saveOrUpdate(){
		HibernateTemplate hibernateTemplate = getHibernateTemplate(Constant.DEFAULT_DATABASE_ID);
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "insert into ctp_module(id) values(?)";
		SQLQuery query = session.createSQLQuery(sql);
		query.setString(0, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		query.executeUpdate();
	}
}
