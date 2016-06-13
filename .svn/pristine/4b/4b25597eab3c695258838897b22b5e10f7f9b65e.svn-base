package com.proem.exm.controller.purchase;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

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
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.warehouse.ZcStorehouse;
import com.proem.exm.service.purchase.PurchaseOrderService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 采购订单管理控制器
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("purchaseGoods")
public class PurchaseGoodsController extends BaseController {
	@Autowired
	PurchaseOrderService purchaseOrderService;
	@InitBinder("goodsFile")
	public void intGoodsFile (WebDataBinder binder){
		binder.setFieldDefaultPrefix("goodsFile.");
	}

	/**
	 * 打开初始化跳转页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("init")
	public ModelAndView init(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "采购管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "待采购商品";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "待采购商品汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("purchase/purchaseGoods/orders_handle_list");
		return view;
	}
	
	@RequestMapping(value= "listHandleJson",produces = "application/json")
	@ResponseBody
	public DataGrid listHandleJson(@ModelAttribute GoodsFile goodsFile,
			HttpServletRequest request, HttpServletResponse response, Page page){
		DataGrid dataGrid = null;
		DecimalFormat df=new DecimalFormat(".##");
		try {
			dataGrid = purchaseOrderService.getHandleDataGrid(page, goodsFile);
			List<Map<String, Object>> list = dataGrid.getRows();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					String goodId=(String) list.get(i).get("GOODSFILE_ID");
					BigDecimal  totalNum=(BigDecimal) list.get(i).get("NUMS");
					double needNums=0;
					String guige=(String) list.get(i).get("GOODS_SPECIFICATIONS");
					String str2="";
					String[] str3 = null;
					String str4 = "";
					if (guige != null && guige != "") {
						for (int z = 0; z < guige.length(); z++) {
							if ((guige.charAt(z) >= '0' && guige.charAt(z) <= '9')
									|| guige.charAt(z) == '.') {
								str2 += guige.charAt(z);
							}
						}
						// TODO 后台去掉“商品规格没有实现”
						str3 = guige.split(":",2);
						if (str3[0] != "商品规格") {
							str4 = str3[0];
						} else {
							str4 = str3[1];
						}
					}
					list.get(i).put("specifications", str4);
					double spe=Double.valueOf(str2);
					double store=0;
					List<ZcStorehouse> storeList=purchaseOrderService.getListByObj(ZcStorehouse.class, "goodsfile_id='"+goodId+"'");
					//库存数量
					if(storeList!=null && storeList.size()>0){
						for(int a =0;a<storeList.size();a++){
							String num=storeList.get(a).getStore();
							if(StringUtils.isBlank(num)){
								num="0";
							}
							double nums=Double.valueOf(num);
							store=store+nums;
						}
					}
					needNums=totalNum.doubleValue()-store;
					if(needNums<=0){
						list.get(i).put("lossStore", "0");
					}else{
						list.get(i).put("lossStore", "<span style='color:red;font-weight:700 '>"+needNums+"</span>");
					}
					//判断商品为成品还是原材料
					List<GoodsFile> goodsFileList=purchaseOrderService.getListByObj(GoodsFile.class, "PRODUCTGOODSID='"+goodId+"'");
					if(goodsFileList==null ||goodsFileList.size()==0){
						list.get(i).put("WASTERATE", "0");
						list.get(i).put("needNums", "0");
						list.get(i).put("storeNums", "0");
						if(needNums<=0){
							list.get(i).put("storeneedNums", "0");
						}else{
							list.get(i).put("storeneedNums", "<span style='color:red;font-weight:700 '>"+needNums+"</span>");
						}
					}else{
						GoodsFile good=goodsFileList.get(0);
						String  wasterate=good.getWasteRate();
						double wast=Double.valueOf(wasterate);
						list.get(i).put("WASTERATE", good.getWasteRate());
						double storeNums=0;
						String goodsId=good.getId();
						List<ZcStorehouse> storeCailiaoList=purchaseOrderService.getListByObj(ZcStorehouse.class, "goodsfile_id='"+goodsId+"'");
						if(storeCailiaoList!=null && storeCailiaoList.size()>0){
							for(int a =0;a<storeCailiaoList.size();a++){
								String num=storeCailiaoList.get(a).getStore();
								if(StringUtils.isBlank(num)){
									num="0";
								}
								double nums=Double.valueOf(num);
								storeNums=storeNums+nums;
							}
							if(needNums<=0){
								list.get(i).put("needNums", "0");
								if(storeNums<=0){
									list.get(i).put("storeNums", "0");
								}else{
									list.get(i).put("storeNums", df.format(storeNums));
								}
								list.get(i).put("storeneedNums", "0");
							}else{
								double needNum=needNums*spe*(1+wast/100);
								list.get(i).put("needNums", df.format(needNum));
								if(storeNums<=0){
									list.get(i).put("storeNums", "0");
								}else{
									list.get(i).put("storeNums", df.format(storeNums));
								}
								double storeneedNums=needNum-storeNums;
								if(storeneedNums<=0){
									list.get(i).put("storeneedNums", "0");
								}else{
									list.get(i).put("storeneedNums","<span style='color:red;font-weight:700 '>"+ df.format(storeneedNums)+"</span>");
								}
							}
						}else{
							if(needNums<=0){
								list.get(i).put("needNums", "0");
								if(storeNums<=0){
									list.get(i).put("storeNums", "0");
								}else{
									list.get(i).put("storeNums", df.format(storeNums));
								}
								list.get(i).put("storeneedNums", "0");
							}else{
							double needNum=needNums*spe*(1+wast/100);
							list.get(i).put("needNums", df.format(needNum));
							if(storeNums<=0){
								list.get(i).put("storeNums", "0");
							}else{
								list.get(i).put("storeNums", df.format(storeNums));
							}
							double storeneedNums=needNum-storeNums;
							if(storeneedNums<=0){
								list.get(i).put("storeneedNums", "0");
							}else{
								list.get(i).put("storeneedNums","<span style='color:red;font-weight:700 '>"+ df.format(storeneedNums)+"</span>");
							}
							}
						}
					}
					list.get(i).put("store", store);
				}
				dataGrid.setRows(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

}
