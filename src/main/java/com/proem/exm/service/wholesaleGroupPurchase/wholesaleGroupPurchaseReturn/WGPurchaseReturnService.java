package com.proem.exm.service.wholesaleGroupPurchase.wholesaleGroupPurchaseReturn;

import java.util.List;
import java.util.Map;

import com.proem.exm.entity.wholesaleGroupPurchase.wholesaleGroupPurchaseReturn.WGPurchaseReturn;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 团购退货单
 * @author ZuoYM 
 * @com proem
 */
public interface WGPurchaseReturnService extends BaseService {
	
	/**
	 * 分页查询数据
	 * 
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception;

	/**
	 * 无分页条件查询所有
	 * 
	 * @param obj
	 * @return
	 */
	public List<Map<String, Object>> getObjList(Object obj);
	
	/**
	 * 获取团购退货单信息列表
	 * @return
	 */
	public List<WGPurchaseReturn> getAllObject();

	/**
	 * 团购退货单明细查询
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public DataGrid getWGPurchaseReturnItem(Page page,
			Object obj, String serialNumber) throws Exception;
	
}
