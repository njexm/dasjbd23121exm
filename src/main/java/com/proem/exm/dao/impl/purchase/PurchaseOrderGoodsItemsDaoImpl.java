package com.proem.exm.dao.impl.purchase;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cisdi.ctp.datamng.impl.BaseDataMngImpl;
import com.proem.exm.dao.purchase.PurchaseOrderGoodsItemsDao;
import com.proem.exm.utils.Page;

/**
 * 采购订单商品集合分页实现类
 * 
 * @author Administrator
 * 
 */
@Repository("purchaseOrderGoodsItemsDao")
public class PurchaseOrderGoodsItemsDaoImpl extends BaseDataMngImpl implements
		PurchaseOrderGoodsItemsDao {

	@Override
	public List<Map<String, Object>> getObjPagedList(Page page)
			throws Exception {
		List<Map<String, Object>> list = this
				.querySql(
						Page.getPagedSQL(page.getSql(), page.getPage(),
								page.getRows()), null);
		return list;
	}

	@Override
	public Long getObjListCount(Page page) throws Exception {
		Long count = 0L;
		List<Map<String, Object>> list = null;
		list = this.querySql(Page.getListCountSQL(page.getSql()), null);
		Map<String, Object> map = (Map<String, Object>) list.get(0);
		BigDecimal bd = (BigDecimal) map.get("COUNT");
		count = bd.longValue();
		return count;
	}

}
