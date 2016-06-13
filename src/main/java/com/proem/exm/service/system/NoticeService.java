package com.proem.exm.service.system;

import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

public interface NoticeService extends BaseService{

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception;
	
}
