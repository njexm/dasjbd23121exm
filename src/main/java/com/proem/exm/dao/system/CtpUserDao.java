package com.proem.exm.dao.system;

import java.io.Serializable;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.cisdi.ctp.dao.BaseDao;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.utils.SharePager;

public interface CtpUserDao extends BaseDao<T, Serializable>{

	/**
	 * 保存用户
	 * @param ctpUser
	 * @return
	 */
	public int addUser(CtpUser ctpUser);
	
	/**
	 * 更新用户
	 * @param ctpUser
	 * @return
	 */
	public int updateUser(CtpUser ctpUser);
	
	/**
	 * 批量删除用户
	 * @param ctpUser
	 */
	public void deleteUsers(String idstr);
	
	/**
	 * 条件查询用户
	 * @param ctpUser
	 * @param sharePager
	 * @return
	 */
	public List<CtpUser> getCtpUserList(CtpUser ctpUser,SharePager sharePager);
	
	/**
	 * 查询用户总数
	 * @param ctpUser
	 * @return
	 */
	public long getTotalCount(CtpUser ctpUser);
	
	/**
	 * 根据Id查询用户
	 * @param id
	 * @return
	 */
	public CtpUser getCtpUserById(String id);
	
	/**
	 * 根据Id查询用户名
	 * @param id
	 * @return
	 */
	public String getCtpUserNameById(String id);
	
	/**
	 * 根据用户名称模糊查询用户id
	 * @param name
	 * @return
	 */
	public List<String> getCtpUserIdListByName(String name);
	
	
	/**
	 * 根据用户名称模糊查询用户id
	 * @param name
	 * @return
	 */
	public CtpUser getCtpUserByName(String name);
	
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
