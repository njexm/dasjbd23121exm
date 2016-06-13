package com.proem.exm.dao.purchase;

import java.util.List;
import java.util.Map;

import com.proem.exm.utils.Page;

/**
 * 采购退货单分页信息数据访问接口
 * 
 * @author Administrator
 * 
 */
public interface PurchaseReturnDao {

	public List<Map<String, Object>> getObjPagedList(Page page)
			throws Exception;

	public Long getObjListCount(Page page) throws Exception;
}