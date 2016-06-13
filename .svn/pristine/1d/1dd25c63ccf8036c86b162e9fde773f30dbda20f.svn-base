package com.proem.exm.service.userManage;

import com.proem.exm.entity.IUser;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.SharePager;

public interface UserManageService {
	
	public DataGrid getUserDataGrid(IUser iuser,SharePager sharePager);
	
	public IUser getIUserById(String id);

	public boolean saveOrUpdate(IUser iuser);
	
	public boolean deleteUser(String ids);
	
	public String getIUserNameById(String id);
	
	public boolean saveDistributeRole(String roleJsonStr,String userId);
	
	/**
	 * 删除用户和角色的关联关系
	 * @param roleId
	 * @param userId
	 */
	public void delteRelationById(String roleId,String userId);
	
}
