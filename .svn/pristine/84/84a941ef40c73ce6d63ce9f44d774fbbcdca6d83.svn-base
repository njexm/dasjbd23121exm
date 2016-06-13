package com.proem.exm.service.basic;

import java.util.List;
import java.util.Map;

import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * @author songcj 商品信息service 2015年10月20日下午2:16:32
 */

public interface GoodsFileService extends BaseService {

	/**
	 * 修改表信息
	 * 
	 * @param Orders
	 */
	public void updateObj(Object obj);

	/**
	 * 分页查询数据
	 * 
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception;

	/**
	 * 分页查询供应商为空的商品信息
	 * 
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataNullProvider(Page page, Object obj)
			throws Exception;

	/**
	 * 通过ID查询数据,可以关联多张表
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> getObjById(Object obj);

	/**
	 * 无分页条件查询所有
	 * 
	 * @param obj
	 * @return
	 */
	public List<Map<String, Object>> getObjList(Object obj);

	public Map<String, Object> getAllByExcel(String path);
	
	public int saveByExcel(GoodsFile goodsFile);


}
