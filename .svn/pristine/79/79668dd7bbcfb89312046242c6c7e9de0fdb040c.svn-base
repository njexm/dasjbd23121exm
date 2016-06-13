package com.proem.exm.controller.system;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdi.ctp.auth.po.Role;
import com.cisdi.ctp.auth.po.User2Role;
import com.cisdi.ctp.utils.common.StringUtils;
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.order.ZcOrder;
import com.proem.exm.entity.purchase.PurchaseOrder;
import com.proem.exm.entity.purchase.PurchaseReceive;
import com.proem.exm.entity.purchase.PurchaseReturn;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.warehouse.Sorte;
import com.proem.exm.entity.warehouse.ZcCheckDifference;
import com.proem.exm.entity.warehouse.ZcStoreChange;
import com.proem.exm.entity.warehouse.ZcSwitchhouse;
import com.proem.exm.entity.warehouse.ZcWarehouse;
import com.proem.exm.entity.warehouse.process.ProcessGoods;
import com.proem.exm.service.order.OrdersService;
import com.proem.exm.service.system.LogManageService;
import com.proem.exm.service.system.ZcZoningService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.CronExpConversion;
import com.proem.exm.utils.JdbcUtil;
import com.proem.exm.utils.UpdateNotificationInterval;

@Controller
@RequestMapping("system")
public class SystemController extends BaseController {
	@Autowired
	OrdersService ordersService;
	@Autowired
	ZcZoningService zcZoningService;
	@Autowired
	LogManageService logManageService;

