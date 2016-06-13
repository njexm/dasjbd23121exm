package com.proem.exm.dao.wholesaleGroupPurchase;

import java.util.List;
import java.util.Map;

import com.proem.exm.utils.Page;

/**
 * 批发团购订单接口
 * @author xuehr
 *
 */
public interface WholesaleGroupPurchaseOrderDao {

	public List<Map<String, Object>> getObjPagedList(Page page)
			throws Exception;

	public Long getObjListCount(Page page) throws Exception;
}
