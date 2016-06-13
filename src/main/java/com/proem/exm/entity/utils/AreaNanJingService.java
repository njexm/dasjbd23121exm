package com.proem.exm.entity.utils;

import java.util.List;
import java.util.Map;

import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

public interface AreaNanJingService extends BaseService{
	/**
	 * 分页查询数据
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception;
	
	public List getObjList(AreaNanJing areaNanJing);

	List<Map<String, Object>> getListByParent(String parentId);

	public List<Map<String, Object>> getTreeData();

	public List<Map<String, Object>> getCountryList(Object obj);


	
}
