package com.proem.exm.service.order;

import javax.servlet.http.HttpServletRequest;

import com.proem.exm.entity.order.ZcOrder;
import com.proem.exm.entity.order.ZcOrderDigits;
import com.proem.exm.service.BaseService;

public interface OrdersDigitsService extends BaseService {
	/**
	 * 查询原金额，算出不同精度的数据
	 */
	public void getDigitsDecimalFormat(HttpServletRequest request,ZcOrderDigits zcOrderDigits);

	/**
	 * 将算出的数据放入表内
	 */
	public void updateDigitsObj(Object obj);
}
