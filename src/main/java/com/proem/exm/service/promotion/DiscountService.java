package com.proem.exm.service.promotion;

import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
/**
 * 折扣业务层接口
 * @author myseft
 *
 */

public interface DiscountService extends BaseService{
	
	DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception;
	
	DataGrid getPagedDataGridBranch(Page page, Object obj) throws Exception;
	
	DataGrid getPagedDataGridGoods(Page page, Object obj) throws Exception;
	
	DataGrid getPagedDataGridBrand(Page page, Object obj,String zcCodeScope) throws Exception;
	
	
	
	public DataGrid getPromotionAddGoods(Page page,String id, Object obj, CtpUser ctpUser)
			throws Exception;
	
	public DataGrid getPromotionEditGoods(Page page ,Object obj ) throws Exception;
	
	public DataGrid getPromotionDeailGoods(Page page ,Object obj ) throws Exception;
	
	
}
