package com.proem.exm.dao.roleManage;

import java.io.Serializable;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.cisdi.ctp.auth.po.AuthRule;
import com.cisdi.ctp.auth.po.Role;
import com.cisdi.ctp.dao.BaseDao;
import com.proem.exm.utils.SharePager;

public interface RoleManageDao extends BaseDao<T, Serializable> {

	/**
	 * 新增或更新角色
	 * @param role
	 */
	public void saveOrUpdate(Role role);

	/**
	 * 批量删除角色
	 * @param idstr
	 */
	public void deleteRoles(String idstr);

	/**
	 * 条件查询角色
	 * @param role
	 * @param sharePager
	 * @return
	 */
	public List<Role> getRoleList(Role role, SharePager sharePager,String userIdStr);

	/**
	 * 条件查询角数量
	 * @param role
	 * @return
	 */
	public long getTotalCount(Role role,String userIdStr);

	/**
	 * 根据ID查询角色
	 * @param id
	 * @return
	 */
	public Role getRoleById(String id);
	
	/**
	 * 得到一个用户上已经分配的角色
	 * @param roleIdList
	 * @param sharePager
	 * @return
	 */
	public List<Role> getExsitRoleListByUserId(List<String> roleIdList,SharePager sharePager);
	
	/**
	 * 查询总数
	 * @param roleIdList
	 * @param sharePager
	 * @return
	 */
	public long getTotalCount(List<String> roleIdList,SharePager sharePager);
	
	/**
	 * 根据用户ID查询关联的角色id集合
	 * @param userId
	 * @return
	 */
	public List<String> getRoleIdListByUserId(String userId);
	
	/**
	 * 一个角色分配的菜单
	 * @param authRule
	 */
	public void saveTheCheckedMenu(AuthRule authRule);
	
	/**
	 * 查询一个角色已经分配的菜单
	 * @param roleId
	 * @return
	 */
	public List<String> getMenuIdListByRoleId(String roleId);
	
	/**
	 * 删除一个角色上已经分配的菜单
	 * @param roleId
	 */
	public void deleteMenuByRoleId(String roleId);
	
}
