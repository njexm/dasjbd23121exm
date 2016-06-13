package com.proem.exm.service.settlement;

import com.proem.exm.entity.settlement.AdvancePaymentItem;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 预付款Service
 * @author songcj
 * 2015年11月25日 下午2:46:14 
 */
public interface AdvancePaymentService extends BaseService{

	public DataGrid getPagedDataGridObj(Page page, Object object) throws Exception;

	public DataGrid getItemDataGridObj(Page page,Object object) throws Exception;

	public DataGrid getAdvancePaymentDetail(Page page, String id,Object obj) throws Exception;

}
