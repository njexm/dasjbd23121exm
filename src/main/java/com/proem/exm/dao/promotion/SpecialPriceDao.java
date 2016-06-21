package com.proem.exm.dao.promotion;

import java.util.List;
import java.util.Map;

import com.proem.exm.utils.Page;

public interface SpecialPriceDao {
	
	public List<Map<String, Object>> getObjPagedList(Page page) throws Exception;

	public Long getObjListCount(Page page) throws Exception;
}
