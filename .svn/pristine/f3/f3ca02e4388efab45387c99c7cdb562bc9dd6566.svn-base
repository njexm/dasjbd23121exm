package com.proem.exm.service.purchase;

import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 采购订单商品集合业务逻辑接口
 * 
 * @author Administrator
 * 
 */
public interface PurchaseOrderGoodsItemsService extends BaseService {
	/**
	 * 分页查询数据
	 * 
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception;

	DataGrid getPurchaseAddGoods(Page page, Object obj, CtpUser ctpUser,
			String providerId,String storehouseId) throws Exception;
}
