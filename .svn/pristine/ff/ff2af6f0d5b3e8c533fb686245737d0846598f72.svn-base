package com.proem.exm.service.impl.wholesaleGroupPurchase.order;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.wholesaleGroupPurchase.WholesaleGroupPurchaseOrderItemDao;
import com.proem.exm.entity.wholesaleGroupPurchase.WholesaleGroupPurchaseOrderItem;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.wholesaleGroupPurchase.order.WholesaleGroupPurchaseOrderItemService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

@Service("wholeGroupItemService")
public class WholesaleGroupPurchaseOrderItemServiceImpl extends BaseServiceImpl
		implements WholesaleGroupPurchaseOrderItemService {

	@Autowired
	private WholesaleGroupPurchaseOrderItemDao wholeGroupItemDao;

	@Override
	public DataGrid getPagedDataGridObj(Page page) throws Exception {
		String sql = "select a.id, b.SERIALNUMBER,b.GOODS_NAME as GOODSNAME, b.GOODS_SPECIFICATIONS as SPECIFICATIONS,b.GOODS_UNIT as UNIT, "
				+ " a.ORDER_PRICE as GOODSPRICE,a.NUMS,a.RATE,a.RATE_AMOUNT as RATEAMOUNT,a.WITHOUT_AMOUNT  as PRICESUM "
				+ " from ZC_WHOLE_ORDER_ITEM a left join ZC_GOODS_MASTER b on a.GOODS_FILE_ID = b.id where 1=1 and a.order_id is null ";
		page.setSql(sql);
		sql += " order by b.SERIALNUMBER asc ";
		List<Map<String, Object>> rows = wholeGroupItemDao
				.getObjPagedList(page);
		Long total = wholeGroupItemDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public DataGrid getPagedDataGridObj(Object obj, Page page) throws Exception {
		String sql = "select a.id, b.SERIALNUMBER,b.GOODS_NAME as GOODSNAME, b.GOODS_SPECIFICATIONS as SPECIFICATIONS,b.GOODS_UNIT as UNIT, "
				+ " a.ORDER_PRICE as GOODSPRICE,a.NUMS,a.RATE,a.RATE_AMOUNT as RATEAMOUNT,a.WITHOUT_AMOUNT  as PRICESUM "
				+ " from ZC_WHOLE_ORDER_ITEM a left join ZC_GOODS_MASTER b on a.GOODS_FILE_ID = b.id where 1=1 ";
		sql += JoinCondition(obj);
		sql += " order by b.SERIALNUMBER asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = wholeGroupItemDao
				.getObjPagedList(page);
		Long total = wholeGroupItemDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	private String JoinCondition(Object obj) {
		WholesaleGroupPurchaseOrderItem item = (WholesaleGroupPurchaseOrderItem) obj;
		String conditions = "";
		if (StringUtil.validate(item.getOrderId())) {
			conditions += " and a.order_id = '" + item.getOrderId() + "'";
		}
		if (StringUtil.validate(item.getGoodsFile())) {
			if (StringUtil.validate(item.getGoodsFile().getSerialNumber())) {
				conditions += " and b.serialNumber like '%"
						+ item.getGoodsFile().getSerialNumber() + "%'";
			}
		}
		return conditions;
	}

}
