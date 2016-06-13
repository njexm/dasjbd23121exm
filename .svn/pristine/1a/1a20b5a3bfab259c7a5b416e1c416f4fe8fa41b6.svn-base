package com.proem.exm.service.impl.settlement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.settlement.AdvanceDetailDao;
import com.proem.exm.entity.purchase.PurchaseOrderGoodsItems;
import com.proem.exm.entity.settlement.AdvanceDetail;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.settlement.AdvanceDetailService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 预收款明细业务访问实现类
 * 
 * @author Administrator
 * 
 */
@Service("advanceDetailService")
public class AdvanceDetailServiceImpl extends BaseServiceImpl implements
		AdvanceDetailService {
	@Autowired
	AdvanceDetailDao advanceDetailDao;

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select * from zc_branch_advance_detail where BRANCHADVANCE_ID is null ";
		page.setSql(sql);
		List<Map<String, Object>> rows = advanceDetailDao.getObjPagedList(page);
		Long total = advanceDetailDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public DataGrid getPagedDataDetailGridObj(Page page, Object obj)
			throws Exception {
		String sql = "select * from zc_branch_advance_detail where 1=1 ";
		sql += joinCondition(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = advanceDetailDao.getObjPagedList(page);
		Long total = advanceDetailDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj) {
		AdvanceDetail advanceDetail = (AdvanceDetail) obj;
		String conditions = "";
		if (StringUtil.validate(advanceDetail.getBranchAdvance())) {
			conditions += " and BRANCHADVANCE_ID = '"
					+ advanceDetail.getBranchAdvance().getId() + "'";
		}
		return conditions;
	}

}
