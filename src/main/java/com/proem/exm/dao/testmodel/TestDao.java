package com.proem.exm.dao.testmodel;

import java.util.List;
import java.util.Map;

import com.proem.exm.utils.Page;

public interface TestDao {
	
	public List<Map<String, Object>> getObjPagedList(Page page);

	public Long getObjListCount(Page page);
	
}
