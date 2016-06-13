package com.proem.exm.service.impl.roleManage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisdi.ctp.auth.po.AuthRule;
import com.cisdi.ctp.auth.po.Role;
import com.proem.exm.dao.roleManage.RoleManageDao;
import com.proem.exm.dao.userManage.UserManageDao;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.roleManage.RoleManageService;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.SharePager;
import com.proem.exm.utils.StringUtil;

@Service
public class RoleManageServiceImpl extends BaseServiceImpl implements RoleManageService{
	
	@Resource
	private RoleManageDao roleManageDao;
	@Resource
	private UserManageDao userManageDao;

	public DataGrid getRoleDataGrid(Role role, SharePager sharePager) {
		List<Role> list = null;
		long totalCount = 0l;
		//根据角色创建者名称模糊查询到一批用户id
		List<String> userIdList = null;
		if(StringUtil.isNotEmpty(role.getCreater())){
			userIdList = userManageDao.getIUserIdListByName(role.getCreater());
			String userIdStr = "";
			if(userIdList == null || userIdList.size() == 0){
				userIdStr = "'"+Constant.ROLEID_ROOT+"'";
			}else{
				for(String userId : userIdList){
					userIdStr+=",'"+userId+"'";
				}
				userIdStr = userIdStr.substring(1);
			}
			list = roleManageDao.getRoleList(role, sharePager, userIdStr);
			totalCount = roleManageDao.getTotalCount(role, userIdStr);
		}else{
			list = roleManageDao.getRoleList(role, sharePager, null);
			totalCount = roleManageDao.getTotalCount(role, null);
		}
		DataGrid dataGrid = new DataGrid(totalCount, list);
		return dataGrid;
	}

	public Role getRoleById(String id) {
		return roleManageDao.getRoleById(id);
	}

	@Transactional
	public boolean saveOrUpdate(Role role) {
		try {
			String roleId = role.getId();
			if(StringUtil.isEmpty(roleId)){
				roleId = UUID.randomUUID().toString();
				role.setId(roleId);
				role.setCreateTime(new Date());
				role.setUpdateTime(new Date());
				roleManageDao.saveOrUpdate(role);
			}else{
				Role oldRole = roleManageDao.getRoleById(roleId);
				oldRole.setCreater(role.getCreater());
				oldRole.setName(role.getName());
				oldRole.setDescription(role.getDescription());
				oldRole.setUpdateTime(new Date());
				roleManageDao.saveOrUpdate(oldRole);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Transactional
	public boolean deleteRoles(String ids) {
		String[] idArr = ids.split(",");
		String str = "";
		for(int i = 0;i<idArr.length;i++){
			if(StringUtil.isNotEmpty(idArr[i])){
				str+=",'"+idArr[i]+"'";
			}
		}
		if(StringUtil.isNotEmpty(str)){
			str = str.substring(1);
		}
		try {
			roleManageDao.deleteRoles(str);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public DataGrid getExsitRoleDataGridByUserId(String userId,
			SharePager sharePager) {
		List<String> roleIdList = roleManageDao.getRoleIdListByUserId(userId);
		if(roleIdList == null || roleIdList.size() == 0){
			roleIdList.add(Constant.ROLEID_ROOT);
		}
		List<Role> roleList = roleManageDao.getExsitRoleListByUserId(roleIdList, sharePager);
		long totalCount = roleManageDao.getTotalCount(roleIdList, sharePager);
		DataGrid dataGrid = new DataGrid(totalCount,roleList);
		return dataGrid;
	}

	@Transactional
	public boolean saveTheCheckedMenu(String roleId, String menuJsonStr) {
		JSONArray menuJsonArray = JSONArray.fromObject(menuJsonStr);
		List<String> menuIdList = new ArrayList<String>();
		for(int i = 0;i<menuJsonArray.size();i++){
			JSONObject jsonObject = menuJsonArray.getJSONObject(i);
			if(jsonObject != null && jsonObject.getString("id") != null){
				 String menuId = jsonObject.getString("id").toString();
				 menuIdList.add(menuId);
			}
		}
		deleteMenuByRoleId(roleId);
		AuthRule authRule = null;
		for(String menuId : menuIdList){
			try {
				authRule = new AuthRule();
				authRule.setId(UUID.randomUUID().toString());
				authRule.setRoleid(roleId);
				authRule.setResourceid(menuId);
				authRule.setResourceType("MODULE");
				authRule.setCreateTime(new Date());
				authRule.setUpdateTime(new Date());
				roleManageDao.saveTheCheckedMenu(authRule);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public List<String> getMenuIdListByRoleId(String roleId) {
		return roleManageDao.getMenuIdListByRoleId(roleId);
	}
	
	@Transactional
	public void deleteMenuByRoleId(String roleId){
		roleManageDao.deleteMenuByRoleId(roleId);
	}

}
