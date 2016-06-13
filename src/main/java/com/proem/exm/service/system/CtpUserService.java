package com.proem.exm.service.system;

import java.util.List;

import com.cisdi.ctp.model.gen.Root;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.SharePager;

public interface CtpUserService extends BaseService{
	
	public DataGrid getUserDataGrid(CtpUser ctpUser,SharePager sharePager);
	
	public CtpUser getCtpUserById(String id);

	public boolean saveOrUpdate(CtpUser ctpUser);
	
	public boolean deleteUser(String ids);
	
	public String getCtpUserNameById(String id);

	public List<Root> getUserRoles(String userId);

	public void updateUserRole(String userId, String roleId) throws Exception;
	
	
}
