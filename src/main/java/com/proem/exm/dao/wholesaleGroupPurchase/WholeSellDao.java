package com.proem.exm.dao.wholesaleGroupPurchase;

import java.util.List;
import java.util.Map;

import com.proem.exm.utils.Page;

/**
 * 团购销售单数据访问接口
 * 
 * @author Administrator
 * 
 */
public interface WholeSellDao {
	public List<Map<String, Object>> getObjPagedList(Page page)
			throws Exception;

	public Long getObjListCount(Page page) throws Exception;
}
