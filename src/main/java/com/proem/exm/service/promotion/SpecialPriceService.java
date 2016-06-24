package com.proem.exm.service.promotion;

import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.salesPromotion.ZcSalesPromotion;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

public interface SpecialPriceService extends BaseService{

	DataGrid getPagedDataGridObj(Object obj, Page page) throws Exception;

	DataGrid getGoodsInfo(Page page, GoodsFile goodsFile) throws Exception;

	DataGrid getPagedDataGridObj(Page page, ZcSalesPromotion zcSalesPromotion) throws Exception;

	

}
