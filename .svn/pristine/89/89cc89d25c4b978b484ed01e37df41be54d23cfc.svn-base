package com.proem.exm.service.basic.code;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.proem.exm.entity.basic.code.Code;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.Result;

public interface CodeService extends BaseService{
	
	/**
	 * 分页查询数据
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception;

	public Result checkDictionary(Code code);

	public void saveOrUpdateDictionary(HttpServletRequest request,Code code);

	public void deleteSelfAndchild(String id);

	public List<Code> getAllParent();
	
	Map initCodeMap();

	public List<Code> findByParentId(String id);

	List<Map<String, Object>> getListByParent(String parentId);
	public Result initCode(String codeType);
	public List<Map<String,Object>> findByType(String type);
}
