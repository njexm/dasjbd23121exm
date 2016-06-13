package com.proem.exm.service.system;

import java.util.List;
import java.util.Map;

import com.cisdi.ctp.auth.po.Role;
import com.proem.exm.entity.system.CtpRole;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.SharePager;

public interface CtpRoleService extends BaseService{
	
	public DataGrid getRoleDataGrid(CtpRole ctpRole,SharePager sharePager);
	
	public CtpRole getCtpRoleById(String id);

	public boolean saveOrUpdate(CtpRole ctpRole);
	
	public boolean deleteRole(String ids);
	
	public String getCtpRoleNameById(String id);

	public DataGrid getPagedDataGridObj(Page page, CtpRole ctpRole) throws Exception;

	public Object getRoleInfoList();

	public void saveOrUpdateAuthRole(String roleId, String moduleIds);

	public List<Map<String, Object>> getGrantModuleId(String roleId);

	public List<CtpRole> getRoleList();
	
	
}
