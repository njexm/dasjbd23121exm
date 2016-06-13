package com.proem.exm.service.system.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisdi.ctp.model.gen.Module;
import com.proem.exm.dao.system.ModuleDao;
import com.proem.exm.entity.order.ZcOrder;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.system.ModuleService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 菜单ServiceImpl
 * 
 * @author Administrator
 * 
 */
@Service("moduleService")
public class ModuleServiceImpl extends BaseServiceImpl implements ModuleService {
@Autowired
ModuleDao moduleDao;
	public List<Module> getModuleList() {
		return baseDataMng.getAllObj(Module.class);
	}

	public void delete(List<String> list) {
		for(int i=0;i<list.size();i++){
			String id = list.get(i);
			System.out.println(id);
			try {
				baseDataMng.deleteObj(id, Module.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	public List<Map<String,Object>> getRootModuleTreeData() {
		List<Map<String,Object>> list = null;
		try {
			List<Object> paramList = new ArrayList<Object>();
			String sql = "select id,indexnum,modulename as text,modulename,parentid,target"
					+ " from ctp_module"
					+ " where moduletype = ? and parentid = ?"
					+ " order by indexnum asc";
			paramList.clear();
			paramList.add("MODULE");
			paramList.add("ROOT");
			list = baseDataMng.querySqlToLowerCase(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public List<Map<String,Object>> getModuleTreeData() {
		List<Map<String,Object>> list = null;
		try {
			List<Object> paramList = new ArrayList<Object>();
			String sql = "select id,indexnum,modulename as text,modulename,parentid,target"
					+ " from ctp_module"
					+ " where moduletype = ? and parentid = ?"
					+ " order by indexnum asc";
			paramList.clear();
			paramList.add("MODULE");
			paramList.add("ROOT");
			list = baseDataMng.querySqlToLowerCase(sql, paramList);
			
			List<Map<String,Object>> subList = new ArrayList<Map<String,Object>>();
			Map<String,Object> map = null;
			for(int i=0;i<list.size();i++){
				map = list.get(i);
				paramList.clear();
				paramList.add("MODULE");
				paramList.add(map.get("id").toString());
				subList = baseDataMng.querySqlToLowerCase(sql, paramList);
				map.put("children", subList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public List<Map<String,Object>> getModuleTreeDataList() {
		List<Map<String,Object>> list = null;
		try {
			List<Object> paramList = new ArrayList<Object>();
			String sql = "select id,indexnum,modulename as text,modulename,parentid,target"
					+ " from ctp_module"
					+ " where moduletype = ? and parentid = ?"
					+ " order by indexnum asc";
			paramList.clear();
			paramList.add("MODULE");
			paramList.add("ROOT");
			list = baseDataMng.querySqlToLowerCase(sql, paramList);
			
			List<Map<String,Object>> subList = new ArrayList<Map<String,Object>>();
			Map<String,Object> map = null;
			for(int i=0;i<list.size();i++){
				map = list.get(i);
				paramList.clear();
				paramList.add("MODULE");
				paramList.add(map.get("id").toString());
				subList = baseDataMng.querySqlToLowerCase(sql, paramList);
				List<Map<String,Object>> subListSon = new ArrayList<Map<String,Object>>();
				Map<String,Object> mapSon = null;
				if(subList!=null && subList.size()>0){
					for(int a =0;a<subList.size();a++){
						mapSon = subList.get(a);
						paramList.clear();
						paramList.add("MODULE");
						paramList.add(mapSon.get("id").toString());
						subListSon = baseDataMng.querySqlToLowerCase(sql, paramList);
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

	public List<Module> getModuleByUserId(String userId){
		List<Module> listOne = null;
		try {
			String sql =  "select distinct m.id,m.indexnum,m.modulename,m.icon,m.target,m.parentid,m.createtime"
					+ " from ctp_module m"
					+ " join ctp_auth_rule ar on m.id=ar.resourceid"
					+ " join ctp_rel_user_role ur on ar.roleid=ur.rightid"
					+ " join ctp_user u on ur.leftid=u.id"
					+ " where u.id=? and m.moduletype='MODULE' and m.parentid = ?"
					+ " order by m.indexnum, m.createtime";
			List<Object> param = new ArrayList<Object>();
			param.add(userId);
			param.add("ROOT");
			List<Map<String,Object>> list = baseDataMng.querySqlToLowerCase(sql, param);
			listOne = new ArrayList<Module>();
			for(Map<String,Object> map : list){
				Module module = new Module();
				module.setId((String)map.get("id"));
				module.setModuleName((String)map.get("modulename"));
				module.setTarget((String)map.get("target"));
				module.setParentID((String)map.get("parentid"));
				module.setIcon((String)map.get("icon"));
				//获取二级菜单
				param.clear();
				param.add(userId);
				param.add(module.getId());
				List<Map<String,Object>> list2 = baseDataMng.querySqlToLowerCase(sql, param);
				List<Module> listTwo = new ArrayList<Module>();
				for(Map<String, Object> map2 : list2){
					Module module2 = new Module();
					module2.setId((String)map2.get("id"));
					module2.setModuleName((String)map2.get("modulename"));
					module2.setTarget((String)map2.get("target"));
					module2.setParentID((String)map2.get("parentid"));
					module2.setIcon((String)map2.get("icon"));
					//获取三级菜单
					param.clear();
					param.add(userId);
					param.add(module2.getId());
					List<Map<String,Object>> list3 = baseDataMng.querySqlToLowerCase(sql, param);
					List<Module> listThree = new ArrayList<Module>();
					for(Map<String, Object> map3 : list3){
						Module module3 = new Module();
						module3.setId((String)map3.get("id"));
						module3.setModuleName((String)map3.get("modulename"));
						module3.setTarget((String)map3.get("target"));
						module3.setParentID((String)map3.get("parentid"));
						module3.setIcon((String)map3.get("icon"));
						listThree.add(module3);
					}
					module2.setChildren(listThree);
					listTwo.add(module2);
				}
				module.setChildren(listTwo);
				
				listOne.add(module);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOne;
	}

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj)
			throws Exception {
		String sql = "select 'closed' as state, a.* from CTP_MODULE a where a.parentid='ROOT' ORDER BY a.indexnum  ";
		page.setSql(sql);
		List<Map<String, Object>> rows = moduleDao.getObjPagedList(page);
		Long total = moduleDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinConditionUserName(Object obj) {
		ZcOrder userInfo = (ZcOrder) obj;
		String conditions = "";
		if (StringUtil.validate(userInfo.getId())) {
			conditions += " and a.name like'%" + userInfo.getId()
					+ "%'";
		}
		return conditions;
	}

	@Override
	public List<Map<String, Object>> getModuleListByParent(String parentId) {
		String sql =" select 'open' as state, a.* from CTP_MODULE a where a.parentid='"+parentId+"' ORDER BY a.indexnum " ;
		Page page = new Page();
		page.setPage(1);
		page.setRows(100000);
		page.setSql(sql);
		try {
			List<Map<String, Object>> rows = moduleDao
					.getObjPagedList(page);
			return rows;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
