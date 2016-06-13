package com.proem.exm.service.impl.branch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.basic.branch.BranchTotalDao;
import com.proem.exm.entity.basic.CommodityClassify.CommodityClassify;
import com.proem.exm.entity.utils.AreaNanJing;
import com.proem.exm.service.branch.BranchTotalService;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;
import com.proem.exm.views.branch.BranchsView;

@Service("branchTotalService")
public class BranchTotalServiceImpl extends BaseServiceImpl implements BranchTotalService{

	@Autowired BranchTotalDao branchTotalDao;
	
	@Override
	public void updateObj(Object obj) {
		// TODO Auto-generated method stub
		
	}

	private String joinCondition(Object obj) {
		BranchsView bv = (BranchsView) obj;
		String conditions = "";
		if (StringUtil.validate(bv.getBranch_code())) {
			conditions += " and branch_code = '" + bv.getBranch_code()+ "'";
		}
		if (StringUtil.validate(bv.getBranch_name())) {
			conditions += " and branch_name like '%" + bv.getBranch_name()+ "%'";
		}
		return conditions;
	}
	
	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select a.*,b.username,b.mobilephone,c.province,c.city,d.areaname as county,e.areaname as street from ZC_BRANCH_TOTAL a left join  zc_user_info b on b.id=a.customer_id left join zc_zoning c on c.id=a.zoning_id left join zc_area_nanjing d on d.id=c.county left join zc_area_nanjing e on e.id=c.street where a.delFlag=0 ";
		sql += joinCondition(obj);
		sql += "order by a.branch_code desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = branchTotalDao.getObjPagedList(page);
		Long total = branchTotalDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}
	@Override
	public List<Map<String, Object>> getObjList1(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select * from ZC_BRANCH_TOTAL where 1=1 ";
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
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
		String sql ="";
		try {			
			sql="select a.* from  zc_branch_TOTAL a";
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获得**区中的小区
	 */
	@Override
	public List<Map<String, Object>> getCountryList(String id) {
		AreaNanJing areaNanJing = new AreaNanJing();
		List<Object>  paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select * from zc_area_nanjing where parentid = '"+id+"'";
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
