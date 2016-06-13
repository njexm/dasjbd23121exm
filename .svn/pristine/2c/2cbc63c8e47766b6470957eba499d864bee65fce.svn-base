package com.proem.exm.service.wholesaleGroupPurchase.customer;

import java.util.List;
import java.util.Map;

import com.proem.exm.entity.wholesaleGroupPurchase.customer.CustomerInfo;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 客户档案
 * @author ZuoYM 
 * @com proem
 */
public interface CustomerInfoService extends BaseService {
	
	/**
	 * 分页查询数据
	 * 
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception;

	/**
	 * 无分页条件查询所有
	 * 
	 * @param obj
	 * @return
	 */
	public List<Map<String, Object>> getObjList(Object obj);
	
	/**
	 * 获取客户信息列表
	 * @return
	 */
	public List<CustomerInfo> getAllObject();

	List<Map<String, Object>> getlistJson(Object obj);

	public Map<String, Object> getAllByExcel(String path);

}
