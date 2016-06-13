package com.proem.exm.service.order;

import java.util.List;
import java.util.Map;

import com.proem.exm.entity.order.ZcOrderItem;
import com.proem.exm.entity.order.ZcOrderProcessItem;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

public interface OrdersItemService extends BaseService {

	
	/**
	 * 分页查询数据
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception;
	
	public DataGrid getPagedDataGridObj1(Page page, Object obj) throws Exception;
	
	/**
	 * 根据条件查询订单详情列表
	 * @param condition
	 * @return
	 */
	public List<Map<String, Object>> getOrderItemListBycon(Object obj) throws Exception;
	
	public DataGrid getOrderProcessDataGridObj(Page page, Object obj) throws Exception;

	public DataGrid getServiceDataGridObj(Page page, Object obj) throws Exception;

	public DataGrid getRefundDataGrid(Page page, Object obj) throws Exception;
	
}
