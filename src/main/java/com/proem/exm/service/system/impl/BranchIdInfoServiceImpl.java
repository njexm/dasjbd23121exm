package com.proem.exm.service.system.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.system.BranchIdInfoDao;
import com.proem.exm.entity.system.BranchIdInfo;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.system.BranchIdInfoService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 
 * @author ZuoYM
 * 
 */
@Service("BranchIdInfoService")
public class BranchIdInfoServiceImpl extends BaseServiceImpl implements BranchIdInfoService {

	@Autowired
	BranchIdInfoDao branchIdInfoDao;

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select * from zc_branchId_info  where 1=1 ";
		sql += joinCondition(obj);
//		sql+=" order by createtime desc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = branchIdInfoDao.getObjPagedList(page);
		Long total = branchIdInfoDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * @param obj
	 * @return
	 */
	 private String joinCondition(Object obj){
		 BranchIdInfo branchIdInfo = (BranchIdInfo) obj;
		 String conditions = "";
		 if (StringUtil.validate(branchIdInfo.getBranch_name())) {
			 conditions += " and branch_name like'%" + branchIdInfo.getBranch_name() + "%'";
		 }
		 return conditions;
	 }

	@Override
	public List<Map<String, Object>> getObjList(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select * from zc_branchId_info";
			BranchIdInfo branchIdInfo = (BranchIdInfo) obj;
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
