package com.proem.exm.service.system.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisdi.ctp.auth.po.User2Role;
import com.cisdi.ctp.model.gen.Root;
import com.proem.exm.dao.roleManage.RoleManageDao;
import com.proem.exm.dao.system.CtpUserDao;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.system.CtpUserService;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.SharePager;
import com.proem.exm.utils.StringUtil;

@Service
public class CtpUserServiceImpl extends BaseServiceImpl implements CtpUserService{

	@Resource
	private CtpUserDao ctpUserDao;
	
	@Resource
	private RoleManageDao roleManageDao;
	@Transactional
	public boolean saveOrUpdate(CtpUser ctpUser) {
		String id = ctpUser.getId();
		if(StringUtil.isEmpty(id)){
			ctpUser.setId(String.valueOf(UUID.randomUUID()).replace("-", ""));
			ctpUser.setCreateTime(new Date());
			ctpUser.setUpdateTime(new Date());
			int i = ctpUserDao.addUser(ctpUser);
			if(i == 1){
				return true;
			}
		}else{
			ctpUser.setUpdateTime(new Date());
			int i = ctpUserDao.updateUser(ctpUser);
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
			ctpUserDao.deleteUsers(str);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public DataGrid getUserDataGrid(CtpUser ctpUser, SharePager sharePager) {
		// TODO Auto-generated method stub
		return null;
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
	public List<Root> getUserRoles(String userId) {
		String condition = " leftId = '"+userId+"'";
		List<Root> userRoles = baseDataMng.getObjListByCondition(Constant.DEFAULT_DATABASE_ID, User2Role.class.getName(), condition);
		return userRoles;
	}

	@Override
	public void updateUserRole(String userId, String roleId) throws Exception {
		String uids[] = userId.split(",");
		String rids[];
		if (roleId == "") {
			rids = new String[0];
		} else {
			rids = roleId.split(",");
		}
		for (String uid : uids) {
			String condition =
					" leftClassId='User' AND rightClassId='Role' AND LeftId='"
							+ uid.trim() + "'";
			List< Root > userRoles =
					baseDataMng.getObjListByCondition(Constant.DEFAULT_DATABASE_ID, User2Role.class.getName(),
							condition);
			for (Root root : userRoles) {
				baseDataMng.deleteObj(Constant.DEFAULT_DATABASE_ID, root.getId(), User2Role.class.getName());
			}
			for (String rid : rids) {
				if (( rid != null && !"".equals(rid.trim()) )
						&& !Constant.ROOTID.equals(rid.trim())) {
					User2Role user2Role = new User2Role();
					user2Role.setLeftClassId("User");
					user2Role.setLeftId(uid);
					user2Role.setRightClassId("Role");
					user2Role.setRightId(rid);
					Date nowDate = new Date();
					user2Role.setCreateTime(nowDate);
					user2Role.setUpdateTime(nowDate);
					baseDataMng.saveObj(Constant.DEFAULT_DATABASE_ID, user2Role);
				}
			}
		}
	}

}
