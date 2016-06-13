package com.proem.exm.dao.wholesaleGroupPurchase.customer;

import java.util.List;
import java.util.Map;

import com.proem.exm.utils.Page;

/**
 * 客户档案
 * @author ZuoYM 
 * @com proem
 */
public interface CustomerInfoDao {
	
	public List<Map<String, Object>> getObjPagedList(Page page) throws Exception;

	public Long getObjListCount(Page page) throws Exception;
}
