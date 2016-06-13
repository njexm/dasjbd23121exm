package com.proem.exm.controller.order;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdi.ctp.utils.common.UuidUtils;
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.Customer;
import com.proem.exm.entity.Orders;
import com.proem.exm.service.order.CustomerService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.StringUtil;

@Controller
//(模块名/类名)-小写
@RequestMapping("order/customer")
public class CustomerController extends BaseController{

	@Autowired
	private CustomerService customerService;
	
	// 初始化跳转页面
	@RequestMapping("init")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = createLayoutView("order/customer/customer_list.vm");
		return view;
	}
	
	// 保存数据
		@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json")
		@ResponseBody
		public AjaxResult save(HttpServletRequest request,
				HttpServletResponse response, Customer customer) {
			AjaxResult ajaxResult = null;
			try {
				
				String id = customer.getId();
				if (StringUtil.validate(id)) {
					customer.setId(id);
					customerService.updateObj(customer);
				} else {
					id = UuidUtils.getUUID();
					customer.setId(id);
					customer.setAccount("zhangsan001");
					customer.setNickName("zhangsan");
					customer.setMobilePhone("13609876541");
					Set<Orders> orders = new HashSet<Orders>();
					Orders order1 = new Orders();
					order1.setCustomer(customer);
					order1.setId(UuidUtils.getUUID());
					order1.setSku("sku000000001");
					order1.setOrderAddress("安徽省");
					order1.setOrderNum("aaa000000001");
					orders.add(order1);
					customer.setOrders(orders);
					customerService.saveObj(customer);
				}
				ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
						AjaxResult.INFO);
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
						AjaxResult.INFO);
			}
			return ajaxResult;
		}
}
