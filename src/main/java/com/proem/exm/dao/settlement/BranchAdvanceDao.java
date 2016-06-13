package com.proem.exm.dao.settlement;

import java.util.List;
import java.util.Map;

import com.proem.exm.utils.Page;

/**
 * 分店预收款分页信息数据访问接口
 * 
 * @author Administrator
 * 
 */
public interface BranchAdvanceDao {
	public List<Map<String, Object>> getObjPagedList(Page page)
			throws Exception;

	public Long getObjListCount(Page page) throws Exception;
}
