package com.proem.exm.service.system.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cisdi.ctp.auth.po.AuthRule;
import com.cisdi.ctp.utils.common.StringUtils;
import com.proem.exm.dao.system.CtpRoleDao;
import com.proem.exm.entity.system.CtpRole;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.system.CtpRoleService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.SharePager;
import com.proem.exm.utils.StringUtil;

@Service
public class CtpRoleServiceImpl extends BaseServiceImpl implements
		CtpRoleService {

	@Resource
	private CtpRoleDao ctpRoleDao;

	@Override
	public DataGrid getRoleDataGrid(CtpRole ctpRole, SharePager sharePager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CtpRole getCtpRoleById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveOrUpdate(CtpRole ctpRole) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteRole(String ids) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getCtpRoleNameById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataGrid getPagedDataGridObj(Page page, CtpRole ctpRole)
			throws Exception {
		String sql = "select * from ctp_role where 1=1";
		sql += joinCondition(ctpRole);
		page.setSql(sql);
		List<Map<String, Object>> rows = ctpRoleDao.getObjPagedList(page);
		Long total = ctpRoleDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	private String joinCondition(CtpRole ctpRole) {
		String condition = "";
		if (StringUtil.validate(ctpRole.getName())) {
			condition += " and name like '%" + ctpRole.getName() + "%'";
		}
		return condition;
	}

	@Override
	public Object getRoleInfoList() {
		String sql = "select * from ctp_role";
		Page page = new Page();
		page.setPage(1);
		page.setRows(100000);
		page.setSql(sql);
		try {
			List<Map<String, Object>> rows = ctpRoleDao.getObjPagedList(page);
			return rows;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveOrUpdateAuthRole(String roleId, String moduleIds) {
		try {
			String sql = "delete from ctp_auth_rule where roleid = '" + roleId
					+ "' and resourcetype = 'MODULE'";
			baseDataMng.querySqlUD(sql, null);

			String[] moduleId = moduleIds.split(",");
			for (int i = 0; i < moduleId.length; i++) {
				if (!StringUtils.isBlank(moduleId[i])) {
					AuthRule ar = new AuthRule();
					ar.setRoleid(roleId);
					ar.setResourceid(moduleId[i]);
					ar.setResourceType("MODULE");
					baseDataMng.saveObj(ar);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Map<String, Object>> getGrantModuleId(String roleId) {
		List<Map<String, Object>> list = null;
		String sql = "select ar.resourceid as moduleid,m.parentid from ctp_auth_rule ar"
				+ " left join ctp_module m on m.id = ar.resourceid"
				+ " where ar.roleid = '"
				+ roleId
				+ "' and ar.resourcetype = 'MODULE'";
		try {
			list = baseDataMng.querySqlToLowerCase(sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<CtpRole> getRoleList() {
		return baseDataMng.getAllObj(CtpRole.class);
	}

}
