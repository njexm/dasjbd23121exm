package com.proem.exm.service.basic.adjustment;

import java.util.List;

import com.proem.exm.entity.basic.adjustment.AdjustmentDetail;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 商品调价单
 * 
 * @author ZuoYM
 * 
 */
public interface AdjustmentService extends BaseService {

	/**
	 * 修改表信息
	 * 
	 * @param Orders
	 */
	public void updateObj(Object obj);

	/**
	 * 分页查询数据
	 * 
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception;

	public List getObjList(GoodsFile goodsFile);

	public List getObjList_name(GoodsFile goodsFile);

	/**
	 * 分页查询数据
	 * 
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObjList(Page page, Object obj,
			String audflag) throws Exception;

	public DataGrid getPagedDataDetailGridObj(Page page,
			AdjustmentDetail adjustmentDetail);

	/**
	 * 
	 * @param adjustmentDetail
	 * @param id
	 * @return
	 */
	List getIdList(String name);

	DataGrid getChooseGoodsItems(Page page, Object obj) throws Exception;

}
