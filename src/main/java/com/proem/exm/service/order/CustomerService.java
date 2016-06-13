package com.proem.exm.service.order;

import com.proem.exm.service.BaseService;

public interface CustomerService extends BaseService{

	/**
	 * 修改已经存在的数据  - 单纯单表操作
	 * @param Orders
	 */
	public void updateObj(Object obj);
	
}
