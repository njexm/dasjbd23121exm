package com.proem.exm.dao.impl.basic;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cisdi.ctp.datamng.impl.BaseDataMngImpl;
import com.proem.exm.dao.basic.gooodsFile.GoodsFileDao;
import com.proem.exm.utils.Page;


/**
 * @author songcj
 *
 * 2015年10月20日下午2:07:59
 */
@Repository("GoodsFileDao")
public class GoodsFileDaoImpl extends BaseDataMngImpl implements GoodsFileDao {

	@Override
	public List<Map<String, Object>> getObjPagedList(Page page)
			throws Exception {
		List<Map<String, Object>> list = this.querySql(Page.getPagedSQL(page.getSql(), page.getPage(), page.getRows()), null);
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
