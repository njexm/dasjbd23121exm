package com.proem.exm.service.purchase;

import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 采购订单购物车业务逻辑接口
 * 
 * @author Administrator
 * 
 */
public interface PurchaseShoppingCartService extends BaseService {
	/**
	 * 分页查询数据
	 * 
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception;
}
