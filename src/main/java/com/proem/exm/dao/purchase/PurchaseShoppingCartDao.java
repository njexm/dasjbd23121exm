package com.proem.exm.dao.purchase;

import java.util.List;
import java.util.Map;

import com.proem.exm.utils.Page;

/**
 * 采购订单购物车分页数据访问接口
 * 
 * @author Administrator
 * 
 */
public interface PurchaseShoppingCartDao {
	public List<Map<String, Object>> getObjPagedList(Page page)
			throws Exception;

	public Long getObjListCount(Page page) throws Exception;
}
