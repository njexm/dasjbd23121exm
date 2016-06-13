package com.proem.exm.service.purchase;

import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 采购查询实现接口
 * 
 * @author songcj 2015年12月14日 上午10:42:02
 */
public interface PurchaseQueryService extends BaseService {

	DataGrid getPagedDataGridObj(Page page, Object obj, String startDate,
			String endDate, String provider, String classify,
			String serialNumber, String branchId, String goods_brand_id)
			throws Exception;


	DataGrid getPagedProviderGoodsGridObj(Page page, Object obj,
			String startDate, String endDate, String provider, String classify,
			String branchId) throws Exception;

	DataGrid getPagedDataGridClass(Page page,Object obj)throws Exception;


	DataGrid getPagedReceiptsGridObj(Page page, Object obj) throws Exception;


	DataGrid getHistoryPriceDataGridObj(Page page, Object obj) throws Exception;


	DataGrid getBigClassPage(Page page, Object obj) throws Exception;


	DataGrid getPagedDetailObj(Page page, Object obj, String startDate,
			String endDate, String provider, String classify,
			String serialNumber, String branchId, String goods_brand_id)
			throws Exception;;

}
