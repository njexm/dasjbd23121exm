package com.proem.exm.service.system;

import javax.servlet.http.HttpServletRequest;

import com.proem.exm.entity.system.LogManage;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.Result;

public interface LogManageService extends BaseService{

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception;
	
	/**
	 * 插入日志记录
	 * @param request
	 * @param description 日志内容描述
	 * @param moduleName  模块名称
	 */
	public void insertLog(HttpServletRequest request,String description,String moduleName);
}
