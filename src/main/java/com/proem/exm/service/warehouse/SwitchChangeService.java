package com.proem.exm.service.warehouse;

import java.util.List;
import java.util.Map;

import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
/**
 * 
 *
 */
public interface SwitchChangeService extends BaseService {
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

	public DataGrid getPagedDataGridObjById(Page page, Object obj,String id) throws Exception;

	public DataGrid getChangeAddGoods(Page page,String id, Object obj, CtpUser ctpUser)
			throws Exception;

	DataGrid getSwitchChangeItems(Page page, Object obj, String switchOutBranch)
			throws Exception;

	public Map<String, Object> getAllByExcel(String path, CtpUser user);
	

}
