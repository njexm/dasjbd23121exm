package com.proem.exm.dao.impl.settlement;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cisdi.ctp.datamng.impl.BaseDataMngImpl;
import com.proem.exm.dao.settlement.SupplierSettlementDao;
import com.proem.exm.utils.Page;

/**
 * 供应商结算分页数据接口实现类
 * @author songcj
 * 2015年12月1日 下午6:19:18 
 */
@Repository("SupplierSettlementDao")
public class SupplierSettlementDaoImpl extends BaseDataMngImpl implements SupplierSettlementDao{
	
	@Override
	public List<Map<String, Object>> getObjPagedList(Page page)
			throws Exception {
		List<Map<String, Object>> list = this.querySql(Page.getPagedSQL(page.getSql(), page.getPage(),page.getRows()), null);
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
