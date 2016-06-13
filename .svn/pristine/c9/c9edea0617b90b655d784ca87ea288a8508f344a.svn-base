package com.proem.exm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.cisdi.ctp.datamng.BaseDataMng;
import com.cisdi.ctp.model.gen.Root;
import com.cisdi.ctp.utils.common.StringUtils;
import com.cisdi.ctp.web.base.CommonService;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;


@Transactional
public class BaseServiceImpl extends CommonService implements BaseService {

	@Resource
	public BaseDataMng baseDataMng;
	
	@Override
	public Object getObjById(String id, String className) {
		Object object = baseDataMng.getObj(id, className);
		return object;
	}

	@Override
	public void deleteObjById(String id, String className) {
		try {
			baseDataMng.deleteObj(id, className);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveObj(Root root) {
		try {
			baseDataMng.saveObj(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateObj(Root root) {
		// TODO 自动生成的方法存根
		try {
			baseDataMng.updateObj(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public <T extends Root> List<T> getListByObj(Class<T> classType, String condition) {
		try {
			return baseDataMng.getObjListByCondition(classType, condition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public <T extends Root> Long getCountByObj(Class<T> classType, String condition) {
		try {
			return baseDataMng.getAllObjCountByCondition(classType, condition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Object getObjByName(String name, Class objClass) {
		Object object = baseDataMng.getObjByName(name, objClass);
		return object;
	}
	@Override
	public DataGrid getPagedDataGridObj(String className, Page page) {
		String condition = "";
		if(null != page.getCondition()){
			condition = page.getCondition();
		}
		String order = "";
		if(null != page.getOrder()){
			order = page.getOrder();
		}
		DataGrid dataGrid = null;
		Long total = 0L;
		List<Root> rows = new ArrayList<Root>();
		
		int firstRow = (page.getPage()-1) * page.getRows();
		int pageSize = page.getRows();
		if(!StringUtils.isBlank(condition)){
			total = baseDataMng.getAllObjCountByCondition(className, condition);
		}else{
			total = baseDataMng.getAllObjCount(className);
		}
		rows = baseDataMng.getPagedObjOrdered(className, condition, firstRow, pageSize, order);
		
		dataGrid = new DataGrid(total, rows);
		return dataGrid;
	}

	@Override
	public Object getObjByCondition(Class classType, String condition) {
		return baseDataMng.getObjByCondition(classType, condition);
	}

	
}
