package com.proem.exm.dao.settlement;

import java.util.List;
import java.util.Map;

import com.proem.exm.utils.Page;

/**
 * 供应商结算分页数据接口
 * @author songcj
 * 2015年12月1日 下午6:17:33 
 */
public interface SupplierSettlementDao {

	public List<Map<String, Object>> getObjPagedList(Page page)throws Exception;

	public Long getObjListCount(Page page) throws Exception;
}
