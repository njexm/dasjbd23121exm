package com.proem.exm.service.order;

import java.util.List;
import java.util.Map;

import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.order.ZcOrder;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

public interface OrdersService extends BaseService {

	/**
	 * 分页查询数据
	 * 
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObjTodayOrders(Page page, Object obj) throws Exception;

	/**
	 * 分页查询今日订单
	 * 
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception;
	
	public DataGrid getPagedDataGridObjTodayTotal(Page page, Object obj,String startTime, String endTime) throws Exception;

	public DataGrid getPagedDataGridObjTotal(Page page, Object obj) throws Exception;

	public DataGrid getHandleDataGrid(Page page, Object obj) throws Exception;

	public List<Map<String, Object>> getTreeData();

	public DataGrid getPagedDataGridByHandle(Page page, ZcOrder zcOrder,
			String goodfileId) throws Exception;

	public DataGrid getHandleDataGrid1(Page page, String id) throws Exception;

	public DataGrid getServiceDataGrid(Page page, Object obj) throws Exception;

	public DataGrid getRefundServiceDataGrid(Page page, Object obj) throws Exception;

	DataGrid getPagedDataGridByTodayOrders(Page page, ZcOrder zcOrder,
			String goodfileId) throws Exception;
}
