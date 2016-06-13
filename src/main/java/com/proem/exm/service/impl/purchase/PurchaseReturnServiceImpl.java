package com.proem.exm.service.impl.purchase;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.purchase.PurchaseReturnDao;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.purchase.PurchaseReturnService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 采购退货单业务逻辑访问实现类
 * 
 * @author Administrator
 * 
 */
@Service("purchaseReturnService")
public class PurchaseReturnServiceImpl extends BaseServiceImpl implements
		PurchaseReturnService {
	@Autowired
	PurchaseReturnDao purchaseReturnDao;

	/**
	 * 分页查询数据
	 */
	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj, String statue)
			throws Exception {
		String sql = "select * from ZC_PURCHASE_RETURN where 1=1";
		sql += joinCondition(obj, statue);
		sql += "order by CREATETIME desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = purchaseReturnDao
				.getObjPagedList(page);
		Long total = purchaseReturnDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj, String statue) {
		String conditions = "";
		if (StringUtil.validate(statue)) {
			conditions += " and STATUE ='" + statue + "'";
		}
		return conditions;
	}

}
