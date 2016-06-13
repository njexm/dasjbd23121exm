package com.proem.exm.dao.system.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.cisdi.ctp.datamng.impl.BaseDataMngImpl;
import com.proem.exm.dao.system.CtpRoleDao;
import com.proem.exm.entity.system.CtpRole;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.SharePager;

@Repository
public class CtpRoleDaoImpl extends BaseDataMngImpl implements CtpRoleDao{
	@Override
	public int addRole(CtpRole ctpRole) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "insert into ctp_role(id,createtime,updatetime,bpmlocked,creater,description,name,noteditable) values(?,?,?,?,?,?,?,?)";
		SQLQuery query = session.createSQLQuery(sql);
		query.setString(0, String.valueOf(UUID.randomUUID()).replace("-", ""));
		query.setDate(1, ctpRole.getCreateTime());
		query.setDate(2, ctpRole.getUpdateTime());
		query.setString(3, ctpRole.getBpmlocked());
		query.setString(4, ctpRole.getCreater());
		query.setString(5, ctpRole.getDescription());
		query.setString(6, ctpRole.getName());
		query.setInteger(7, ctpRole.getNoteditable());
		return query.executeUpdate();
	}

	@Override
	public int updateRole(CtpRole ctpRole) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "update ctp_role set bpmlocked = ?,creater = ?,description = ?,name = ?, noteditable= ? ,updatetime = ?where id = ?";
		SQLQuery query = session.createSQLQuery(sql);
		query.setString(0, ctpRole.getBpmlocked());
		query.setString(1, ctpRole.getCreater());
		query.setString(2, ctpRole.getDescription());
		query.setString(3, ctpRole.getName());
		query.setInteger(4, ctpRole.getNoteditable());
		query.setDate(5, ctpRole.getUpdateTime());
		query.setString(6, ctpRole.getId());
		return query.executeUpdate();
	}

	@Override
	public void deleteRoles(String idstr) {
		Session session = getSession(Constant.DEFAULT_DATABASE_ID);
		String sql = "delete from ctp_role where id in ("+idstr+")";
		SQLQuery query = session.createSQLQuery(sql);
		query.executeUpdate();
		
	}

	@Override
	public List<CtpRole> getCtpRoleList(CtpRole ctpRole, SharePager sharePager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTotalCount(CtpRole ctpRole) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CtpRole getCtpUserById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCtpRoleNameById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getCtpRoleIdListByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveDistributeRole(String roleId, String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delteRelationById(String roleId, String userId) {
		// TODO Auto-generated method stub
		
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
