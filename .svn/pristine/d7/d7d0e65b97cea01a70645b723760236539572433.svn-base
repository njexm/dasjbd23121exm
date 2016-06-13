package com.proem.exm.service.impl.settlement;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.settlement.SupplierCostDao;
import com.proem.exm.entity.settlement.SupplierCost;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.settlement.SupplierCostService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 供应商费用实现类
 * @author songcj
 * 2015年11月30日 上午11:17:47 
 */
@Service("SupplierCostService")
public class SupplierCostServiceImpl extends BaseServiceImpl implements SupplierCostService{

	@Autowired 
	private SupplierCostDao supplierCostDao;
	
	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select a.*,b.provider_nickname as providername from Zc_supplier_cost a"
				+" left join zc_provider_info b on a.provider_id=b.id where 1=1";
		sql += joinCondition(obj);
		sql += " order by a.createtime desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = supplierCostDao.getObjPagedList(page);
		long total = supplierCostDao.getObjListCount(page);
		return  new DataGrid(total, rows);
	}

	private String joinCondition(Object obj) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SupplierCost supplierCost = (SupplierCost) obj;
		String conditions = "";
		if (StringUtil.validate(supplierCost.getProvider())) {
			if (StringUtil.validate(supplierCost.getProvider().getId())){
				conditions += " and a.provider_id='" + supplierCost.getProvider().getId() + "'";
			}
		}
		if (StringUtil.validate(supplierCost.getCreateTime())) {
			conditions += " and a.createtime>=TO_DATE('"+ sdf.format(supplierCost.getCreateTime())+ "', 'YYYY-MM-DD HH24:MI:SS')";
		}
		if (StringUtil.validate(supplierCost.getUpdateTime())) {
			conditions += " and a.createtime<=TO_DATE('"+ sdf.format(supplierCost.getUpdateTime())+ "', 'YYYY-MM-DD HH24:MI:SS')";
		}
		if (supplierCost.getAuditStatus() != 0) {
			conditions += " and a.audit_status = '"+(supplierCost.getAuditStatus()-1)+"'";
		}
		return conditions;
	}

	/**
	 * 新增页面数据获取
	 */
	@Override
	public DataGrid getItemDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select a.* from Zc_supplier_cost_item a where a.supplier_cost_id is null order by a.createtime asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = supplierCostDao.getObjPagedList(page);
		long total = supplierCostDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public DataGrid getDetail(Page page, String id,Object obj) throws Exception {
		String sql ="select * from Zc_supplier_cost_item where supplier_cost_id = '"+id+"'";
		page.setSql(sql);
		List<Map<String, Object>> rows = supplierCostDao.getObjPagedList(page);
		Long total = supplierCostDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

}
