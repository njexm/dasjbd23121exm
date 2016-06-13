package com.proem.exm.service.wholesaleGroupPurchase.order;

import java.util.List;
import java.util.Map;

import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 批发团购主表service
 * 
 * @author xuehr
 * 
 */
public interface WholesaleGroupPurchaseOrderService extends BaseService {

	/**
	 * 分页查询数据
	 * 
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObj(Page page, Object obj,
			String startDate, String endDate, String state) throws Exception;

	/**
	 * 无分页条件查询所有
	 * 
	 * @param obj
	 * @return
	 */
	public List<Map<String, Object>> getObjList(Object obj);

}
