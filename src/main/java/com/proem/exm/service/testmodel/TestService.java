package com.proem.exm.service.testmodel;

import java.util.List;
import java.util.Map;






import com.proem.exm.entity.TestTable;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

public interface TestService extends BaseService{

	/**
	 * 不分页查询所有数据
	 * @return
	 */
	public List<TestTable> getObjList();

	/**
	 * 修改已经存在的数据
	 * @param testTable
	 */
	public void updateObj(TestTable testTable);
	
	/**
	 * 分页查询数据
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObj(Page page,Map<String, Object> paramMap);

	/**
	 * 通过ID查询数据
	 * @param id
	 * @return
	 */
	public Map<String, Object> getObjById(String id);

}
