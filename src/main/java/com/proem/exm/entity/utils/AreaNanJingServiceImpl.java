package com.proem.exm.entity.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;










import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

@Service("AreaNanJingService")
public class AreaNanJingServiceImpl extends BaseServiceImpl implements AreaNanJingService{

	@Autowired
	private AreaNanJingDao areaNanJingDao;

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select * from zc_area_nanjing  where 1=1 ";
		sql += joinCondition(obj);
		sql += " order by CREATETIME desc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = areaNanJingDao.getObjPagedList(page);
		Long total = areaNanJingDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * @param obj
	 * @return
	 */
	 private String joinCondition(Object obj){
		 AreaNanJing areaNanJing =  (AreaNanJing) obj;
			String conditions = "";
			if(!StringUtil.validate(areaNanJing.getAreaName())&&!StringUtil.validate(areaNanJing.getAddress())&&!StringUtil.validate(areaNanJing.getRealname())){
				conditions += " and leveltype='1'";
			}else{
				if (StringUtil.validate(areaNanJing.getAreaName())) {
					conditions += " and AREANAME like'%" + areaNanJing.getAreaName() + "%'";
				}
				if (StringUtil.validate(areaNanJing.getAddress())) {
					conditions += " and ADDRESS like'%" + areaNanJing.getAddress() + "%'";
				}
				if (StringUtil.validate(areaNanJing.getRealname())) {
					conditions += " and REALNAME like'%" + areaNanJing.getRealname() + "%'";
				}
			}
		 return conditions;
	 }

	 
	 /**
	  * 找到leveltype=3 的地址信息
	  */
	@Override
	public List getObjList(AreaNanJing areaNanJing) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;		
		try {
			String sql = "select * from zc_area_nanjing where leveltype = 3 ";
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 找父目录的信息 1级为0 2级为3268
	 */
	@Override
	public List<Map<String, Object>> getListByParent(String parentId) {
		String sql = "select * from zc_area_nanjing a  where  a.parentid = '"+parentId +"' order by a.id ";
		Page page = new Page();
		page.setPage(1);
		page.setRows(100000);
		page.setSql(sql);
		try {
			List<Map<String, Object>> rows = areaNanJingDao.getObjPagedList(page);
			return rows;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 建树目录
	 */
	@Override
	public List<Map<String, Object>> getTreeData() {
		List<Map<String,Object>> list = null;
		try {
			List<Object> paramList = new ArrayList<Object>();
			String sql = "select a.id,a.areaname as text from zc_area_nanjing a where a.parentid = ? order by a.id asc";
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
//				List<Map<String,Object>> subListSon = new ArrayList<Map<String,Object>>();
//				Map<String,Object> mapSon = null;
//				if(subList!=null && subList.size()>0){
//					for(int a =0;a<subList.size();a++){
//						mapSon = subList.get(a);
//						paramList.clear();
//						paramList.add(mapSon.get("id").toString());
//						subListSon = baseDataMng.querySqlToLowerCase(sql, paramList);
//						mapSon.put("children", subListSon);
//					}
//				}
				map.put("children", subList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getCountryList(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;		
		try {
			String sql = "select * from zc_area_nanjing where leveltype = 2 ";
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}




}
