package com.proem.exm.service.wholesaleGroupPurchase.query;

import java.util.List;
import java.util.Map;

import com.proem.exm.entity.wholesaleGroupPurchase.WholesaleQuery;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 团购销售查询
 * @author ZuoYM 
 * @com proem
 */
public interface WGPQueryService extends BaseService {

	/**
	 * 商品查询
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	DataGrid getGoodsQuery(Page page, Object obj) throws Exception;

	/**
	 * 类别查询
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	DataGrid getClassifyQueryGoods(Page page, Object obj) throws Exception;

	/**
	 * 明细查询
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	DataGrid getDetailQuery(Page page, Object obj) throws Exception;

	/**
	 * 客户查询
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	DataGrid getCustomerQuery(Page page, Object obj) throws Exception;

	/**
	 * 客户-商品销售查询
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	DataGrid getCustAndGoodsQuery(Page page, Object obj) throws Exception;

	/**
	 * 业务员-客户查询
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	DataGrid getCustAndCreQuery(Page page, Object obj) throws Exception;

	/**
	 * 品牌销售查询
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	DataGrid getBrandQuery(Page page, Object obj) throws Exception;

	/**
	 * 销售单据查询
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	DataGrid getDocumentQuery(Page page, Object obj) throws Exception;

	/**
	 * 客户-商品销售明细查询
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	DataGrid getCAGDetailQuery(Page page, Object obj) throws Exception;

	/**
	 * 区域销售查询
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	DataGrid getAreaQueryGoods(Page page, Object obj) throws Exception;

	/**
	 * 商品毛利查询
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	DataGrid getGProfitQuery(Page page, Object obj) throws Exception;

	/**
	 * 类别毛利查询
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	DataGrid getCProfitQuery(Page page, Object obj) throws Exception;

	/**
	 * 品牌毛利查询
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	DataGrid getBProfitQuery(Page page, Object obj) throws Exception;

	/**
	 * 客户毛利查询
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	DataGrid getCustProfitQuery(Page page, Object obj) throws Exception;

	/**
	 * 分店/仓库毛利查询
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	DataGrid getBranchProfitQuery(Page page, Object obj) throws Exception;

	/**
	 * 区域毛利查询
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	DataGrid getAreaProfitQuery(Page page, Object obj) throws Exception;

	/**
	 * 业务员毛利查询
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	DataGrid getCreProfitQuery(Page page, Object obj) throws Exception;

	/**
	 * 单据毛利查询
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	DataGrid getDocuProfitQuery(Page page, Object obj) throws Exception;

	/**
	 * 日毛利查询
	 * @param page
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	DataGrid getDateProfitQuery(Page page, Object obj) throws Exception;
	
}
