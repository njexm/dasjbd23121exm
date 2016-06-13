package com.proem.exm.service.impl.settlement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.settlement.BranchAdvanceDao;
import com.proem.exm.entity.basic.provider.ProviderInfo;
import com.proem.exm.entity.settlement.BranchAdvance;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.settlement.BranchAdvanceService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;
import com.proem.exm.views.branch.BranchsView;

/**
 * 分店预收款业务逻辑访问实现类
 * 
 * @author Administrator
 * 
 */
@Service("branchAdvanceService")
public class BranchAdvanceServiceImpl extends BaseServiceImpl implements
		BranchAdvanceService {
	@Autowired
	BranchAdvanceDao branchAdvanceDao;

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select a.*,b.branch_name,c.username,c.mobilephone,d.areaname as province,e.areaname as city,f.areaname as county,g.street as street from zc_branch_advance a left join zc_branch_info b on a.branch_id=b.id left join zc_user_info c on b.customer_id=c.id left join zc_zoning g on g.id=b.zoning_id left join zc_area d on d.id=g.province left join zc_area e on e.id=g.city left join zc_area f on f.id=g.county where 1=1 ";
		sql += joinCondition(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = branchAdvanceDao.getObjPagedList(page);
		Long total = branchAdvanceDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	private String joinCondition(Object obj) {
		BranchAdvance branchAdvance = (BranchAdvance) obj;
		String conditions = "";
		if (StringUtil.validate(branchAdvance.getStatue())) {
			conditions += " and STATUE ='" + branchAdvance.getStatue() + "'";
		}
		return conditions;
	}

	/**
	 * 按条件查询
	 */
	@Override
	public List<Map<String, Object>> getObjList(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select * from zc_branch_info";
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
