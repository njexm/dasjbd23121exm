package com.proem.exm.service.impl.settlement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.settlement.TaxManagerDao;
import com.proem.exm.entity.settlement.TaxManager;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.settlement.TaxManagerService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 税务管理业务逻辑访问实现类
 * 
 * @author Administrator
 * 
 */
@Service("taxManagerService")
public class TaxManagerServiceImpl extends BaseServiceImpl implements
		TaxManagerService {
	@Autowired
	TaxManagerDao taxManagerDao;

	/**
	 * 分页查询数据
	 */
	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select * from zc_settlement_tax where 1=1";
		sql += joinCondition(obj);
		sql += " order by UPDATETIME desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = taxManagerDao.getObjPagedList(page);
		Long total = taxManagerDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj) {
		TaxManager taxManager = (TaxManager) obj;
		String conditions = "";
		if (StringUtil.validate(taxManager.getTaxCode())) {
			conditions += " and TAXCODE like '%" + taxManager.getTaxCode()
					+ "%'  ";
		}
		if (StringUtil.validate(taxManager.getTaxNumber())) {
			conditions += " and TAXNUMBER like '%" + taxManager.getTaxNumber()
					+ "%'  ";
		}
		if (StringUtil.validate(taxManager.getTaxType())) {
			conditions += " and TAXTYPE like '%" + taxManager.getTaxType()
					+ "%'  ";
		}
		return conditions;
	}
}
