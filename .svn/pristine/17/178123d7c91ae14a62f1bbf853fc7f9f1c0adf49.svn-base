package com.proem.exm.service.system;

import java.util.List;
import java.util.Map;

import com.cisdi.ctp.model.gen.Module;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

public interface ModuleService extends BaseService {

	/**
	 * 获取菜单List
	 * @return
	 */
	public List<Module> getModuleList();
	public List<Map<String, Object>> getModuleListByParent(String parentId);
	/**
	 * 获取根级树形菜单数据
	 * @return
	 */
	public List<Map<String,Object>> getRootModuleTreeData();

	/**
	 * 获取树形菜单数据
	 * @return
	 */
	public List<Map<String,Object>> getModuleTreeData();
	/**
	 * 获取树形菜单数据
	 * @return
	 */
	public List<Map<String,Object>> getModuleTreeDataList();
	
	
	/**
	 * 根据UserID获取菜单权限
	 * @param userId
	 * @return
	 */
	public List<Module> getModuleByUserId(String userId);
	/**
	 * 分页查询数据
	 * 
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception;

}
