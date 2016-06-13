package com.proem.exm.dao.system;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import com.cisdi.ctp.dao.BaseDao;
import com.proem.exm.entity.system.CtpRole;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.SharePager;

public interface CtpRoleDao {

	/**
	 * 保存角色
	 * @param ctpRole
	 * @return
	 */
	public int addRole(CtpRole ctpRole);
	
	/**
	 * 更新角色
	 * @param ctpRole
	 * @return
	 */
	public int updateRole(CtpRole ctpRole);
	
	/**
	 * 批量删除角色
	 * @param ctpRole
	 */
	public void deleteRoles(String idstr);
	
	/**
	 * 条件查询角色
	 * @param ctpRole
	 * @param sharePager
	 * @return
	 */
	public List<CtpRole> getCtpRoleList(CtpRole ctpRole,SharePager sharePager);
	
	/**
	 * 查询角色总数
	 * @param ctpRole
	 * @return
	 */
	public long getTotalCount(CtpRole ctpRole);
	
	/**
	 * 根据Id查询角色
	 * @param id
	 * @return
	 */
	public CtpRole getCtpUserById(String id);
	
	/**
	 * 根据Id查询角色名
	 * @param id
	 * @return
	 */
	public String getCtpRoleNameById(String id);
	
	/**
	 * 根据用户名称模糊查询角色id
	 * @param name
	 * @return
	 */
	public List<String> getCtpRoleIdListByName(String name);
	
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

	public List<Map<String, Object>> getObjPagedList(Page page) throws Exception;

	public Long getObjListCount(Page page) throws Exception;
}
