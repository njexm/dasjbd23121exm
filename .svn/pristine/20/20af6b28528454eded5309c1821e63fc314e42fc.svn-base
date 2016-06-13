package com.proem.exm.service.basic.CommodityClassify;

import java.util.List;
import java.util.Map;

import com.proem.exm.entity.basic.CommodityClassify.CommodityClassify;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

public interface CommodityClassifyService extends BaseService{
	
	/**
	 * 分页查询数据
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception;
	public DataGrid getPagedDataGridList(Page page, Object obj) throws Exception;
	public List getObjList(CommodityClassify commodityClassify);

	List<Map<String, Object>> getListByParent(String parentId);

	public List<Map<String, Object>> getTreeData();

	
}
