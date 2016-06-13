package com.proem.exm.dao.userManage;

import java.io.Serializable;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.cisdi.ctp.dao.BaseDao;
import com.proem.exm.entity.IUser;
import com.proem.exm.utils.SharePager;

public interface UserManageDao extends BaseDao<T, Serializable>{

	/**
	 * 保存用户
	 * @param iuser
	 * @return
	 */
	public int addUser(IUser iuser);
	
	/**
	 * 更新用户
	 * @param iuser
	 * @return
	 */
	public int updateUser(IUser iuser);
	
	/**
	 * 批量删除用户
	 * @param idstr
	 */
	public void deleteUsers(String idstr);
	
	/**
	 * 条件查询用户
	 * @param iuser
	 * @param sharePager
	 * @return
	 */
	public List<IUser> getIUserList(IUser iuser,SharePager sharePager);
	
	/**
	 * 查询用户总数
	 * @param iuser
	 * @return
	 */
	public long getTotalCount(IUser iuser);
	
	/**
	 * 根据Id查询用户
	 * @param id
	 * @return
	 */
	public IUser getIUserById(String id);
	
	/**
	 * 根据Id查询用户名
	 * @param id
	 * @return
	 */
	public String getIUserNameById(String id);
	
	/**
	 * 根据用户名称模糊查询用户id
	 * @param name
	 * @return
	 */
	public List<String> getIUserIdListByName(String name);
	
	/**
	 * 给一个用户分配角色，角色id和用户id保存到关系表
	 * @param roleId
	 * @param userId
	 */
	public void saveDistributeRole(String roleId,String userId);
	
	/**
	 * 删除用户和角色的关联关系
	 * @param roleId
	 * @param userId
	 */
	public void delteRelationById(String roleId,String userId);
}
