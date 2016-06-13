package com.proem.exm.controller.basic.goodsFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cisdi.ctp.utils.common.StringUtils;
import com.cisdi.ctp.utils.common.UuidUtils;
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.basic.CommodityClassify.CommodityClassify;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.warehouse.ZcStorehouse;
import com.proem.exm.service.basic.CommodityClassify.CommodityClassifyService;
import com.proem.exm.service.basic.goodsFileService.GoodsFileService;
import com.proem.exm.service.basic.provider.ProviderInfoService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.Result;
import com.proem.exm.utils.Result.Status;

/**
 * 
 * @author songcj 商品档案controller 2015年10月20日下午4:15:52
 */
@Controller
@RequestMapping("goodsFile/goodsFile")
public class GoodsFileController extends BaseController {
	private static final String OBLIQUE_LINE = "/";
	private static final String WEBPOSITION = "webapps";
	private static final String WTPPWEBAPPS = "wtpwebapps";

	public static final String SBPATH = "goodsExcel/";
	@Autowired
	private GoodsFileService goodsFileService;
	@Autowired
	ProviderInfoService providerInfoService;
	@Autowired
	CommodityClassifyService commodityClassifyService;

	@InitBinder("goodsFile")
	public void initUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("goodsFile.");
	}

	@InitBinder("ProviderInfoView.")
	public void initProviderInfoView(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("ProviderInfoView.");
	}

	@InitBinder("commodityClassify")
	public void initCommodityClassify(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("commodityClassify.");
	}

	// 初始化跳转页面
	@RequestMapping("init")
	public ModelAndView init(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "基础档案";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "档案管理";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "商品档案";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("basic/goods_file/goods_file_list");
		return view;
	}

	// 跳转供应商选择

	// 跳转供应商新增
	@RequestMapping(value = "add")
	public ModelAndView addBranch(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = createIframeView("basic/goods_file/goods_file/goods_provider_add");
		return view;
	}

	// 跳转供应商选择
	@RequestMapping("providerInit")
	public ModelAndView providerInit(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		GoodsFile goodsFile = (GoodsFile) goodsFileService.getObjById(id,
				"GoodsFile");
		model.addAttribute("goodsFile", goodsFile);
		ModelAndView view = createIframeView("basic/goods_file/goods_provider_list");
		return view;
	}

	// 打开新增页面
	@RequestMapping("gotoAddGoodsFile")
	public ModelAndView gotoAddOrders(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		CommodityClassify commodityClassify = (CommodityClassify) goodsFileService
				.getObjById(id, "CommodityClassify");
		GoodsFile goodsFile = new GoodsFile();
		goodsFile.setId(UuidUtils.getUUID());
		goodsFile.setDelflag("2");
		goodsFileService.saveObj(goodsFile);
		model.addAttribute("goodsFile", goodsFile);
		model.addAttribute("CommodityClassify", commodityClassify);
		ModelAndView view = createIframeViewIncludeMap(
				"basic/goods_file/goods_file_add", initLoadPage());
		return view;
	}

	// 打开编辑页面
	@RequestMapping("gotoEditGoodsFile")
	public ModelAndView gotoEditGoodsFile(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		GoodsFile goodsFile = (GoodsFile) goodsFileService.getObjById(id,
				"GoodsFile");
		model.addAttribute("goodsFile", goodsFile);
		model.addAttribute("id", id);
		ModelAndView view = createIframeViewIncludeMap(
				"basic/goods_file/goods_file_edit", initLoadPage());
		return view;
	}

	// 打开详细页面
	@RequestMapping("gotoDetailGoodsFile")
	public ModelAndView gotoDetailOrders(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		GoodsFile goodsFile = (GoodsFile) goodsFileService.getObjById(id,
				"GoodsFile");
		model.addAttribute("goodsFile", goodsFile);
		ModelAndView view = createIframeViewIncludeMap(
				"basic/goods_file/goods_file_detail", initLoadPage());
		return view;
	}

	// 打开新增商品调价单页面
	@RequestMapping("gotoAddAdjustment")
	public ModelAndView gotoAddAdjustment(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = createIframeViewIncludeMap(
				"basic/goods_file/goods_file_addAdjustment", initLoadPage());
		return view;
	}

	private Map initLoadPage() {
		Map returnMap = new HashMap();
		returnMap.put("ClassList", goodsFileService.getClassInfoList());
		returnMap.put("ProviderList", goodsFileService.getClassInfoList());
		return returnMap;
	}

	/**
	 * 分页查询
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listGoodsFileJson", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsFileJson(@ModelAttribute GoodsFile goodsFile,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			System.out.println(goodsFile.getId());
			GoodsFile goodsFileDel = null;
			List<GoodsFile> goodsFileList = goodsFileService.getListByObj(
					GoodsFile.class, " delflag = '2' ");
			if (goodsFileList.size() > 0) {
				for (int i = 0; i < goodsFileList.size(); i++) {
					goodsFileDel = goodsFileList.get(i);
					goodsFileService.deleteObjById(goodsFileDel.getId(),
							GoodsFile.class.getName());
				}
			}
			dataGrid = goodsFileService.getPagedDataGridObj(page, goodsFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 分页查询供应商商品-
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listGoodsFileOfJson", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsFileOfJson(@ModelAttribute GoodsFile goodsFile,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			String providerId = request.getParameter("providerId");
			dataGrid = goodsFileService.providerGoods(page, goodsFile,
					providerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 分页查询供应商为空的商品信息
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listGoodsNullProvider", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsNullProvider(@ModelAttribute GoodsFile goodsFile,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = goodsFileService.getPagedDataNullProvider(page,
					goodsFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 分页查询供应商商品-
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listGoodsOfProvider", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsOfProvider(@ModelAttribute GoodsFile goodsFile,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			String providerId = request.getParameter("providerId");
			dataGrid = goodsFileService.providerGoods(page, goodsFile,
					providerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	// 新增数据
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult save(@ModelAttribute GoodsFile goodsFile,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			Long count = goodsFileService.getCountByObj(GoodsFile.class,
					"serialNumber='" + goodsFile.getSerialNumber() + "'");
			if (count != 0) {
				ajaxResult = new AjaxResult(AjaxResult.SELECT,
						AjaxResult.SUCCESS, AjaxResult.INFO);
				return ajaxResult;
			}
			String id = request.getParameter("id");
			GoodsFile goods = (GoodsFile) goodsFileService.getObjById(id,
					"GoodsFile");
			GoodsFile good = null;
			List<GoodsFile> goodsFilesList = goodsFileService.getListByObj(
					GoodsFile.class,
					"PRODUCTGOODSID='" + goods.getProductGoodsId() + "'");
			if (goodsFilesList != null && goodsFilesList.size() > 0) {
				good = goodsFilesList.get(0);
			}
			List<ZcStorehouse> zcStorehousesLists = goodsFileService
					.getListByObj(ZcStorehouse.class,
							"goodsFile_id='" + goods.getProductGoodsId() + "'");
			if (goods.getId() != null && goods.getId().length() > 0) {
				goodsFileService.deleteObjById(goods.getId(),
						GoodsFile.class.getName());
			}
			goodsFile.setId(id);
			goodsFile.setDelflag("1");
			goodsFile.setProductGoodsId(goodsFile.getProductGoodsId());
			goodsFileService.saveObj(goodsFile);
			if (zcStorehousesLists != null && zcStorehousesLists.size() > 0
					&& good != null) {
				ZcStorehouse zcStorehouse = zcStorehousesLists.get(0);
				zcStorehouse.setGoodsFile(goodsFile);
				goodsFileService.updateObj(zcStorehouse);
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

	/**
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getObjById", produces = "application/json")
	@ResponseBody
	public Map<String, Object> getObjById(@ModelAttribute GoodsFile goodsFile,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = null;
		try {
			map = goodsFileService.getObjById(goodsFile.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	// 修改数据
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult update(@ModelAttribute GoodsFile goodsFile,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			goodsFile.setDelflag("0");
			goodsFileService.updateObj(goodsFile);
			GoodsFile goods = null;
			GoodsFile good = (GoodsFile) goodsFileService.getObjById(
					goodsFile.getId(), "GoodsFile");
			List<GoodsFile> goodsFilesList = goodsFileService.getListByObj(
					GoodsFile.class,
					"PRODUCTGOODSID='" + good.getProductGoodsId() + "'");
			if (goodsFilesList != null && goodsFilesList.size() > 0) {
				goods = goodsFilesList.get(0);
			}
			List<ZcStorehouse> zcStorehousesLists = goodsFileService
					.getListByObj(ZcStorehouse.class,
							"goodsFile_id='" + good.getProductGoodsId() + "'");
			if (zcStorehousesLists != null && zcStorehousesLists.size() > 0
					&& goods != null) {
				ZcStorehouse zcStorehouse = zcStorehousesLists.get(0);
				zcStorehouse.setGoodsFile(goods);
				goodsFileService.updateObj(zcStorehouse);
			}
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	// 删除
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult delete(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				goodsFileService.deleteObjById(ids[i],
						GoodsFile.class.getName());
				logManageService.insertLog(request, "删除了选中的商品", "商品档案");
			}
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
			return ajaxResult;
		}
		return ajaxResult;
	}

	@RequestMapping(value = "/saveBatchImport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveBatchImport(
			@RequestParam("sourceBrocastBatch") MultipartFile myfile,
			HttpServletRequest request) {
		Map result = new HashMap<Object, Object>();
		// 获取文件路径
		String path = saveExcelFile(request, myfile);
		Map<String, Object> listResult = goodsFileService.getAllByExcel(path);
		if (listResult != null) {
			// 判断excel数据没有错误
			if ("".equals(listResult.get("returnAnwer"))) {
				List<GoodsFile> listSupply = (List<GoodsFile>) listResult
						.get("listSupply");
				for (int i = 0; i < listSupply.size(); i++) {
					GoodsFile goodsFile = new GoodsFile();
					goodsFile = listSupply.get(i);
					int re = goodsFileService.saveByExcel(goodsFile);
					if (re == 0) {
						result.put("msg", "fail");
						JSONObject jsonObject = JSONObject.fromObject(result);
						return jsonObject.toString();
					}
				}
				result.put("msg", "success");
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			} else {
				result.put("resultAnwser", listResult.get("returnAnwer"));
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			}
		} else {
			result.put("msg", "fail");
			JSONObject jsonObject = JSONObject.fromObject(result);
			return jsonObject.toString();
		}

	}

	/**
	 * 保存文件至服务器
	 */
	private String saveExcelFile(HttpServletRequest request,
			MultipartFile myfile) {
		String path = "";
		// 文件保存路径
		try {
			String tomcatPath = request.getSession().getServletContext()
					.getRealPath("/");
			tomcatPath = tomcatPath.replace("\\", "/");
			StringBuffer destFName = new StringBuffer();
			destFName.append(getRealDir(tomcatPath).replace("web/", "/"))
					.append(SBPATH);
			System.out.println("得到的地址是：" + destFName);
			String filePath = destFName.toString().replace("\\", "/");
			// 转存文件
			String fileName = myfile.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			fileName = "" + System.currentTimeMillis();
			if (fileType.equals(".xls")) {
				fileName = fileName + ".xls";
				File targetFile = new File(filePath, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs(); // 上传图片到服务器
					myfile.transferTo(targetFile);
					path = targetFile.getPath();
				}
			} else {
				path = "fail";
			}

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * 验证excel的目录
	 * 
	 * @param newFileNameRoot
	 * @return
	 * @throws Exception
	 */
	private String getRealDir(String newFileNameRoot) throws Exception {
		if (newFileNameRoot == null)
			throw new Exception("get real dir failed !");
		int dp = newFileNameRoot.lastIndexOf(OBLIQUE_LINE);
		if (dp == -1)
			throw new Exception("invalid path !");
		int dpbefore = newFileNameRoot.lastIndexOf(OBLIQUE_LINE, dp - 1);
		if (dpbefore == -1)
			throw new Exception("invalid path !");
		String needSubStr = newFileNameRoot.substring(dpbefore + 1, dp);
		String nextStr = newFileNameRoot.substring(0, dpbefore + 1);
		if (!needSubStr.trim().equals(WEBPOSITION)
				&& !needSubStr.trim().equals(WTPPWEBAPPS)) {
			return getRealDir(nextStr);
		} else
			return newFileNameRoot;
	}

	/**
	 * 区域基本信息列表/树形表格
	 * 
	 * @param areaNanJing
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listGoodsFileTreeJson", produces = "application/json")
	@ResponseBody
	public Object listGoodsFileTreeJson(@ModelAttribute GoodsFile goodsFile,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			if ("0".equals(goodsFile.getId())) {
				dataGrid = goodsFileService
						.getPagedDataGridObj(page, goodsFile);
				List<Map<String, Object>> list = dataGrid.getRows();
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						list.get(i).put("state", "closed");
					}
					dataGrid.setRows(list);
				}
				return dataGrid;
			} else {
				String id = goodsFile.getId();
				List<Map<String, Object>> children = goodsFileService
						.getListByParent(id);
				if (children != null && children.size() > 0) {
					for (int i = 0; i < children.size(); i++) {
						if ("1".equals(children.get(i).get("GOODS_CLASS_NAME"))) {
							children.get(i).put("state", "closed");
						} else if ("2".equals(children.get(i).get(
								"GOODS_CLASS_NAME"))) {
							children.get(i).put("state", "closed");
						} else {
							children.get(i).put("state", "open");
						}
					}
				}
				return children;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 编辑页面展示父级菜单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getTreeData")
	@ResponseBody
	public List<Map<String, Object>> getTreeData(HttpServletRequest request,
			HttpServletResponse response) {
		return goodsFileService.getTreeData();
	}

	/**
	 * 获取首字母并存入数据库
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getLetter", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult getLetter(@ModelAttribute GoodsFile goodsFile,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		AjaxResult ajaxResult = null;
		try {
			System.out.println("进入方法：");
			List<GoodsFile> goodsList = goodsFileService.getListByObj(
					GoodsFile.class, "");
			if (goodsList != null && goodsList.size() > 0) {
				String str = "";
				String Letter = "";
				for (int i = 0; i < goodsList.size(); i++) {
					GoodsFile goods = goodsList.get(i);
					str = goods.getGoods_name();
					Letter = getLetterStr(str, true);
					goods.setGoods_PY_code(Letter);
					System.out.println("商品名：" + goods.getGoods_name()
							+ "\n首字母：" + goods.getGoods_PY_code());
					goodsFileService.updateObj(goods);
				}
			}
			logManageService.insertLog(request, "更新了商品拼音码", "商品管理");
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	/**
	 * 返回首字母
	 * 
	 * @param strChinese
	 * @param bUpCase
	 * @return
	 */
	public String getLetterStr(String strChinese, boolean bUpCase) {

		try {
			StringBuffer buffer = new StringBuffer();
			byte b[] = strChinese.getBytes("GBK");// 转化byte数组
			for (int i = 0; i < b.length; i++) {
				if ((b[i] & 255) > 128) {
					int char1 = b[i++] & 255;
					char1 <<= 8;
					int chart = char1 + (b[i] & 255);
					buffer.append(getLetterChar((char) chart, bUpCase));
					continue;
				}
				char c = (char) b[i];
				if (!Character.isJavaIdentifierPart(c)) {// 确定指定字符是否可以是 Java
															// 标识符中首字符以外的部分。
					c = 'A';
				}
				buffer.append(c);
			}
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 得到首字母
	 * 
	 * @param strChinese
	 * @param bUpCase
	 * @return
	 */
	private char getLetterChar(char strChinese, boolean bUpCase) {

		int charGBK = strChinese;
		char result;

		if (charGBK >= 45217 && charGBK <= 45252) {
			result = 'A';
		} else if (charGBK >= 45253 && charGBK <= 45760) {
			result = 'B';
		} else if (charGBK >= 45761 && charGBK <= 46317) {
			result = 'C';
		} else if (charGBK >= 46318 && charGBK <= 46825) {
			result = 'D';
		} else if (charGBK >= 46826 && charGBK <= 47009) {
			result = 'E';
		} else if (charGBK >= 47010 && charGBK <= 47296) {
			result = 'F';
		} else if (charGBK >= 47297 && charGBK <= 47613) {
			result = 'G';
		} else if (charGBK >= 47614 && charGBK <= 48118) {
			result = 'H';
		} else if (charGBK >= 48119 && charGBK <= 49061) {
			result = 'J';
		} else if (charGBK >= 49062 && charGBK <= 49323) {
			result = 'K';
		} else if (charGBK >= 49324 && charGBK <= 49895) {
			result = 'L';
		} else if (charGBK >= 49896 && charGBK <= 50370) {
			result = 'M';
		} else if (charGBK >= 50371 && charGBK <= 50613) {
			result = 'N';
		} else if (charGBK >= 50614 && charGBK <= 50621) {
			result = 'O';
		} else if (charGBK >= 50622 && charGBK <= 50905) {
			result = 'P';
		} else if (charGBK >= 50906 && charGBK <= 51386) {
			result = 'Q';
		} else if (charGBK >= 51387 && charGBK <= 51445) {
			result = 'R';
		} else if (charGBK >= 51446 && charGBK <= 52217) {
			result = 'S';
		} else if (charGBK >= 52218 && charGBK <= 52697) {
			result = 'T';
		} else if (charGBK >= 52698 && charGBK <= 52979) {
			result = 'W';
		} else if (charGBK >= 52980 && charGBK <= 53688) {
			result = 'X';
		} else if (charGBK >= 53689 && charGBK <= 54480) {
			result = 'Y';
		} else if (charGBK >= 54481 && charGBK <= 55289) {
			result = 'Z';
		} else {
			result = (char) (65 + (new Random()).nextInt(25));
		}
		if (!bUpCase) {
			result = Character.toLowerCase(result);
		}
		return result;
	}

	/**
	 * 打开选择成品商品面板
	 * 
	 * @param type
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("chooseProduct")
	public ModelAndView chooseProduct(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String goodsFileId = request.getParameter("goodsFileId");
		model.addAttribute("goodsFileId", goodsFileId);
		ModelAndView view = createIframeView("basic/goods_file/goods_file_choseGoods");
		return view;
	}

	/**
	 * 成品选择列表展示
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listProductGoodsJson", produces = "application/json")
	@ResponseBody
	public DataGrid listProductGoodsJson(@ModelAttribute GoodsFile goodsFile,
			String serial, String goodsName, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = goodsFileService.getProductGoodsList(page, goodsFile,
					serial, goodsName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 保存原材料对应的成品
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "saveCheck", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result saveCheck(@ModelAttribute GoodsFile goodsFile,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		Result ajaxResult = null;
		try {
			String goodsFileId = request.getParameter("goodsFileId");
			String id = request.getParameter("id");
			GoodsFile goods = (GoodsFile) goodsFileService.getObjById(
					goodsFileId, "GoodsFile");
			goods.setProductGoodsId(id);
			goodsFileService.updateObj(goods);
			ajaxResult = new Result(Status.OK, id);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new Result(Status.ERROR, "error");
		}
		return ajaxResult;
	}

}
