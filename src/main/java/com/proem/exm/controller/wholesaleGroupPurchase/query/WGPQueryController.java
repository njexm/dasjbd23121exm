package com.proem.exm.controller.wholesaleGroupPurchase.query;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdi.ctp.utils.common.StringUtils;
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.purchase.PurchaseOrderGoodsItems;
import com.proem.exm.entity.wholesaleGroupPurchase.WholesaleQuery;
import com.proem.exm.entity.wholesaleGroupPurchase.wholesell.WholeSellGoodsItems;
import com.proem.exm.service.wholesaleGroupPurchase.query.WGPQueryService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 团购销售查询
 * 
 * @author ZuoYM
 * @com proem
 */
@Controller
@RequestMapping("wholesaleGroupPurchase/query")
public class WGPQueryController extends BaseController {

	@Autowired
	WGPQueryService wGPQueryService;

	@InitBinder("wholesaleQuery")
	public void initWholesaleQuery(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("wholesaleQuery.");
	}

	/**
	 * 打开商品汇总页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initQuery_Goods")
	public ModelAndView initQuery_Goods(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "批发团购";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "团购销售查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "商品汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/query/wGPQuery_Goods");
		return view;
	}

	/**
	 * 打开类别汇总页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initSellClass")
	public ModelAndView initSellClass(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "批发团购";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "团购销售查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "类别汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/query/wGPQuery_Class");
		return view;
	}

	/**
	 * 打开批发明细页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initQuery_Detail")
	public ModelAndView initQuery_Detail(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "批发团购";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "团购销售查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "批发明细";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/query/wGPQuery_Detail");
		return view;
	}

	/**
	 * 打开客户汇总页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initQuery_Customer")
	public ModelAndView initQuery_Customer(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "批发团购";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "团购销售查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "客户汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/query/wGPQuery_Customer");
		return view;
	}

	/**
	 * 打开客户-商品销售汇总页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initQuery_CustAndGoods")
	public ModelAndView initQuery_CustAndGoods(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "批发团购";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "团购销售查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "客户-商品销售汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/query/wGPQuery_CustAndGoods");
		return view;
	}

	/**
	 * 打开业务员-客户页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initQuery_CustAndCre")
	public ModelAndView initQuery_CustAndCre(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "批发团购";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "团购销售查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "业务员-客户";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/query/wGPQuery_CustAndCre");
		return view;
	}

	/**
	 * 打开品牌销售汇总页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initQuery_Brand")
	public ModelAndView initQuery_Brand(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "批发团购";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "团购销售查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "品牌销售汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/query/wGPQuery_Brand");
		return view;
	}

	/**
	 * 打开销售单据汇总页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initQuery_Document")
	public ModelAndView initQuery_Document(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "批发团购";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "团购销售查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "销售单据汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/query/wGPQuery_Document");
		return view;
	}

	/**
	 * 打开客户-商品销售明细页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initQuery_CAGDetail")
	public ModelAndView initQuery_CAGDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "批发团购";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "团购销售查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "客户-商品销售明细";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/query/wGPQuery_CAGDetail");
		return view;
	}

	/**
	 * 打开区域销售汇总页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initQuery_Area")
	public ModelAndView initQuery_Area(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "批发团购";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "团购销售查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "区域销售汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/query/wGPQuery_Area");
		return view;
	}

	/**
	 * 打开商品毛利汇总页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initQuery_GoodsProfit")
	public ModelAndView initQuery_GoodsProfit(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "批发团购";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "团购销售查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "商品(毛利)汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/query/wGPQuery_GoodsProfit");
		return view;
	}

	/**
	 * 打开类别毛利汇总页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initQuery_ClassProfit")
	public ModelAndView initQuery_ClassProfit(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "批发团购";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "团购销售查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "类别(毛利)汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/query/wGPQuery_ClassProfit");
		return view;
	}

	/**
	 * 打开品牌毛利汇总页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initQuery_BrandProfit")
	public ModelAndView initQuery_BrandProfit(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "批发团购";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "团购销售查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "品牌(毛利)汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/query/wGPQuery_BrandProfit");
		return view;
	}

	/**
	 * 打开客户毛利汇总页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initQuery_CustProfit")
	public ModelAndView initQuery_CustProfit(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "批发团购";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "团购销售查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "客户(毛利)汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/query/wGPQuery_CustProfit");
		return view;
	}

	/**
	 * 打开分店/仓库毛利汇总页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initQuery_BranchProfit")
	public ModelAndView initQuery_BranchProfit(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "批发团购";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "团购销售查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "分店/仓库(毛利)汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/query/wGPQuery_BranchProfit");
		return view;
	}

	/**
	 * 打开区域毛利汇总页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initQuery_AreaProfit")
	public ModelAndView initQuery_AreaProfit(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "批发团购";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "团购销售查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "区域(毛利)汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/query/wGPQuery_AreaProfit");
		return view;
	}

	/**
	 * 打开业务员毛利汇总页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initQuery_CreProfit")
	public ModelAndView initQuery_CreProfit(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "批发团购";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "团购销售查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "业务员(毛利)汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/query/wGPQuery_CreProfit");
		return view;
	}

	/**
	 * 打开单据毛利汇总页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initQuery_DocuProfit")
	public ModelAndView initQuery_DocuProfit(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "批发团购";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "团购销售查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "单据(毛利)汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/query/wGPQuery_DocuProfit");
		return view;
	}

	/**
	 * 打开日毛利汇总页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initQuery_DateProfit")
	public ModelAndView initQuery_DateProfit(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "批发团购";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "团购销售查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "日毛利汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/query/wGPQuery_DateProfit");
		return view;
	}

	/**
	 * 商品查询
	 * @param wholesaleQuery
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listGoodsQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsQueryJson(
			@ModelAttribute WholesaleQuery wholesaleQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wGPQueryService.getGoodsQuery(page, wholesaleQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 类别查询
	 * @param wholesaleQuery
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listClassifyQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listClassifyQueryJson(
			@ModelAttribute WholesaleQuery wholesaleQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wGPQueryService.getClassifyQueryGoods(page, wholesaleQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 明细查询
	 * @param wholesaleQuery
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listDetailQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listDetailQueryJson(
			@ModelAttribute WholesaleQuery wholesaleQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wGPQueryService.getDetailQuery(page, wholesaleQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 客户查询
	 * @param wholesaleQuery
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listCustomerQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listCustomerQueryJson(
			@ModelAttribute WholesaleQuery wholesaleQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wGPQueryService.getCustomerQuery(page, wholesaleQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 客户-商品销售查询
	 * @param wholesaleQuery
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listCAGoodsQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listCAGoodsQueryJson(
			@ModelAttribute WholesaleQuery wholesaleQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wGPQueryService.getCustAndGoodsQuery(page, wholesaleQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 业务员-客户查询
	 * @param wholesaleQuery
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listCACQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listCACQueryJson(
			@ModelAttribute WholesaleQuery wholesaleQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wGPQueryService.getCustAndCreQuery(page, wholesaleQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 品牌销售查询
	 * @param wholesaleQuery
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listBrandQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listBrandQueryJson(
			@ModelAttribute WholesaleQuery wholesaleQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wGPQueryService.getBrandQuery(page, wholesaleQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 销售单据查询
	 * @param wholesaleQuery
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listDocumentQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listDocummentQueryJson(
			@ModelAttribute WholesaleQuery wholesaleQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wGPQueryService.getDocumentQuery(page, wholesaleQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 客户-商品销售明细查询
	 * @param wholesaleQuery
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listCAGDetailQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listCAGDetailQueryJson(
			@ModelAttribute WholesaleQuery wholesaleQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wGPQueryService.getCAGDetailQuery(page, wholesaleQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 区域销售查询
	 * @param wholesaleQuery
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listAreaQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listAreaQueryJson(
			@ModelAttribute WholesaleQuery wholesaleQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wGPQueryService.getAreaQueryGoods(page, wholesaleQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 商品毛利查询
	 * @param wholesaleQuery
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listGProfitQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listGProfitQueryJson(
			@ModelAttribute WholesaleQuery wholesaleQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wGPQueryService.getGProfitQuery(page, wholesaleQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 类别毛利查询
	 * @param wholesaleQuery
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listCProfitQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listCProfitQueryJson(
			@ModelAttribute WholesaleQuery wholesaleQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wGPQueryService.getCProfitQuery(page, wholesaleQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 品牌毛利查询
	 * @param wholesaleQuery
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listBProfitQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listBProfitQueryJson(
			@ModelAttribute WholesaleQuery wholesaleQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wGPQueryService.getBProfitQuery(page, wholesaleQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 客户毛利查询
	 * @param wholesaleQuery
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listCustProfitQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listCustProfitQueryJson(
			@ModelAttribute WholesaleQuery wholesaleQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wGPQueryService.getCustProfitQuery(page, wholesaleQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 分店/仓库毛利查询
	 * @param wholesaleQuery
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listBranchProfitQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listBranchProfitQueryJson(
			@ModelAttribute WholesaleQuery wholesaleQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wGPQueryService.getBranchProfitQuery(page, wholesaleQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 区域毛利查询
	 * @param wholesaleQuery
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listAreaProfitQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listAreaProfitQueryJson(
			@ModelAttribute WholesaleQuery wholesaleQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wGPQueryService.getAreaProfitQuery(page, wholesaleQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 业务员毛利查询
	 * @param wholesaleQuery
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listCreProfitQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listCreProfitQueryJson(
			@ModelAttribute WholesaleQuery wholesaleQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wGPQueryService.getCreProfitQuery(page, wholesaleQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 单据毛利查询
	 * @param wholesaleQuery
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listDocuProfitQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listDocuProfitQueryJson(
			@ModelAttribute WholesaleQuery wholesaleQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wGPQueryService.getDocuProfitQuery(page, wholesaleQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 日毛利查询
	 * @param wholesaleQuery
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listDateProfitQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listDateProfitQueryJson(
			@ModelAttribute WholesaleQuery wholesaleQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wGPQueryService.getDateProfitQuery(page, wholesaleQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
}