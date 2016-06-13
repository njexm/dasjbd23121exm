package com.proem.exm.controller.utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cisdi.ctp.utils.common.StringUtils;
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.basic.CommodityClassify.CommodityClassify;
import com.proem.exm.entity.basic.provider.ProviderInfo;
import com.proem.exm.entity.utils.Area;
import com.proem.exm.entity.utils.AreaNanJing;
import com.proem.exm.entity.utils.AreaNanJingService;
import com.proem.exm.entity.utils.AreaService;
import com.proem.exm.utils.JdbcUtil;
import com.proem.exm.utils.Result;
import com.proem.exm.utils.Result.Status;

@Controller
@RequestMapping("/area")
public class AreaController extends BaseController {

	@Autowired
	AreaService areaService;
	@Autowired
	AreaNanJingService areaNanJingService;

	@InitBinder("areaNanJing")
	public void areaNanJing(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("areaNanJing.");
	}

	@InitBinder("providerInfo")
	public void initUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("providerInfo.");
	}

	/**
	 * 方法说明：初始化行政区划
	 * 
	 * @param 参数名称
	 *            ：上级区划
	 * @return 返回值类型
	 * @Exception 异常对象
	 * 
	 * @author:zhuxinglu
	 * @date:2015年6月25日
	 */
	@ResponseBody
	@RequestMapping(value = "/initArea", method = RequestMethod.GET)
	public Result initArea(String parentId) {
		if (parentId == null || "".equals(parentId)) {
			String sql = "parentId = '100000' order by id asc";
			List<Area> areaList = areaService.getListByObj(Area.class, sql);
			return (null != areaList && areaList.size() > 0) ? new Result(
					Status.OK, areaList) : new Result(Status.ERROR, "");
		} else if (null != parentId && !"".equals(parentId)) {
			String sql = "parentId = '" + parentId + "' order by id asc";
			List<Area> areaList = areaService.getListByObj(Area.class, sql);
			return (null != areaList && areaList.size() > 0) ? new Result(
					Status.OK, areaList) : new Result(Status.ERROR, "");
		}
		return null;
	}

	@RequestMapping(value = "importArea")
	public void importArea(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Map<String, Object> listResult = getAllByExcel();
		List<AreaNanJing> listSupply = (List<AreaNanJing>) listResult
				.get("listArea");
		for (int i = 0; i < listSupply.size(); i++) {
			AreaNanJing supplyForecast = listSupply.get(i);
			areaNanJingService.saveObj(supplyForecast);
		}
	}

	/**
	 * 导入商品类别
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "importClass")
	public void importClass(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Connection con = JdbcUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "";
			sql = "SELECT item_clsno,item_flag,cls_parent,display_flag,return_rate,item_clsname,comp_rate,trans_flag FROM dbo.t_bd_item_cls";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CommodityClassify commodityClassify = new CommodityClassify();
				commodityClassify.setId(rs.getString(1).trim());
				commodityClassify.setClassify_name(rs.getString(6).trim());
				commodityClassify.setClassify_code(rs.getString(1).trim());
				commodityClassify.setParentPath(rs.getString(1).trim());
				commodityClassify.setClassify_type("1");
				if (rs.getString(3) == null || "".equals(rs.getString(3) )) {
					commodityClassify.setParentId("3");
				} else {
					commodityClassify.setParentId(rs.getString(3));
				}
				String parentPath = rs.getString(1).trim();
				if (parentPath.length() == 2) {
					commodityClassify.setClassify_level("1");
				} else if (parentPath.length() == 4) {
					commodityClassify.setClassify_level("2");
				} else if (parentPath.length() == 6) {
					commodityClassify.setClassify_level("3");
				}
				commodityClassify.setDelFlag("0");
				areaNanJingService.saveObj(commodityClassify);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}

	@RequestMapping(value = "importBrand")
	public void importBrand(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Connection con = JdbcUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "";
			sql = " SELECT item_brandno,item_flag,brand_parent,display_flag,return_rate,item_brandname,comp_rate,trans_flag FROM dbo.t_bd_item_brand ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CommodityClassify commodityClassify = new CommodityClassify();
				commodityClassify.setId(rs.getString(1).trim());
				commodityClassify.setClassify_name(rs.getString(6).trim());
				commodityClassify.setClassify_code(rs.getString(1).trim());
				commodityClassify.setClassify_type("2");
				commodityClassify.setClassify_level("1");
				commodityClassify.setDelFlag("0");
				areaNanJingService.saveObj(commodityClassify);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}
	@RequestMapping(value = "importProvider")
	public void importProvider(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Connection con = JdbcUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "";
			sql = " SELECT  supcust_no,supcust_flag,sup_name,region_no,sup_type,sup_man,sup_addr,zip,sup_email,sup_tel,sup_fax, "
					+ "	sup_acct_back,sup_acct_no,sup_tax_no,display_flag,check_out_flag,check_out_date, "
					+ "  check_out_day, credit_amt,sub_no,pay_flag,order_flag,purchase_day, "
					+ "po_cycle,acc_level,reg_type,oper_id,purchase_week,sale_way,shopcard,modify_date,settle_branch,com_flag,mobile,discount,trans_flag, "
					+ "vip_card_id,memo,receive_management,mbranch_no,use_way FROM dbo.t_bd_supcust_info ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				if (!"S".equals(rs.getString(2))) {
				} else {
					ProviderInfo providerInfo = new ProviderInfo();
					providerInfo.setId(rs.getString(1).trim());
					providerInfo.setCreateTime(dateFormat.parse(dateFormat
							.format(date)));
					providerInfo.setUpdateTime(dateFormat.parse(dateFormat
							.format(date)));
					if ("".equals(rs.getString(7)) || rs.getString(7) == null) {
						providerInfo.setAddress("");
					} else {
						providerInfo.setAddress(rs.getString(7).trim());
					}
					if (rs.getString(13) == null ||   "".equals(rs.getString(13))) {
						providerInfo.setAccount("");
					} else {
						providerInfo.setAccount(rs.getString(13).trim());
					}
					if ("".equals(rs.getString(3)) || rs.getString(3) == null) {
						providerInfo.setNickname("");
					} else {
						providerInfo.setNickname(rs.getString(3).trim());
					}
					if ("".equals(rs.getString(9)) || rs.getString(9) == null) {
						providerInfo.setMail("");
					} else {
						providerInfo.setMail(rs.getString(9).trim());
					}
					if (rs.getString(10) == null || "".equals(rs.getString(10))) {
						providerInfo.setTelephone("");
					} else {
						providerInfo.setTelephone(rs.getString(10).trim());
					}
					if (rs.getString(34) == null ||  "".equals(rs.getString(34))) {
						providerInfo.setMobilephone("");
					} else {
						providerInfo.setMobilephone(rs.getString(34).trim());
					}
					if (rs.getString(12) == null ||  "".equals(rs.getString(12))) {
						providerInfo.setBank("");
					} else {
						providerInfo.setBank(rs.getString(12).trim());
					}
					if ("1".equals(rs.getString(4))) {
						providerInfo.setArea("本地");
					} else {
						providerInfo.setArea("外地");
					}
					providerInfo.setDelFlag(rs.getString(15));
					if ("".equals(rs.getString(15)) || rs.getString(15) == null) {
						providerInfo.setTaxregistration("");
					} else {
						providerInfo.setTaxregistration(rs.getString(15).trim());
					}
					if ( "".equals(rs.getString(11)) || rs.getString(11) == null) {
						providerInfo.setFax("");
					} else {
						providerInfo.setFax(rs.getString(11).trim());
					}
					if (rs.getString(8) == null ||  "".equals(rs.getString(8))) {
						providerInfo.setPostcode("");
					} else {
						providerInfo.setPostcode(rs.getString(8).trim());
					}
					if (rs.getString(6) == null ||  "".equals(rs.getString(6))) {
						providerInfo.setLinkman("");
					} else {
						providerInfo.setLinkman(rs.getString(6).trim());
					}
					if ("1".equals(rs.getString(5))) {
						providerInfo.setEnterprisetype("国有");
					} else {
						providerInfo.setEnterprisetype("民营");
					}
					if ("A".equals(rs.getString(29))) {
						providerInfo.setPractice("购销");
					} else {
						providerInfo.setPractice("联营");
					}
					providerInfo.setFrozen("正常");
					providerInfo.setSettlement("总部");
					if (rs.getString(23) == null || "".equals(rs.getString(23))) {
						providerInfo.setDeliverycycle("");
					} else {
						providerInfo.setDeliverycycle(rs.getString(23));
					}
					if (rs.getString(18) == null ||  "".equals(rs.getString(18))) {
						providerInfo.setSettlementcycle("");
					} else {
						providerInfo.setSettlementcycle(rs.getString(18));
					}
					if (rs.getString(17) == null ||  "".equals(rs.getString(17))) {
						providerInfo.setSettlementdate("");
					} else {
						providerInfo.setSettlementdate(rs.getString(17));
					}
					if ("0".equals(rs.getString(21))) {
						providerInfo.setSettlementway("指定账期");
					} else {
						providerInfo.setSettlementway("货到付款");
					}
					areaNanJingService.saveObj(providerInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	@SuppressWarnings("deprecation")
	private Map<String, Object> getAllByExcel() {
		Map returnMap = new HashMap();
		List<AreaNanJing> list = new ArrayList<AreaNanJing>();
		try {
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(
					"D:/文档资料/众彩物流/设计文档/njexmArea.xls"));
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = null;// 对应excel的行
			HSSFCell cell = null;// 对应excel的列
			int totalRow = sheet.getLastRowNum();

			for (int i = 1; i <= totalRow; i++) {
				row = sheet.getRow(i);
				// System.out.println(row.getCell(0).toString());
				if (row != null) {
					AreaNanJing area = new AreaNanJing();
					// 品名
					HSSFCell id = row.getCell(0);
					double d = Double.valueOf(getValue(id));
					int Id = (int) d;
					area.setId(Id + "");
					HSSFCell name = row.getCell(1);
					area.setAreaName(getValue(name) == null ? ""
							: getValue(name));
					HSSFCell parentid = row.getCell(3);
					double parent = Double.valueOf(StringUtils
							.isBlank(getValue(parentid)) == true ? "0"
							: getValue(parentid));
					int parentId = (int) parent;
					area.setParentId(parentId + "");
					HSSFCell shortName = row.getCell(2);
					area.setShortName(getValue(shortName) == null ? ""
							: getValue(shortName));
					HSSFCell levelType = row.getCell(5);
					double level = Double.valueOf(StringUtils
							.isBlank(getValue(levelType)) == true ? "0"
							: getValue(levelType));
					int levelType1 = (int) level;
					area.setLevelType(levelType1 + "");
					HSSFCell realName = row.getCell(11);
					area.setRealname(getValue(realName) == null ? ""
							: getValue(realName));
					HSSFCell phone = row.getCell(12);
					area.setPhone(getValue(phone) == null ? ""
							: getValue(phone));
					HSSFCell dizhi = row.getCell(14);
					area.setAddress(getValue(dizhi) == null ? ""
							: getValue(dizhi));
					HSSFCell lng = row.getCell(13);
					area.setLng(getValue(lng) == null ? "" : getValue(lng));
					HSSFCell pinyin = row.getCell(15);
					area.setPinyin(getValue(pinyin) == null ? ""
							: getValue(pinyin));
					area.setCreateTime(new Date());
					area.setUpdateTime(new Date());
					list.add(area);
				}
			}
			returnMap.put("listArea", list);
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("static-access")
	private static String getValue(HSSFCell hssfCell) {
		if (hssfCell != null) {
			if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
				// 返回布尔类型的值
				return String.valueOf(hssfCell.getBooleanCellValue());
			} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
				// 返回数值类型的值
				return String.valueOf(hssfCell.getNumericCellValue());
			} else {
				// 返回字符串类型的值
				return String.valueOf(hssfCell.getStringCellValue());
			}
		} else {
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/listCountryJson", method = RequestMethod.GET)
	public Result listCountryJson(String parentId) {
		if (parentId == null || "".equals(parentId)) {
			String sql = "leveltype = '2' order by id asc";
			List<AreaNanJing> areaList = areaNanJingService.getListByObj(
					AreaNanJing.class, sql);
			return (null != areaList && areaList.size() > 0) ? new Result(
					Status.OK, areaList) : new Result(Status.ERROR, "");
		} else if (null != parentId && !"".equals(parentId)) {
			String sql = "parentId = '" + parentId + "' order by id asc";
			List<AreaNanJing> areaList = areaNanJingService.getListByObj(
					AreaNanJing.class, sql);
			return (null != areaList && areaList.size() > 0) ? new Result(
					Status.OK, areaList) : new Result(Status.ERROR, "");
		}
		return null;
	}

}
