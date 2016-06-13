package com.proem.exm.service.impl.settlement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.settlement.SupplierSettlementDao;
import com.proem.exm.entity.purchase.PurchaseSearch;
import com.proem.exm.entity.settlement.SupplierQuery;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.settlement.SupplierQueryOrdersService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 供应商账款往来业务层实现类
 * @author myseft
 *
 */
@Service("supplierQueryOrderService")
public class SupplierQueryOrdersServiceImpl extends BaseServiceImpl implements SupplierQueryOrdersService {
	
	@Autowired
	private SupplierSettlementDao supplierSettlementDao ;
	
	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj
			) throws Exception {
		String sql = "SELECT Z.ID  ,Z.CODE ,P.PROVIDER_NICKNAME AS PROVIDER_NICKNAME,"
					+ "Z.ACTUAL_MONEY,Z.UNPAID_MONEY, Z.PAID_MONEY ,"
					+ "Z.FAVORABLE_MONEY ,Z.DISCOUNT_MONEY,Z.PAYABLE_MONEY,"
					+ "Z.AGREED_TIME AS NEW_TIME,Z.TAX ,Z.REMARKS "
					+ "FROM ZC_SUPPLIER_SETTLEMENT_ITEM Z "
					+ "LEFT JOIN ZC_PROVIDER_INFO P  ON Z.PROVIDER_ID = P.ID WHERE 1=1 ";
		sql += conditions(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = supplierSettlementDao.getObjPagedList(page);
		Long total = supplierSettlementDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}
	
	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String conditions(Object obj) {
		SupplierQuery supplierQuery = (SupplierQuery) obj;
		String conditions = "";
		if (StringUtil.validate(supplierQuery.getStartTime())
				&& StringUtil.validate(supplierQuery.getEndTime())) {
			conditions += " and createtime between to_date('"
					+ supplierQuery.getStartTime()
					+ "','yyyy-mm-dd HH24:mi:ss') and to_date('"
					+ supplierQuery.getEndTime()
					+ "','yyyy-mm-dd HH24:mi:ss')";
		}
		if (StringUtil.validate(supplierQuery.getProviderInfo())) {
			if (StringUtil.validate(supplierQuery.getProviderInfo().getId())) {
				conditions += " and P.ID='"
						+ supplierQuery.getProviderInfo().getId() + "'";
			}
		}
		
		return conditions;
	}

}
