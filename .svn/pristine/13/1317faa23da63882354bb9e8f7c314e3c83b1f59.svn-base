package com.proem.exm.service.warehouse;

import java.util.List;
import java.util.Map;

import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
/**
 * 
 * @author zhusf
 *
 */
public interface CheckNumberService extends BaseService {
	/**
	 * 分页查询数据
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception;

	/**
	 * 无分页条件查询所有
	 * 
	 * @param obj
	 * @return
	 */
	public List<Map<String, Object>> getObjList(Object obj);

	public DataGrid getGoodList(Page page, String type, String classifyIds,
			GoodsFile goodsFile, String houseId)throws Exception ;

	public DataGrid getCheckGoodList(Page page, String id, GoodsFile goodsFile,String houseId)throws Exception;

	

}
