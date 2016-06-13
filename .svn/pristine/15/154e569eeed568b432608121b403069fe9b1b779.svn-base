package com.proem.exm.service.impl.purchase;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.purchase.PurchaseOrderGoodsItemsDao;
import com.proem.exm.entity.purchase.PurchaseOrderGoodsItems;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.purchase.PurchaseOrderGoodsItemsService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 采购订单商品集合业务逻辑访问实现类
 * 
 * @author Administrator
 * 
 */
@Service("purchaseOrderGoodsItemsService")
public class PurchaseOrderGoodsItemsServiceImpl extends BaseServiceImpl
		implements PurchaseOrderGoodsItemsService {
	@Autowired
	PurchaseOrderGoodsItemsDao purchaseOrderGoodsItemsDao;

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj) {
		PurchaseOrderGoodsItems orders = (PurchaseOrderGoodsItems) obj;
		String conditions = "";
		if (StringUtil.validate(orders.getPurchaseId())) {
			conditions += " and PURCHASEID = '" + orders.getPurchaseId() + "'";
		}
		if (StringUtil.validate(orders.getSerialNumber())) {
			conditions += " and SERIALNUMBER like '%"
					+ orders.getSerialNumber() + "%'";
		}
		return conditions;
	}

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		// String sql =
		// "select id,sum(ordernum) as ordernum,goods_name,sum(goods_price*ordernum) as totalprice,classify_name ,goods_unit,goods_specifications,goods_code,goods_price from "
		// +
		// "(select b.id,c.classify_name,a.ordernum,a.goodsprice,b.goods_name,b.goods_code,b.goods_unit,b.goods_specifications,b.goods_price "
		// + "from zc_purchase_order_items a "
		// + "left join zc_goods_master b on b.id=a.goodsfile_id "
		// + "left join zc_classify_info c on b.goods_class_id=c.id "
		// + "where 1=1)"
		// +
		// "group by goods_name,id,classify_name,goods_unit,goods_specifications,goods_price,goods_code";
		String sql = "select * from zc_purchase_order_items where 1=1";
		sql += joinCondition(obj);
		sql += " order by serialNumber asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = purchaseOrderGoodsItemsDao
				.getObjPagedList(page);
		Long total = purchaseOrderGoodsItemsDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 分页展示采购订单商品表中采购订单为空的数据
	 */
	@Override
	public DataGrid getPurchaseAddGoods(Page page, Object obj, CtpUser ctpUser,
			String providerId, String storehouseId) throws Exception {
		String sql = "select a.*,c.branch_id,c.store from zc_purchase_order_items a left join zc_goods_master b on b.id=a.goodsfile_id left join zc_storehouse c on c.goodsfile_id=b.id where a.PURCHASEID is null and CTPUSER_ID='"
				+ ctpUser.getId() + "'";
		sql += conditions(obj, providerId, storehouseId);
		sql += " order by a.serialNumber asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = purchaseOrderGoodsItemsDao
				.getObjPagedList(page);
		Long total = purchaseOrderGoodsItemsDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String conditions(Object obj, String providerId, String storehouseId) {
		String conditions = "";
		if (StringUtil.validate(storehouseId)) {
			conditions += " and c.branch_id= '" + storehouseId + "'";
		}
		if (StringUtil.validate(providerId)) {
			conditions += " and a.PROVIDERINFO_ID= '" + providerId + "'";
		}
		return conditions;
	}
}