	@InitBinder("zcOrder")
	public void initUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcOrder.");
	}

	@InitBinder("zcOrder1")
	public void zcOrder1(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcOrder1.");
	}

	// 打开新增页面
	@RequestMapping("gotoAddOrders")
	public ModelAndView gotoAddOrders(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ZcOrder zcOrder = Constant.ZC_ORDER;
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("HH:mm:ss");
		if (zcOrder.getCreateTime() != null) {
			String timeStart = sdf.format(zcOrder.getCreateTime());
			model.addAttribute("timeOrder", timeStart);
		} else {
			model.addAttribute("timeOrder", "08:00:00");
		}
		if (zcOrder.getOrderNum() != null) {
			model.addAttribute("timeHz", zcOrder.getOrderNum());
		} else {
			model.addAttribute("timeHz", "1");
		}
		if (zcOrder.getUpdateTime() != null) {
			String timeEnd = sdf.format(zcOrder.getUpdateTime());
			model.addAttribute("timeEnd", timeEnd);
		} else {
			model.addAttribute("timeEnd", "08:00:00");
		}
		if (zcOrder.getOrderDate() != null) {
			String timeOrder = sdf.format(zcOrder.getOrderDate());
			model.addAttribute("timeStart", timeOrder);
		} else {
			model.addAttribute("timeStart", "08:00:00");
		}
		model.addAttribute("zcOrder", zcOrder);
		ModelAndView view = createIframeView("order/orders/system_orderTimeSet");
		return view;
	}

	// 设置定时任务时间
	@RequestMapping(value = "setOrderTime", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult pushGetInfo(@ModelAttribute ZcOrder zcOrder,
			ZcOrder zcOrder1, HttpServletRequest request,
			HttpServletResponse response) throws SQLException {
		AjaxResult ajaxResult = null;
		Connection conn = JdbcUtil.getConnection();
		String cronExpression = "";
		try {
			// zcZoningService.updateObj(zcOrder);
			cronExpression = CronExpConversion.convertDateToCronExp(zcOrder);
			UpdateNotificationInterval.updateNotificationInterval(
					"DailyTaskCronTriggerBean", "DailyTaskCronTriggerBean",
					cronExpression);
			logManageService.insertLog(request, "设置了订单拉取定时任务", "定时任务");
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			conn.close();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		} finally {
			conn.close();
		}
		return ajaxResult;
	}

	/**
	 * 根绝登陆人获取待办事项
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "waittooDeal", method = RequestMethod.GET, produces = "application/jso;charset=UTF-8")
	@ResponseBody
	public String waittooDeal(HttpServletRequest request,
			HttpServletResponse response) {
		String result = "";
		CtpUser user = (CtpUser) request.getSession().getAttribute("user");
		String userId = user.getId();
		// 根据当前登录用户获取角色并展示出待办事项
		List<User2Role> user2RoleList = ordersService.getListByObj(
				User2Role.class, "LEFTID='" + userId + "'");
		if (user2RoleList != null && user2RoleList.size() > 0) {
			User2Role user2Role = user2RoleList.get(0);
			String roleId = user2Role.getRightId();
			Role role = new Role();
			role = (Role) ordersService.getObjById(roleId, "Role");
			String roleCode = role.getDescription();
			// 如果为采购审核员
			result = getPurchaseOrderCount(roleCode, result);
			result = getWareHouseCount(roleCode, result);
			result = getWorkCount(roleCode, result);
		}
		if ("".equals(result)) {
			result = "暂无待办事项";
		}
		return result;
	}

	/**
	 * 获取采购管理中的条数
	 * 
	 * @param roleCode
	 * @return
	 */
	private String getPurchaseOrderCount(String roleCode, String result) {
		if ("CGSHY".equals(roleCode) || "ADMIN001".equals(roleCode)) {
			// 得到采购订单待审核条数
			Long countOrder = ordersService.getCountByObj(PurchaseOrder.class,
					"PURCHASE_STATE='1'");
			// 得到采购收货单待审核条数
			Long countReceive = ordersService.getCountByObj(
					PurchaseReceive.class, "AUDIT_STATUS='1'");
			// 得到采购退货单待审核条数
			Long countReturn = ordersService.getCountByObj(
					PurchaseReturn.class, "STATUE='1'");
			if (countOrder != null && countOrder > 0) {
				if (StringUtils.isBlank(result)) {
					result = "&nbsp;&nbsp;&nbsp;· <a href='purchase/purchaseOrder/init'><span style='color:blue'>您有"
							+ countOrder + "条采购订单需要审核 </span></a></br>";
				} else {
					result += "&nbsp;&nbsp;&nbsp;· <a href='purchase/purchaseOrder/init'><span style='color:blue'>您有"
							+ countOrder + "条采购订单需要审核 </span></a></br>";
				}
			}
			if (countReceive != null && countReceive > 0) {
				if (StringUtils.isBlank(result)) {
					result = "&nbsp;&nbsp;&nbsp;· <a href='purchaseReceive/purchaseReceiveDo/init'><span style='color:blue'>您有"
							+ countReceive + "条采购收货单需要审核</span></a></br>";
				} else {
					result += "&nbsp;&nbsp;&nbsp;· <a href='purchaseReceive/purchaseReceiveDo/init'><span style='color:blue'>您有"
							+ countReceive + "条采购收货单需要审核</span></a></br>";
				}
			}
			if (countReturn != null && countReturn > 0) {
				if (StringUtils.isBlank(result)) {
					result = "&nbsp;&nbsp;&nbsp;· <a href='purchase/purchaseReturn/init'><span style='color:blue'>您有"
							+ countReturn + "条采购退货单需要审核</span></a></br>";
				} else {
					result += "&nbsp;&nbsp;&nbsp;· <a href='purchase/purchaseReturn/init'><span style='color:blue'>您有"
							+ countReturn + "条采购退货单需要审核</span></a></br>";
				}
			}
		}
		return result;
	}

	/**
	 * 获取仓库管理中的条数
	 * 
	 * @param roleCode
	 * @return
	 */
	private String getWareHouseCount(String roleCode, String result) {
		if ("CKSHY".equals(roleCode) || "ADMIN001".equals(roleCode)) {
			// 得到盘点单待审核条数
			Long countWareHouse = ordersService.getCountByObj(
					ZcWarehouse.class, "status='1'");
			// 得到差异盘点待审核条数
			Long countDifference = ordersService.getCountByObj(
					ZcCheckDifference.class, "STATUS='1'");
			// 得到库存调整待审核条数
			Long countStoreChange = ordersService.getCountByObj(
					ZcStoreChange.class, "STATUS='1'");
			// 得到转仓单待审核条数
			Long countSwitchChange = ordersService.getCountByObj(
					ZcSwitchhouse.class, "STATUS='1'");
			if (countWareHouse != null && countWareHouse > 0) {
				if (StringUtils.isBlank(result)) {
					result = "&nbsp;&nbsp;&nbsp;· <a href='warehouse/wareHouse/init'><span style='color:blue'>您有"
							+ countWareHouse + "条盘点单需要审核</span></a></br>";
				} else {
					result += "&nbsp;&nbsp;&nbsp;· <a href='warehouse/wareHouse/init'><span style='color:blue'>您有"
							+ countWareHouse + "条盘点单需要审核</span></a></br>";
				}
			}
			if (countDifference != null && countDifference > 0) {
				if (StringUtils.isBlank(result)) {
					result = "&nbsp;&nbsp;&nbsp;· <a href='checkDifference/init'><span style='color:blue'>您有"
							+ countDifference + "条差异盘点单需要审核</span></a></br>";
				} else {
					result += "&nbsp;&nbsp;&nbsp;· <a href='checkDifference/init'><span style='color:blue'>您有"
							+ countDifference + "条差异盘点单需要审核</span></a></br>";
				}
			}
			if (countStoreChange != null && countStoreChange > 0) {
				if (StringUtils.isBlank(result)) {
					result = "&nbsp;&nbsp;&nbsp;· <a href='storeChange/init'><span style='color:blue'>您有"
							+ countStoreChange + "条库存调整单需要审核</span></a></br>";
				} else {
					result += "&nbsp;&nbsp;&nbsp;· <a href='storeChange/init'><span style='color:blue'>您有"
							+ countStoreChange + "条库存调整单需要审核</span></a></br>";
				}
			}
			if (countSwitchChange != null && countSwitchChange > 0) {
				if (StringUtils.isBlank(result)) {
					result = "&nbsp;&nbsp;&nbsp;· <a href='switchChange/init'><span style='color:blue'>您有"
							+ countSwitchChange + "条转仓单需要审核</span></a></br>";
				} else {
					result += "&nbsp;&nbsp;&nbsp;· <a href='switchChange/init'><span style='color:blue'>您有"
							+ countSwitchChange + "条转仓单需要审核</span></a></br>";
				}
			}
		}
		return result;
	}

	/**
	 * 获取仓库加工分拣的条数
	 * 
	 * @param roleCode
	 * @return
	 */
	private String getWorkCount(String roleCode, String result) {
		if ("FJJGSHY".equals(roleCode) || "ADMIN001".equals(roleCode)) {
			// 得到加工单待审核条数
			Long countWareHouse = ordersService.getCountByObj(
					ProcessGoods.class, "statue='1'");
			// 得到分拣单待审核条数
			Long countDifference = ordersService.getCountByObj(Sorte.class,
					"auditStatus='1'");
			if (countWareHouse != null && countWareHouse > 0) {
				if (StringUtils.isBlank(result)) {
					result = "&nbsp;&nbsp;&nbsp;· <a href='wareHouse/processGoods/init'><span style='color:blue'>您有"
							+ countWareHouse + "条加工单需要审核</span></a></br>";
				} else {
					result += "&nbsp;&nbsp;&nbsp;· <a href='wareHouse/processGoods/init'><span style='color:blue'>您有"
							+ countWareHouse + "条加工单需要审核</span></a></br>";
				}
			}
			if (countDifference != null && countDifference > 0) {
				if (StringUtils.isBlank(result)) {
					result = "&nbsp;&nbsp;&nbsp;· <a href='sorte/sorteDo/init'><span style='color:blue'>您有"
							+ countDifference + "条分拣单需要审核</span></a></br>";
				} else {
					result += "&nbsp;&nbsp;&nbsp;· <a href='sorte/sorteDo/init'><span style='color:blue'>您有"
							+ countDifference + "条分拣单需要审核</span></a></br>";
				}
			}
		}
		return result;
	}
}
