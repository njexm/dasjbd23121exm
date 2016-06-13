package com.proem.exm.service.wholesaleGroupPurchase.wholesaleGroupPurchaseReturn.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.wholesaleGroupPurchase.wholesaleGroupPurchaseReturn.WGPurchaseReturnItemDao;
import com.proem.exm.service.wholesaleGroupPurchase.wholesaleGroupPurchaseReturn.WGPurchaseReturnItemService;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 团购退货单明细
 * @author ZuoYM 
 * @com proem
 */
@Service("WGPurchaseReturnItemService")
public class WGPurchaseReturnItemServiceImpl extends BaseServiceImpl implements WGPurchaseReturnItemService {
	@Autowired
	private WGPurchaseReturnItemDao wGPurchaseReturnItemDao;

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select * from ZC_WGPURCHASE_RETURN_ITEM  where 1=1 ";
		sql += joinCondition(obj);
		sql+=" order by createtime desc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPurchaseReturnItemDao.getObjPagedList(page);
		Long total = wGPurchaseReturnItemDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public List<Map<String, Object>> getObjList(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select * from ZC_WGPURCHASE_RETURN_ITEM where 1=1 ";
//			WGPurchaseReturnItem wGPurchaseReturnItem = (WGPurchaseReturnItem) obj;
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询条件拼接
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj) {
//		WGPurchaseReturnItem wGPurchaseReturnItem = (WGPurchaseReturnItem) obj;
		String conditions = "";

		return conditions;
	}

}
