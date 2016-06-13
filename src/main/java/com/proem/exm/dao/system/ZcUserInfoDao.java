package com.proem.exm.dao.system;

import java.util.List;
import java.util.Map;

import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.system.ZcZoning;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.SharePager;

public interface ZcUserInfoDao {

	/**
	 * 保存用户信息
	 * @param zcuserInfo
	 * @return
	 */
	public int addUserInfo(ZcUserInfo zcuserInfo);
	
	/**
	 * 更新用户
	 * @param zcuserInfo
	 * @return
	 */
	public int updateUserInfo(ZcUserInfo zcuserInfo);
	
	/**
	 * 批量删除地址
	 * @param zcuserInfo
	 */
	public void deleteUserInfos(String idstr);
	
	/**
	 * 条件查询地址
	 * @param zcuserInfo
	 * @param sharePager
	 * @return
	 */
	public List<ZcZoning> getUserInfoList(ZcUserInfo zcuserInfo,SharePager sharePager);
	
	/**
	 * 查询地址总数
	 * @param zcuserInfo
	 * @return
	 */
	public long getTotalCount(ZcUserInfo zcuserInfo);
	
	/**
	 * 根据Id查询地址
	 * @param id
	 * @return
	 */
	public ZcUserInfo getUserInfoById(String id);
	
	/**
	 * 根据Id查询地址
	 * @param id
	 * @return
	 */
	public String getUserInfoNameById(String id);
	
	/**
	 * 根据名称模糊查询地址id
	 * @param name
	 * @return
	 */
	public List<String> getZUserInfoListByName(String name);
	
	public List<Map<String, Object>> getObjPagedList(Page page) throws Exception;

	public Long getObjListCount(Page page) throws Exception;
	
}
