package com.proem.exm.service.impl.userManage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proem.exm.dao.roleManage.RoleManageDao;
import com.proem.exm.dao.userManage.UserManageDao;
import com.proem.exm.entity.IUser;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.userManage.UserManageService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.SharePager;
import com.proem.exm.utils.StringUtil;

@Service
public class UserManageServiceImpl extends BaseServiceImpl implements UserManageService{

	@Resource
	private UserManageDao userManageDao;
	
	@Resource
	private RoleManageDao roleManageDao;
	
	@Transactional
	public boolean saveOrUpdate(IUser iuser) {
		String id = iuser.getId();
		if(StringUtil.isEmpty(id)){
			iuser.setId(UUID.randomUUID().toString());
			iuser.setCreateTime(new Date());
			iuser.setUpdateTime(new Date());
			int i = userManageDao.addUser(iuser);
			if(i == 1){
				return true;
			}
		}else{
			iuser.setUpdateTime(new Date());
			int i = userManageDao.updateUser(iuser);
			if(i == 1){
				return true;
			}
		}
		return false;
	}

	@Transactional
	public boolean deleteUser(String ids) {
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
			userManageDao.deleteUsers(str);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public DataGrid getUserDataGrid(IUser iuser, SharePager sharePager) {
		long totalCount = userManageDao.getTotalCount(iuser);
		List<IUser> list = userManageDao.getIUserList(iuser, sharePager);
		DataGrid dataGrid = new DataGrid(totalCount, list);
		return dataGrid;
	}

	public IUser getIUserById(String id) {
		IUser iuser = userManageDao.getIUserById(id);
		return iuser;
	}
	
	public String getIUserNameById(String id) {
		return userManageDao.getIUserNameById(id);
	}

	@Transactional
	public boolean saveDistributeRole(String roleJsonStr, String userId) {
		JSONArray roleJsonArray = JSONArray.fromObject(roleJsonStr);
		List<String> roleIdList = new ArrayList<String>();
		for(int i = 0;i<roleJsonArray.size();i++){
			JSONObject jsonObject = roleJsonArray.getJSONObject(i);
			if(jsonObject != null && jsonObject.getString("id") != null){
				String roleId = jsonObject.getString("id").toString();
				roleIdList.add(roleId);
			}
		}
		List<String> exsitRoleIdList = roleManageDao.getRoleIdListByUserId(userId);
		//取得页面传过来的roleId集合中与数据库中该用户已经分配的roleid集合做个比较取得交集。
		List<String> sameRoleIdList = new ArrayList<String>();
		if(exsitRoleIdList != null && exsitRoleIdList.size() > 0){
			if(exsitRoleIdList.size() > roleIdList.size()){
				for(String exsitRoleId : exsitRoleIdList){
					for(String roleId : roleIdList){
						if(exsitRoleId.equals(roleId)){
							sameRoleIdList.add(exsitRoleId);
						}
					}
				}
			}else{
				for(String roleId : roleIdList){
					for(String exsitRoleId : exsitRoleIdList){
						if(exsitRoleId.equals(roleId)){
							sameRoleIdList.add(exsitRoleId);
						}
					}
				}
			}
			/**
			 * 已存在的roleId集合与交集比较，去掉相同部分，得到页面上减少的roleId集合，
			 * 即数据库中需要删除的部分,此时exsitRoleIdList中存放的都是即将要删除的数据
			 */
			for(int i = 0;i<sameRoleIdList.size();i++){
				if(exsitRoleIdList.contains(sameRoleIdList.get(i))){
					exsitRoleIdList.remove(sameRoleIdList.get(i));
				}
			}
			
			/**
			 * 页面传过来的roleId集合与交集比较，去掉相同部分，得到页面新增加的roleId集合
			 * 即需要往数据库中保存的部分，此时roleIdList中存放的是需要保存到数据中的数据
			 */
			for(int i = 0;i<sameRoleIdList.size();i++){
				if(roleIdList.contains(sameRoleIdList.get(i))){
					roleIdList.remove(sameRoleIdList.get(i));
				}
			}
			
			
			
		}
		for(String roleId : exsitRoleIdList){
			delteRelationById(roleId, userId);
		}
		
		for(String roleId : roleIdList){
			try {
				userManageDao.saveDistributeRole(roleId, userId);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	@Transactional
	public void delteRelationById(String roleId, String userId) {
		userManageDao.delteRelationById(roleId, userId);
	}

}
