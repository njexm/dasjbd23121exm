package com.proem.exm.service.impl.settlement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.settlement.SupplierSettleOrderDao;
import com.proem.exm.entity.settlement.SupplierSettletOrder;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.settlement.SupplierSettletOrderService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 供应商结算信息业务层实体类
 * @author myseft
 *
 */
@Service("SupplierSettletOrderService")
public class SupplierSettletOrderServiceImpl extends BaseServiceImpl implements SupplierSettletOrderService {
	
	@Autowired
	private SupplierSettleOrderDao supplierSettleOrderDao;
	
	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select a.*,b.name as customername from Zc_supplier_settle_order a"
				+ " left join zc_customer_info b on a.customer_id=b.id where 1=1";
		sql += joinCondition(obj);
		sql += " order by a.createTime desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = supplierSettleOrderDao
				.getObjPagedList(page);
		long total = supplierSettleOrderDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}
	
	private String joinCondition(Object obj) {
		// TODO Auto-generated method stub
		String conditions = "";
		return conditions;
	}
	private String joinConditionItem(Object obj) {
		SupplierSettletOrder supplierSettletOrder = (SupplierSettletOrder) obj;
		String conditions = "";
		if (StringUtil.validate(supplierSettletOrder.getCustomer().getId())) {
			conditions += " and c.customer_id = '"
					+ supplierSettletOrder.getCustomer().getId() + "'";
		}
		return conditions;
	}

	@Override
	public DataGrid getItemDataGridObj(Page page, Object obj) throws Exception {
		SupplierSettletOrder supplierSettletOrder = (SupplierSettletOrder) obj;
		String sql = "select a.* from Zc_supplier_settle_orderitems a  where a.id is null ";
		if (StringUtil.validate(supplierSettletOrder.getCustomer().getId())) {
			sql = "	select * from "
					+ "(select a.*,b.name as customername,to_number(a.unpaid_money)as unpaid from Zc_supplier_settle_orderitems a "
					+ " left join zc_customer_info b on a.provider_id=b.id) c "
					+ " where c.unpaid <> 0 and c.money != '1' and c.supplier_settle_order_id is null ";
			sql += joinConditionItem(obj);
			sql += "order by c.createtime asc";
		}
		page.setSql(sql);
		List<Map<String, Object>> rows = supplierSettleOrderDao
				.getObjPagedList(page);
		long total = supplierSettleOrderDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public DataGrid getEdit(Page page, String id, Object obj) throws Exception {
		SupplierSettletOrder supplierSettletOrder = (SupplierSettletOrder) obj;
		String sql = "select a.* from Zc_supplier_settle_orderitems a  where a.id is null ";
		if (StringUtil.validate(supplierSettletOrder.getCustomer().getId())) {
			sql = "	select * from "
					+ "(select a.*,b.name as customername,to_number(a.unpaid_money)as unpaid from Zc_supplier_settle_orderitems a "
					+ " left join zc_customer_info b on a.customer_id=b.id) c "
					+ " where c.unpaid != 0 and c.money != '0' and c.supplier_settle_order_id = '"
					+ id + "'";
			sql += joinConditionItem(obj);
			sql += "order by c.createtime asc";
		}
		page.setSql(sql);
		List<Map<String, Object>> rows = supplierSettleOrderDao
				.getObjPagedList(page);
		long total = supplierSettleOrderDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public DataGrid getDetail(Page page, String id, Object obj)
			throws Exception {
		SupplierSettletOrder supplierSettletOrder = (SupplierSettletOrder) obj;
		String sql = "select a.* from Zc_supplier_settle_orderitems a  where a.id is null ";
		if (StringUtil.validate(supplierSettletOrder.getCustomer().getId())) {
			sql = "	select * from "
					+ "(select a.*,b.name as customername,to_number(a.unpaid_money)as unpaid from Zc_supplier_settle_orderitems a "
					+ " left join zc_customer_info b on a.customer_id=b.id) c "
					+ " where c.money != '0' and c.supplier_settle_order_id = '"
					+ id + "'";
			sql += joinConditionItem(obj);
			sql += "order by c.createtime asc";
		}
		page.setSql(sql);
		List<Map<String, Object>> rows = supplierSettleOrderDao
				.getObjPagedList(page);
		long total = supplierSettleOrderDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public DataGrid getSettlementObj(Page page, Object obj) throws Exception {
		SupplierSettletOrder supplierSettletOrder = (SupplierSettletOrder) obj;
		String sql = "select a.* from Zc_supplier_settle_orderitems a  where a.id is null ";
		if (StringUtil.validate(supplierSettletOrder.getCustomer().getId())) {
			sql = "select * from(select a.*,b.name as customername,to_number(a.unpaid_money)as unpaid from Zc_supplier_settle_orderitems a "
					+ "left join zc_customer_info b on a.customer_id=b.id left join Zc_supplier_settlement d on d.id=a.supplier_settle_order_id where d.audit_status=2) c "
					+ "where c.money != '0' and c.customer_id ='"
					+ supplierSettletOrder.getCustomer().getId() + "' ";
			sql += " order by c.createtime asc";
		}
		page.setSql(sql);
		List<Map<String, Object>> rows = supplierSettleOrderDao
				.getObjPagedList(page);
		long total = supplierSettleOrderDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}
	
}
