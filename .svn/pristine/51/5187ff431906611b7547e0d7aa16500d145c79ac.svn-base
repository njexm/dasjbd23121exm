package com.proem.exm.service.impl.settlement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.settlement.SupplierSettlementDao;
import com.proem.exm.entity.settlement.SupplierSettlement;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.settlement.SupplierSettlementService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 供应商结算实现类
 * 
 * @author songcj 2015年12月1日 下午8:03:30
 */
@Service("SupplierSettlementService")
public class SupplierSettlementServiceImpl extends BaseServiceImpl implements
		SupplierSettlementService {

	@Autowired
	private SupplierSettlementDao supplierSettlementDao;

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select a.*,b.provider_nickname as providername from Zc_supplier_settlement a"
				+ " left join zc_provider_info b on a.provider_id=b.id where 1=1";
		sql += joinCondition(obj);
		sql += " order by a.createTime desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = supplierSettlementDao
				.getObjPagedList(page);
		long total = supplierSettlementDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	private String joinCondition(Object obj) {
		// TODO Auto-generated method stub
		String conditions = "";
		return conditions;
	}

	private String joinConditionItem(Object obj) {
		SupplierSettlement supplierSettlement = (SupplierSettlement) obj;
		String conditions = "";
		if (StringUtil.validate(supplierSettlement.getProvider().getId())) {
			conditions += " and c.provider_id = '"
					+ supplierSettlement.getProvider().getId() + "'";
		}
		return conditions;
	}

	@Override
	public DataGrid getItemDataGridObj(Page page, Object obj) throws Exception {
		SupplierSettlement supplierSettlement = (SupplierSettlement) obj;
		String sql = "select a.* from Zc_supplier_settlement_item a  where a.id is null ";
		if (StringUtil.validate(supplierSettlement.getProvider().getId())) {
			sql = "	select * from "
					+ "(select a.*,b.provider_nickname as providername,to_number(a.unpaid_money)as unpaid from Zc_supplier_settlement_item a "
					+ " left join zc_provider_info b on a.provider_id=b.id) c "
					+ " where c.unpaid <> 0 and c.money != '1' and c.supplier_settlement_id is null ";
			sql += joinConditionItem(obj);
			sql += "order by c.createtime asc";
		}
		page.setSql(sql);
		List<Map<String, Object>> rows = supplierSettlementDao
				.getObjPagedList(page);
		long total = supplierSettlementDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public DataGrid getEdit(Page page, String id, Object obj) throws Exception {
		SupplierSettlement supplierSettlement = (SupplierSettlement) obj;
		String sql = "select a.* from Zc_supplier_settlement_item a  where a.id is null ";
		if (StringUtil.validate(supplierSettlement.getProvider().getId())) {
			sql = "	select * from "
					+ "(select a.*,b.provider_nickname as providername,to_number(a.unpaid_money)as unpaid from Zc_supplier_settlement_item a "
					+ " left join zc_provider_info b on a.provider_id=b.id) c "
					+ " where c.unpaid != 0 and c.money != '0' and c.supplier_settlement_id = '"
					+ id + "'";
			sql += joinConditionItem(obj);
			sql += "order by c.createtime asc";
		}
		page.setSql(sql);
		List<Map<String, Object>> rows = supplierSettlementDao
				.getObjPagedList(page);
		long total = supplierSettlementDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public DataGrid getDetail(Page page, String id, Object obj)
			throws Exception {
		SupplierSettlement supplierSettlement = (SupplierSettlement) obj;
		String sql = "select a.* from Zc_supplier_settlement_item a  where a.id is null ";
		if (StringUtil.validate(supplierSettlement.getProvider().getId())) {
			sql = "	select * from "
					+ "(select a.*,b.provider_nickname as providername,to_number(a.unpaid_money)as unpaid from Zc_supplier_settlement_item a "
					+ " left join zc_provider_info b on a.provider_id=b.id) c "
					+ " where c.money != '0' and c.supplier_settlement_id = '"
					+ id + "'";
			sql += joinConditionItem(obj);
			sql += "order by c.createtime asc";
		}
		page.setSql(sql);
		List<Map<String, Object>> rows = supplierSettlementDao
				.getObjPagedList(page);
		long total = supplierSettlementDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public DataGrid getSettlementObj(Page page, Object obj) throws Exception {
		SupplierSettlement supplierSettlement = (SupplierSettlement) obj;
		String sql = "select a.* from Zc_supplier_settlement_item a  where a.id is null ";
		if (StringUtil.validate(supplierSettlement.getProvider().getId())) {
			sql = "select * from(select a.*,b.provider_nickname as providername,to_number(a.unpaid_money)as unpaid from Zc_supplier_settlement_item a "
					+ "left join zc_provider_info b on a.provider_id=b.id left join Zc_supplier_settlement d on d.id=a.supplier_settlement_id where d.audit_status=2) c "
					+ "where c.money != '0' and c.provider_id ='"
					+ supplierSettlement.getProvider().getId() + "' ";
			sql += " order by c.createtime asc";
		}
		page.setSql(sql);
		List<Map<String, Object>> rows = supplierSettlementDao
				.getObjPagedList(page);
		long total = supplierSettlementDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

}
