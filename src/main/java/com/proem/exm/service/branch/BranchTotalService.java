package com.proem.exm.service.branch;

import java.util.List;
import java.util.Map;

import com.proem.exm.entity.basic.CommodityClassify.CommodityClassify;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

public interface BranchTotalService extends BaseService{
	/**
	 * 修改已经存在的数据  - 单纯单表操作
	 * @param Orders
	 */
	public void updateObj(Object obj);
	
	/**
	 * 分页查询数据
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception;
	
	public List<Map<String, Object>> getObjList1(Object obj);

	/**
	 * 通过ID查询数据,可以关联多张表
	 * @param id
	 * @return
	 */
	public Map<String, Object> getObjById(Object obj);
	
	/**
	 * 无分页条件查询所有
	 * @param obj
	 * @return
	 */
	public List<Map<String, Object>> getObjList(Object obj);

	/**
	 * 获得**区中的小区
	 */
	public List<Map<String, Object>> getCountryList(String id);

}
