package com.proem.exm.service;


import java.util.List;

import com.cisdi.ctp.model.gen.Root;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

public interface BaseService {
	
	/**
	 * 查询单张表数据库   - 单纯单表操作
	 * @param id
	 * @param className = entitiy 中的类
	 * @return
	 */
	public Object getObjById(String id, String className);
	
	public Object getObjByCondition(Class classType, String condition);
	
	
	public Object getObjByName(String name,Class objClass);
	
	/**
	 * 通过ID删除表信息  - 单纯单表操作
	 * @param id
	 * @param className = entity 中的类
	 */
	public void deleteObjById(String id, String className);
	
	/**
	 * 保存信息 - 单纯单表操作
	 * @param root
	 */
	public void saveObj(Root root);
	public <T extends Root> List<T> getListByObj(Class<T> classType,String condition);
	public void updateObj(Root root);
	
	/**
	 * 根据条件查询个数
	 */
	public <T extends Root> Long getCountByObj(Class<T> classType,String condition);


	DataGrid getPagedDataGridObj(String className, Page page);
	
}
