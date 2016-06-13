package com.proem.exm.service.impl.purchase;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.purchase.PurchaseReturnItemsDao;
import com.proem.exm.entity.purchase.PurchaseOrderGoodsItems;
import com.proem.exm.entity.purchase.PurchaseReturnItems;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.purchase.PurchaseReturnItemsService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 采购退货单逻辑业务实现类
 * 
 * @author Administrator
 * 
 */
@Service("purchaseReturnItemsService")
public class PurchaseReturnItemsServiceImpl extends BaseServiceImpl implements
		PurchaseReturnItemsService {
	@Autowired
	PurchaseReturnItemsDao purchaseReturnItemsDao;

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select a.*,a.RETURNNUM*a.GOODSPRICE as MONEY from zc_purchase_return_items a where 1=1";
		sql += joinCondition(obj);
		sql+=" order by a.serialnumber asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = purchaseReturnItemsDao
				.getObjPagedList(page);
		Long total = purchaseReturnItemsDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj) {
		PurchaseReturnItems purchaseReturnItems = (PurchaseReturnItems) obj;
		String conditions = " and RETURNID ='"
				+ purchaseReturnItems.getReturnId() + "'";
		if (StringUtil.validate(purchaseReturnItems.getSerialnumber())) {
			conditions += " and SERIALNUMBER like '%"
					+ purchaseReturnItems.getSerialnumber() + "%'";
		}
		return conditions;
	}

}
