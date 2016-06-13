package com.proem.exm.dao.system;

import java.io.Serializable;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.cisdi.ctp.dao.BaseDao;
import com.proem.exm.entity.system.ZcZoning;
import com.proem.exm.utils.SharePager;

public interface ZcZoningDao extends BaseDao<T, Serializable>{

	/**
	 * 保存地址
	 * @param zcZoning
	 * @return
	 */
	public int addZoning(ZcZoning zcZoning);
	
	/**
	 * 更新地址
	 * @param zcZoning
	 * @return
	 */
	public int updateZoning(ZcZoning zcZoning);
	
	/**
	 * 批量删除地址
	 * @param zcZoning
	 */
	public void deleteZonings(String idstr);
	
	/**
	 * 条件查询地址
	 * @param zcZoning
	 * @param sharePager
	 * @return
	 */
	public List<ZcZoning> getZoningList(ZcZoning zcZoning,SharePager sharePager);
	
	/**
	 * 查询地址总数
	 * @param zcZoning
	 * @return
	 */
	public long getTotalCount(ZcZoning zcZoning);
	
	/**
	 * 根据Id查询地址
	 * @param id
	 * @return
	 */
	public ZcZoning getZoningById(String id);
	
	/**
	 * 根据Id查询地址
	 * @param id
	 * @return
	 */
	public String getZoningNameById(String id);
	
	/**
	 * 根据名称模糊查询地址id
	 * @param name
	 * @return
	 */
	public List<String> getZoningListByName(String name);
	
}
