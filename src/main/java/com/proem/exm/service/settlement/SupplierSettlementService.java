package com.proem.exm.service.settlement;

import com.proem.exm.entity.settlement.SupplierSettlement;
import com.proem.exm.entity.settlement.SupplierSettlementItem;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 供应商结算实现接口
 * @author songcj
 * 2015年12月1日 下午6:22:13 
 */
public interface SupplierSettlementService extends BaseService{

	public DataGrid getPagedDataGridObj(Page page,Object obj) throws Exception;

	public DataGrid getItemDataGridObj(Page page,Object obj) throws Exception;

	public DataGrid getEdit(Page page, String id,Object obj) throws Exception;

	public DataGrid getDetail(Page page, String id,Object obj) throws Exception;

	DataGrid getSettlementObj(Page page, Object obj) throws Exception;


}
