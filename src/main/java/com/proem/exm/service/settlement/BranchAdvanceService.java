package com.proem.exm.service.settlement;

import java.util.List;
import java.util.Map;

import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 分店预收款业务逻辑接口
 * 
 * @author Administrator
 * 
 */
public interface BranchAdvanceService extends BaseService {

	DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception;

	List<Map<String, Object>> getObjList(Object obj);

}
