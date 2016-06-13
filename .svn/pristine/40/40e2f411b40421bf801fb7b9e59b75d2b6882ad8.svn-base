package com.proem.exm.service.dispatching;


import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

public interface DispatchingService extends BaseService {
	
	//获取未处理订单列表
	public DataGrid getPagedDataGridObjDispatching(Page page, Object obj, String minAmount, String maxAmount, String startTime, String endTime) throws Exception;
	
	//根据要货单单号，获得对应的要货单商品，分页显示
	public DataGrid getStoresDataGridObjDispatching(Page page, Object obj, String serialNumber) throws Exception;
}
