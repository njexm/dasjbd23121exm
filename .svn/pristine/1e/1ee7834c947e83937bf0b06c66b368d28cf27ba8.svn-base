package com.proem.exm.service.impl.purchase;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.purchase.PurchaseShoppingCartDao;
import com.proem.exm.entity.purchase.PurchaseOrder;
import com.proem.exm.entity.purchase.PurchaseShoppingCart;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.purchase.PurchaseShoppingCartService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 采购订单购物车业务逻辑实现类
 * 
 * @author Administrator
 * 
 */
@Service("purchaseShoppingCartService")
public class PurchaseShoppingCartServiceImpl extends BaseServiceImpl implements
		PurchaseShoppingCartService {
	@Autowired
	PurchaseShoppingCartDao purchaseShoppingCartDao;

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select * from ZC_PURCHASE_SHOPPINGCART where 1=1";
		sql += joinCondition(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = purchaseShoppingCartDao
				.getObjPagedList(page);
		Long total = purchaseShoppingCartDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj) {
		PurchaseShoppingCart purchaseShoppingCart = (PurchaseShoppingCart) obj;
		String conditions = "";
		if (StringUtil.validate(purchaseShoppingCart.getSerialNumber())) {
			conditions += " and SERIALNUMBER like '%"
					+ purchaseShoppingCart.getSerialNumber() + "%'  ";
		}
		return conditions;
	}

}
