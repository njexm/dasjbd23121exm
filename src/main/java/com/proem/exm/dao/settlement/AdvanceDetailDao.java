package com.proem.exm.dao.settlement;

import java.util.List;
import java.util.Map;

import com.proem.exm.utils.Page;

/**
 * 预收款明细分页接口
 * 
 * @author Administrator
 * 
 */
public interface AdvanceDetailDao {
	public List<Map<String, Object>> getObjPagedList(Page page)
			throws Exception;

	public Long getObjListCount(Page page) throws Exception;
}
