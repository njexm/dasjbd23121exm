package com.proem.exm.service.wholesaleGroupPurchase.order;

import com.proem.exm.entity.wholesaleGroupPurchase.WholesaleGroupPurchaseOrderItem;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

public interface WholesaleGroupPurchaseOrderItemService extends BaseService{

	DataGrid getPagedDataGridObj(Page page) throws Exception;

	DataGrid getPagedDataGridObj(Object obj,
			Page page) throws Exception;

}
