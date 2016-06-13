package com.proem.exm.service.impl.branch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.basic.branch.BranchDao;
import com.proem.exm.entity.basic.CommodityClassify.CommodityClassify;
import com.proem.exm.service.branch.BranchService;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;
import com.proem.exm.views.branch.BranchsView;

@Service("branchService")
public class BranchServiceImpl extends BaseServiceImpl implements BranchService {

	@Autowired
	BranchDao branchDao;

	@Override
	public void updateObj(Object obj) {
		// TODO Auto-generated method stub

	}

	private String joinCondition(Object obj) {
		BranchsView bv = (BranchsView) obj;
		String conditions = "";
		if (StringUtil.validate(bv.getBranch_code())) {
			conditions += " and a.BRANCH_CODE like '%" + bv.getBranch_code()
					+ "%'";
		}
		if (StringUtil.validate(bv.getBranch_name())) {
			conditions += " and a.BRANCH_NAME like '%" + bv.getBranch_name()
					+ "%'";
		}
		if (StringUtil.validate(bv.getBranchTotal())) {
			conditions += " and BRANCHTOTAL_ID like '%" + bv.getBranchTotal()
					+ "%'";
		}

		return conditions;
	}

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select a.*,g.branch_name as branchname,b.username,b.mobilephone,d.areaname as province,e.areaname as city,f.areaname as county,c.street as street from ZC_BRANCH_INFO a "
				+ "left join  zc_user_info b on b.id=a.customer_id "
				+ "left join zc_zoning c on c.id=a.zoning_id "
				+ "left join zc_area d on d.id=c.province "
				+ "left join zc_area e on e.id=c.city "
				+ "left join zc_branch_total g on g.id=a.branchTotal_id "
				+ "left join zc_area f on f.id=c.county where a.delFlag=0  ";
		sql += joinCondition(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = branchDao.getObjPagedList(page);
		Long total = branchDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public Map<String, Object> getObjById(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getObjList(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		String sql = "";
		try {
			sql = "select a.* from zc_branch_info a";
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getTreeData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			List<Object> paramList = new ArrayList<Object>();
			String sql = "select id,branch_name as text from zc_branch_total where 1=1 order by createtime asc";
			paramList.clear();
			paramList.add("0");
			Map<String, Object> mapFa = new HashMap<String, Object>();
			mapFa.put("id", "1");
			mapFa.put("text", "分店");
			list.add(mapFa);
			List<Map<String, Object>> subList = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = null;
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);
				paramList.clear();
				subList = baseDataMng.querySqlToLowerCase(sql, paramList);
				map.put("children", subList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
