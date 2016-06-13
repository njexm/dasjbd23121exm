package com.proem.exm.dao.impl.testmodel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cisdi.ctp.datamng.impl.BaseDataMngImpl;
import com.proem.exm.dao.testmodel.TestDao;
import com.proem.exm.utils.Page;

@Repository("testDaoImpl")
public class TestDaoImpl extends BaseDataMngImpl implements TestDao {

	@Override
	public List<Map<String, Object>> getObjPagedList(Page page) {
		List<Map<String, Object>> list = null;
		try {
			list = this.querySql(Page.getPagedSQL(page.getSql(), page.getPage(), page.getRows()), null);
		} catch (Exception e) {
			list = null;
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Long getObjListCount(Page page) {
		Long count = 0L;
		List<Map<String, Object>> list = null;
		try {
			list = this.querySql(Page.getListCountSQL(page.getSql()), null);
			Map<String, Object> map = (Map<String, Object>) list.get(0);
			BigDecimal bd = (BigDecimal) map.get("COUNT");
			count = bd.longValue();
		} catch (Exception e) {
			list = null;
			e.printStackTrace();
		}
		return count;
	}
	
}
