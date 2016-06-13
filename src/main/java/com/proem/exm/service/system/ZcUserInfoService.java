package com.proem.exm.service.system;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.system.ZcZoning;
import com.proem.exm.entity.wholesaleGroupPurchase.customer.CustomerInfo;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.SharePager;

public interface ZcUserInfoService extends BaseService {

	public DataGrid getUserInfoDataGrid(ZcUserInfo zcUserInfo,
			SharePager sharePager);

	public ZcUserInfo getUserInfoById(String id);

	public boolean saveOrUpdate(ZcUserInfo zcUserInfo);

	public boolean deleteUserInfo(String ids);

	public String getUserInfoNameById(String id);

	public List<Map<String, Object>> getUserInfoList();

	/**
	 * 分页查询数据
	 * 
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception;

	/**
	 * 按用户名查询
	 * 
	 * @param page
	 * @param obj
	 * @param providerId
	 * @return
	 * @throws Exception
	 */
	public DataGrid getPagedDataOfGridObj(Page page, Object obj)
			throws Exception;

	List<Map<String, Object>> getObjList1(Object obj);

	List<Map<String, Object>> getObjList2(Object obj);
	
	/**
	 * 获取用户信息列表
	 * @return
	 */
	public List<ZcUserInfo> getAllObject();

}
