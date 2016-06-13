package com.proem.exm.service.purchase;

import java.util.List;
import java.util.Map;

import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 采购订单业务逻辑接口
 * 
 * @author Administrator
 * 
 */
public interface PurchaseOrderService extends BaseService {
	/**
	 * 修改已经存在的数据 - 单纯单表操作
	 * 
	 * @param Orders
	 */
	public void updateObj(Object obj);

	/**
	 * 分页查询数据
	 * 
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObj(Page page, Object obj, String state)
			throws Exception;

	/**
	 * 通过ID查询数据,可以关联多张表
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> getObjById(Object obj);

	/**
	 * 无分页条件查询所有
	 * 
	 * @param obj
	 * @return
	 */
	public List<Map<String, Object>> getObjList(Object obj);

	List<Map<String, Object>> getObjList1(Object obj);

	DataGrid getSearchGoodsDataGridObj(Page page, Object obj) throws Exception;

	public DataGrid getHandleDataGrid(Page page, Object obj) throws Exception;

	DataGrid getProviderGoodsObj(Page page, Object obj, String providerId)
			throws Exception;

	List<Map<String, Object>> getObjList3(Object obj);

	List<Map<String, Object>> getObjPinYinList(Object obj);
}
