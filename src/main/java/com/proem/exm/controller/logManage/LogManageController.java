package com.proem.exm.controller.logManage;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.cisdi.ctp.utils.common.StringUtils;
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.order.ZcHistoryOrder;
import com.proem.exm.entity.order.ZcOrderHistoryItem;
import com.proem.exm.entity.system.LogManage;
import com.proem.exm.service.system.LogManageService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

@Controller
@RequestMapping(value = "/logManage")
public class LogManageController extends BaseController {

	@Autowired
	LogManageService logManageService;

	@InitBinder("logManage")
	public void initGoods(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("logManage.");
	}

	@RequestMapping(value = "/init")
	public ModelAndView init(HttpServletRequest request,
			HttpServletResponse response) {
		return createSingleView("logManage/logManage");
	}

	@RequestMapping(value = "/initList")
	public ModelAndView initList(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "系统设置";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "系统管理";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "日志管理";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		return createIframeView("system/logmanage/logmanage_list");
	}

	@RequestMapping(value = "listJson", produces = "application/json")
	@ResponseBody
	public DataGrid listJson(@ModelAttribute LogManage logManage,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = logManageService.getPagedDataGridObj(page, logManage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 宜鲜美订单统计数据源
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "orderNumsExm", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String orderNumsExm(HttpServletRequest request,
			HttpServletResponse response) {
		String json = "[";
		try {
			String[] last12Months = new String[12];
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1); // 要先+1,才能把本月的算进去</span>
			for (int i = 0; i < 12; i++) {
				cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1); // 逐次往前推1个月
				if (Integer.valueOf(cal.get(Calendar.MONTH)) + 1 < 10) {
					last12Months[11 - i] = cal.get(Calendar.YEAR) + "-0"
							+ (Integer.valueOf(cal.get(Calendar.MONTH)) + 1)
							+ "";
				} else {
					last12Months[11 - i] = cal.get(Calendar.YEAR) + "-"
							+ (Integer.valueOf(cal.get(Calendar.MONTH)) + 1)
							+ "";
				}
			}
			for (int i = 0; i < last12Months.length; i++) {
				Long count = logManageService.getCountByObj(
						ZcHistoryOrder.class,
						"orderCome='1' and to_char(ORDERDATE,'YYYY-MM')='"
								+ last12Months[i] + "'");
				json += "\"" + last12Months[i] + "\"" + ",\"" + count + "\""
						+ ",";
			}
			for (int i = 0; i < last12Months.length; i++) {
				Long count = logManageService.getCountByObj(
						ZcHistoryOrder.class,
						"orderCome<>'1' and to_char(ORDERDATE,'YYYY-MM')='"
								+ last12Months[i] + "'");
				json += "\"" + count + "\"" + ",";
			}
			json = json.substring(0, json.length() - 1 > 0 ? json.length() - 1
					: 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		json += "]";
		return json;
	}

	/**
	 * 销售统计数据源
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "sellNums", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String sellNums(HttpServletRequest request,
			HttpServletResponse response) {
		String json = "[";
		try {
			String[] lastWeek = new String[7];
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.WEEK_OF_MONTH, -1);
			for (int i = 0; i < 7; i++) {
				cal.add(Calendar.DATE, -1 * cal.get(Calendar.DAY_OF_WEEK) + 2
						+ i);
				lastWeek[i] = dateFormat.format(cal.getTime());
			}
			for (int i = 0; i < lastWeek.length; i++) {
				List<ZcOrderHistoryItem> zcOrderHistoryItemsList = logManageService
						.getListByObj(ZcOrderHistoryItem.class,
								" to_char(CREATETIME,'YYYY-MM-DD')='"
										+ lastWeek[i] + "'");
				int shucai = 0;
				if (zcOrderHistoryItemsList != null
						&& zcOrderHistoryItemsList.size() > 0) {
					for (int j = 0; j < zcOrderHistoryItemsList.size(); j++) {
						ZcOrderHistoryItem zcOrderHistoryItem = zcOrderHistoryItemsList
								.get(j);
						if ("1".equals(zcOrderHistoryItem.getGoodsFile()
								.getGoods_class().getClassify_type())
								&& "143".equals(zcOrderHistoryItem
										.getGoodsFile().getGoods_class()
										.getId())) {
							shucai += 1;
						}
					}
				}
				json += "\"" + shucai + "\"" + ",";
				int shuiguo = 0;
				if (zcOrderHistoryItemsList != null
						&& zcOrderHistoryItemsList.size() > 0) {
					for (int j = 0; j < zcOrderHistoryItemsList.size(); j++) {
						ZcOrderHistoryItem zcOrderHistoryItem = zcOrderHistoryItemsList
								.get(j);
						if ("1".equals(zcOrderHistoryItem.getGoodsFile()
								.getGoods_class().getClassify_type())
								&& "144".equals(zcOrderHistoryItem
										.getGoodsFile().getGoods_class()
										.getId())) {
							shuiguo += 1;
						}
					}
				}
				json += "\"" + shuiguo + "\"" + ",";
				int lengxian = 0;
				if (zcOrderHistoryItemsList != null
						&& zcOrderHistoryItemsList.size() > 0) {
					for (int j = 0; j < zcOrderHistoryItemsList.size(); j++) {
						ZcOrderHistoryItem zcOrderHistoryItem = zcOrderHistoryItemsList
								.get(j);
						if ("1".equals(zcOrderHistoryItem.getGoodsFile()
								.getGoods_class().getClassify_type())
								&& "429".equals(zcOrderHistoryItem
										.getGoodsFile().getGoods_class()
										.getId())) {
							lengxian += 1;
						}
					}
				}
				json += "\"" + lengxian + "\"" + ",";
				int qita = 0;
				if (zcOrderHistoryItemsList != null
						&& zcOrderHistoryItemsList.size() > 0) {
					for (int j = 0; j < zcOrderHistoryItemsList.size(); j++) {
						ZcOrderHistoryItem zcOrderHistoryItem = zcOrderHistoryItemsList
								.get(j);
						if ("1".equals(zcOrderHistoryItem.getGoodsFile()
								.getGoods_class().getClassify_type())
								&& !"429".equals(zcOrderHistoryItem
										.getGoodsFile().getGoods_class()
										.getId())
								&& !"144".equals(zcOrderHistoryItem
										.getGoodsFile().getGoods_class()
										.getId())
								&& !"143".equals(zcOrderHistoryItem
										.getGoodsFile().getGoods_class()
										.getId())) {
							qita += 1;
						}
					}
				}
				json += "\"" + qita + "\"" + ",";

			}
			json = json.substring(0, json.length() - 1 > 0 ? json.length() - 1
					: 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		json += "]";
		return json;
	}

	/**
	 * 商品销售排行数据源
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "goodsSellsRand", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String goodsSellsRand(HttpServletRequest request,
			HttpServletResponse response) {
		String json = "[";
		try {
			String[] lastWeek = new String[7];
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.WEEK_OF_MONTH, -1);
			for (int i = 0; i < 7; i++) {
				cal.add(Calendar.DATE, -1 * cal.get(Calendar.DAY_OF_WEEK) + 2
						+ i);
				lastWeek[i] = dateFormat.format(cal.getTime());
			}
			for (int i = 0; i < lastWeek.length; i++) {
				List<ZcOrderHistoryItem> zcOrderHistoryItemsList = logManageService
						.getListByObj(ZcOrderHistoryItem.class,
								" to_char(CREATETIME,'YYYY-MM-DD')='"
										+ lastWeek[i] + "'");
				String[] goodsIdList = null;
				Long[] countList = null;
				if (zcOrderHistoryItemsList != null
						&& zcOrderHistoryItemsList.size() > 0) {
					for (int j = 0; j < zcOrderHistoryItemsList.size(); j++) {
						ZcOrderHistoryItem zcOrderHistoryItem = zcOrderHistoryItemsList
								.get(j);
						Long count = logManageService.getCountByObj(
								ZcOrderHistoryItem.class, "GOODSFILE_ID='"
										+ zcOrderHistoryItem.getGoodsFile()
												.getId() + "'");
						countList[j] = count;
					}
				}
				for (int j = 0; j < countList.length; j++) {
					for (int k = j; k < countList.length; k++) {
						if (countList[j] < countList[k]) {
							Long temp = countList[j];
							countList[j] = countList[k];
							countList[j] = temp;
						}
					}
				}
				for (int j = 0; j < zcOrderHistoryItemsList.size(); j++) {
					ZcOrderHistoryItem zcOrderHistoryItem = zcOrderHistoryItemsList
							.get(j);
					Long count = logManageService.getCountByObj(
							ZcOrderHistoryItem.class, "GOODSFILE_ID='"
									+ zcOrderHistoryItem.getGoodsFile().getId()
									+ "'");
					if (count == countList[0]) {
						json += "\""
								+ zcOrderHistoryItem.getGoodsFile()
										.getGoods_name() + "\"" + ",\"" + count
								+ "\"" + ",";
						break;
					}
				}

			}
			json = json.substring(0, json.length() - 1 > 0 ? json.length() - 1
					: 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		json += "]";
		return json;
	}
}
