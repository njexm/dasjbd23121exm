package com.proem.exm.service.basic.CommodityClassify.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.basic.CommodityClassify.CommodityClassifyDao;
import com.proem.exm.entity.basic.CommodityClassify.CommodityClassify;
import com.proem.exm.service.basic.CommodityClassify.CommodityClassifyService;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

@Service("CommodityClassifyService")
public class CommodityClassifyServiceImpl extends BaseServiceImpl implements CommodityClassifyService{

	@Autowired CommodityClassifyDao commodityClassifyDao;
	

	private String joinCondition(Object obj) {
		CommodityClassify zcClassify=(CommodityClassify) obj;
		String conditions = "";
		if (StringUtil.validate(zcClassify.getClassify_type())) {
			conditions += " and a.CLASSIFY_TYPE = '" + zcClassify.getClassify_type()
					+ "'";
			if("1".equals(zcClassify.getClassify_type())){
				if (!StringUtil.validate(zcClassify.getClassify_level())
						&&!StringUtil.validate(zcClassify.getClassify_code())
						&&!StringUtil.validate(zcClassify.getClassify_name())) {
					conditions += " and a.parentid='0'";
				}
			}
			if (StringUtil.validate(zcClassify.getClassify_code())) {
				conditions += " and a.CLASSIFY_CODE like '%" + zcClassify.getClassify_code()
						+ "%'";
			}
			if (StringUtil.validate(zcClassify.getClassify_level())) {
				conditions += " and a.CLASSIFY_LEVEL = '" + zcClassify.getClassify_level()
						+ "'";
			}
			if (StringUtil.validate(zcClassify.getClassify_name())) {
				conditions += " and a.CLASSIFY_NAME like '%" + zcClassify.getClassify_name()
						+ "%'";
			}
			
			
		}
		return conditions;
	}
	
	private String joinConditionByList(Object obj) {
		CommodityClassify zcClassify=(CommodityClassify) obj;
		String conditions = "";
		if (StringUtil.validate(zcClassify.getClassify_type())) {
			conditions += " and a.CLASSIFY_TYPE = '" + zcClassify.getClassify_type()
					+ "'";
		}
		if (StringUtil.validate(zcClassify.getClassify_code())) {
				conditions += " and a.CLASSIFY_CODE like '%" + zcClassify.getClassify_code()
						+ "%'";
		}
		if (StringUtil.validate(zcClassify.getClassify_level())) {
				conditions += " and a.CLASSIFY_LEVEL = '" + zcClassify.getClassify_level()
						+ "'";
		}
		if (StringUtil.validate(zcClassify.getClassify_name())) {
				conditions += " and a.CLASSIFY_NAME like '%" + zcClassify.getClassify_name()
					+ "%'";
		}
			
			
		
		return conditions;
	}
	
	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select a.* from zc_classify_info  a where a.delFlag=0  ";
		sql += joinCondition(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = commodityClassifyDao.getObjPagedList(page);
		Long total = commodityClassifyDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public DataGrid getPagedDataGridList(Page page, Object obj) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select a.* from zc_classify_info  a where a.delFlag=0  ";
		sql += joinConditionByList(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = commodityClassifyDao.getObjPagedList(page);
		Long total = commodityClassifyDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}
	
	public String sqlObj(){
		String sql="'closed' as state,"+"a.*";
		return sql;
		
	}
	
	@Override
	public List getObjList(CommodityClassify commodityClassify) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		String sql ="";
		try {
			if("1".equals(commodityClassify.getClassify_type())){
				sql= "select * from zc_classify_info where classify_level=3 and classify_type='"+commodityClassify.getClassify_type()+"'";
			}else{
				 sql = "select * from zc_classify_info where  classify_type='"+commodityClassify.getClassify_type()+"'";
			}
			
			CommodityClassify commodityClassify1 = (CommodityClassify) commodityClassify;
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<Map<String, Object>> getListByParent(String parentId) {
		String sql =" select  a.* from zc_classify_info a where a.parentid='"+parentId.trim()+"' ORDER BY a.order_index asc" ;
		Page page = new Page();
		page.setPage(1);
		page.setRows(100000);
		page.setSql(sql);
		try {
			List<Map<String, Object>> rows = commodityClassifyDao
					.getObjPagedList(page);
			return rows;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getTreeData() {
		List<Map<String,Object>> list = null;
		try {
			List<Object> paramList = new ArrayList<Object>();
			String sql = "select a.id,a.classify_name as text"
					+ " from zc_classify_info a"
					+ " where  a.parentid = ?"
					+ " order by a.order_index asc";
			paramList.clear();
			paramList.add("0");
			list = baseDataMng.querySqlToLowerCase(sql, paramList);
			
			List<Map<String,Object>> subList = new ArrayList<Map<String,Object>>();
			Map<String,Object> map = null;
			for(int i=0;i<list.size();i++){
				map = list.get(i);
				paramList.clear();
				paramList.add(map.get("id").toString());
				subList = baseDataMng.querySqlToLowerCase(sql, paramList);
				List<Map<String,Object>> subListSon = new ArrayList<Map<String,Object>>();
				Map<String,Object> mapSon = null;
				if(subList!=null && subList.size()>0){
					for(int a =0;a<subList.size();a++){
						mapSon = subList.get(a);
						paramList.clear();
						paramList.add(mapSon.get("id").toString());
						subListSon = baseDataMng.querySqlToLowerCase(sql, paramList);
						List<Map<String,Object>> subListSon1 = new ArrayList<Map<String,Object>>();
						Map<String,Object> mapSon1 = null;
						if(subListSon!=null && subListSon.size()>0){
							for(int j =0;j<subListSon.size();j++){
								mapSon1 = subListSon.get(j);
								paramList.clear();
								paramList.add(mapSon1.get("id").toString());
								subListSon1 = baseDataMng.querySqlToLowerCase(sql, paramList);
								mapSon1.put("children", subListSon1);
							}
						}
						mapSon.put("children", subListSon);
					}
				}
				map.put("children", subList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
