package com.proem.exm.service.impl.settlement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.settlement.BranchAchieveDao;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.settlement.BranchAchieveService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;


/**
 * @author songcj
 * 2016年1月23日 下午3:45:29 
 */
@Service("BranchAchieveService")
public class BranchAchieveServiceImpl extends BaseServiceImpl implements BranchAchieveService{

	@Autowired 
	BranchAchieveDao branchAchieveDao;

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select street,branch_code,branch_name,associator_address,ordercome,city,county,province,sum(orderamount) as total_money,member_id from"
				+ " (select a.branch_code,a.branch_name,d.associator_address,c.orderamount,c.member_id,c.ordercome,b.city,b.county,b.province,b.street"
				+ " from zc_branch_total a"
				+ " left join zc_zoning b on b.id = a.zoning_id"
				+ " left join zc_order_history c on  b.street = c.branchid"
				+ " left join zc_associator_info d on d.id = c.member_id"
				+ " where b.street is not null )"
				+ " group by street,branch_code,branch_name,associator_address,ordercome,city,county,province,member_id";
		sql += joinCondition(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = branchAchieveDao.getObjPagedList(page);
		Long total = branchAchieveDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	private String joinCondition(Object obj) {
		String conditions = "";
		return conditions;
	}
}
