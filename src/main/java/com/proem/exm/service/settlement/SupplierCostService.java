package com.proem.exm.service.settlement;

import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 供应商费用实现接口
 * @author songcj
 * 2015年11月30日 上午11:17:15 
 */
public interface SupplierCostService extends BaseService{

	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception;

	public DataGrid getItemDataGridObj(Page page,Object obj) throws Exception;

	public DataGrid getDetail(Page page, String id,Object obj) throws Exception;

}
