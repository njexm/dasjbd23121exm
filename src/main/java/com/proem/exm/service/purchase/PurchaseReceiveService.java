package com.proem.exm.service.purchase;

import java.util.List;
import java.util.Map;

import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

public interface PurchaseReceiveService extends BaseService {

	public DataGrid getPagedDataGridObj(Page page, Object obj, String state)
			throws Exception;

	public List<Map<String, Object>> getClassInfoList();

	public void updateObj(Object obj);

	public DataGrid getPagedDataGridGoods(Page page, Object obj,
			String serialNumber) throws Exception;

	DataGrid getPurchaseAddGoods(Page page, Object obj, CtpUser ctpUser,
			String providerId) throws Exception;

	// public List<Map<String, Object>> getGoodsItem(String purchaseOrderId);

}