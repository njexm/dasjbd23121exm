package com.proem.exm.dao.settlement;

import java.util.List;
import java.util.Map;

import com.proem.exm.utils.Page;

/**
 * 预付款分页数据接口
 * @author songcj
 * 2015年11月25日 下午2:24:26 
 */
public interface AdvancePaymentDao {
	public List<Map<String, Object>> getObjPagedList(Page page)
			throws Exception;

	public Long getObjListCount(Page page) throws Exception;
}
