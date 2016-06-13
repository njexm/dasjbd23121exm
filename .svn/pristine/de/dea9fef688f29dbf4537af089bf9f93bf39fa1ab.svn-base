package com.proem.exm.service.roleManage;

import java.util.List;

import com.cisdi.ctp.auth.po.Role;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.SharePager;

public interface RoleManageService {

	public DataGrid getRoleDataGrid(Role role, SharePager sharePager);

	public Role getRoleById(String id);

	public boolean saveOrUpdate(Role role);

	public boolean deleteRoles(String ids);
	
	public DataGrid getExsitRoleDataGridByUserId(String userId, SharePager sharePager);
	
	public boolean saveTheCheckedMenu(String roleId,String menuJsonStr);
	
	public List<String> getMenuIdListByRoleId(String roleId);
}
