package com.proem.exm.dao.purchase;

import java.util.List;
import java.util.Map;

import com.proem.exm.utils.Page;

/**
 * @author songcj
 *
 * 2015年11月11日下午4:16:23
 */
public interface PurchaseReceiveItemDao {
	public List<Map<String, Object>> getObjPagedList(Page page)
			throws Exception;

	public Long getObjListCount(Page page) throws Exception;
}
