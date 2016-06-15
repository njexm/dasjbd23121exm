package com.proem.exm.service.basic.associator;

import java.util.List;
import java.util.Map;

import com.proem.exm.entity.basic.associator.Associator;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
/**
 * 
 * @author ZuoYM
 *
 */
public interface AssociatorService extends BaseService {
	
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
	/**
	 * 分页查询会员消费方式
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public DataGrid getPageDataGridByPayInfo(Page page, Object obj) throws Exception;

}
