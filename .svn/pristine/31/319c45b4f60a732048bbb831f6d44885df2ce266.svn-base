package com.proem.exm.dao.impl.settlement;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cisdi.ctp.datamng.impl.BaseDataMngImpl;
import com.proem.exm.dao.settlement.SupplierSettleOrderDao;
import com.proem.exm.utils.Page;
/**
 * 供应商结算信息数据访问层实体类
 * @author myseft
 *
 */
@Repository("SupplierSettleOrderDao")
public class SupplierSettleOrderDaoImpl extends BaseDataMngImpl implements SupplierSettleOrderDao{

	@Override
	public List<Map<String, Object>> getObjPagedList(Page page)
			throws Exception {
		return this.querySql(Page.getPagedSQL(page.getSql(), page.getPage(),page.getRows()), null);
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
