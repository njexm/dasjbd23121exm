package com.proem.exm.service.warehouse;

import java.util.Map;

import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 盘点号列表中信息的修改和查询
 * @author zhusf
 *
 */
public interface WareHouseService extends BaseService {
	/**
	 * 修改已经存在的盘点号申请单或者填写空白的。
	 * @param obj
	 */
	public void updateObj(Object obj);
	/**
	 * 分页查询盘点号
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception;
	/**
	 * 通过ID查询数据,可以关联多张表
	 * @param obj
	 * @return
	 */
	public Map<String, Object> getObjById(Object obj);

}
