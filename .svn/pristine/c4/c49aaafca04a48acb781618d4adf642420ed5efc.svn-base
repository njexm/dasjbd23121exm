package com.proem.exm.controller.basic.associator;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdi.ctp.utils.common.StringUtils;
import com.cisdi.ctp.utils.common.UuidUtils;
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.basic.CommodityClassify.CommodityClassify;
import com.proem.exm.entity.basic.associator.Associator;
import com.proem.exm.entity.basic.branch.Branch;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.order.ZcOrderItem;
import com.proem.exm.entity.system.ZcZoning;
import com.proem.exm.service.basic.associator.AssociatorService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.JdbcUtil;
import com.proem.exm.utils.Page;

/**
 * 会员管理
 * @author ZuoYM
 *
 */
@Controller
@RequestMapping("associator/associator")
public class AssociatorController extends BaseController {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	AssociatorService associatorService;
	
	@InitBinder("associator")
	public void associator(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("associator.");
	}

	/**
	 *  初始化跳转页面
	 * @param request
	 * @param response
	 * @param model 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "init")
	public ModelAndView init(HttpServletRequest request,
			HttpServletResponse response, Model model) throws UnsupportedEncodingException {
		String faName=request.getParameter("faName");
		 if(StringUtils.isBlank(faName)){
			 faName="基础档案";
		 }else{
		 faName= new String(faName.getBytes("iso8859-1"),"utf-8"); 
		 }
		String fasonName=request.getParameter("fasonName");
		 if(StringUtils.isBlank(fasonName)){
			 fasonName="档案管理";
		 }else{
		fasonName= new String(fasonName.getBytes("iso8859-1"),"utf-8"); 
		 }
		String sonName=request.getParameter("sonName");
		 if(StringUtils.isBlank(sonName)){
			 sonName="会员管理";
		 }else{
		sonName= new String(sonName.getBytes("iso8859-1"),"utf-8"); }
		String title="  "+faName+" > "+fasonName+" > "+sonName+"";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("basic/associator/associator_list");
		return view;
	}

	/**
	 *  跳转新增会员信息页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addAssociator")
	public ModelAndView addAssociator(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = createIframeView("basic/associator/associator_add");
		return view;
	}
	
	/**
	 *  跳转编辑会员信息页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "editAssociator")
	public ModelAndView editAssociator(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		Associator associator = (Associator) associatorService.getObjById(id, "Associator");
		model.addAttribute("Associator", associator);
		ModelAndView view = createIframeView(
				"basic/associator/associator_edit");
		return view;
	}
	
	/**
	 *  跳转查看页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detailAssociator")
	public ModelAndView detailAssociator(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		Associator associator = (Associator) associatorService.getObjById(id, "Associator");
		model.addAttribute("Associator", associator);
		ModelAndView view = createIframeView(
				"basic/associator/associator_detail");
		return view;
	}
	
	/**
	 *  会员基本信息列表/可按条件查询
	 * @param associator
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listAssociatorJson", produces = "application/json")
	@ResponseBody
	public DataGrid listAssociatorJson(@ModelAttribute Associator associator,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = associatorService.getPagedDataGridObj(page, associator);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 *  新增会员信息
	 * @param associator
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult save(@ModelAttribute Associator associator,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String id = UuidUtils.getUUID();
			String cardid = UuidUtils.getUUID();
			associator.setId(id);
			associator.setAssociator_Cardid(cardid);
			associator.setDelFlag("0");
			associatorService.saveObj(associator);
			System.out.println("会员ID:"+associator.getAssociator_Cardid()+"\n会员年龄："+associator.getAssociator_Age()+"\n证件："+associator.getAssociator_Certificate()+"\n性别："+associator.getAssociator_Sex()+"\n卡状态："+associator.getAssociator_State()+"\n积分有效期："+associator.getAssociator_CreditStartDate()+"\n积分有效期："+associator.getAssociator_CreditValidityDate()+"\n.");
			logManageService.insertLog(request, "新增了会员信息", "会员管理");
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
	 * 编辑会员信息
	 * @param associator
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult update(@ModelAttribute Associator associator,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			associatorService.updateObj(associator);
			System.out.println("会员ID:"+associator.getAssociator_Cardid()+"\n会员年龄："+associator.getAssociator_Age()+"\n证件："+associator.getAssociator_Certificate()+"\n性别："+associator.getAssociator_Sex()+"\n卡状态："+associator.getAssociator_State()+"\n积分有效期-1："+associator.getAssociator_CreditStartDate()+"\n积分有效期-2："+associator.getAssociator_CreditValidityDate()+"\n.");
			logManageService.insertLog(request, "编辑了会员信息", "会员管理");
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
	 *  删除会员信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult deleteBranch(HttpServletRequest request) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String[] id = ids.split(",");
		if (id != null && id.length > 0) {
			for (int i = 0; i < id.length; i++) {
				try {
					String Id = id[i];
					associatorService.deleteObjById(Id, "Associator");
				} catch (Exception e) {
					e.printStackTrace();
					ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
							AjaxResult.INFO);
					return ajaxResult;
				}
			}
			logManageService.insertLog(request, "删除了会员信息", "会员管理");
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
			return ajaxResult;
		}
		ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
				AjaxResult.INFO);
		return ajaxResult;
	}
	
	@RequestMapping(value="/importMember", method = RequestMethod.GET)
	@ResponseBody
	public void InsertItem() {
		Connection con = JdbcUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		try {
				sql = "SELECT b.value,a.member_id,a.name,a.member_lv_id,a.point_history,a.point_freeze,a.point,a.advance,a.advance_freeze , "
						+ "FROM_UNIXTIME(a.regtime,'%Y-%m-%d %H:%i:%s'),a.sex,a.wedlock,(a.`b_year`+a.`b_month`+a.`b_day`) AS birthday,a.tel,a.mobile,a.email,c.addr,a.zip,a.remark FROM sdb_b2c_members a "
						+ "LEFT JOIN sdb_dbeav_meta_value_varchar b ON b.pk=a.member_id  "
						+ "LEFT JOIN sdb_b2c_member_addrs c ON c.member_id=a.member_id ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			while (rs.next()) {
					Associator associator = new Associator();
					associator.setAssociator_CardNumber(rs.getString(1));
					associator.setId(rs.getString(2));
					associator.setAssociator_Name(rs.getString(3));
					associator.setAssociator_Category(rs.getString(4));
					associator.setAssociator_AccumulatedCredit(rs.getInt(5));
					associator.setAssociator_UsedCredit(rs.getInt(6));
					associator.setAssociator_Credit(rs.getInt(7));
					associator.setAssociator_Amount(rs.getDouble(8));
					associator.setAssociator_ConsumeAmount(rs.getDouble(9));
					Date regDate = sdf.parse(rs.getString(10));
					associator.setAssociator_AdmissionDate(regDate);
					associator.setAssociator_Sex(rs.getString(11));
					associator.setAssociator_MaritalStatus(rs.getString(12));
					// 设置生日
					// associator.setAssociator_Birthday(rs.getDate(13));
					associator.setAssociator_Telephone(rs.getString(14));
					associator.setAssociator_Mobilephone(rs.getString(15));
					associator.setAssociator_Email(rs.getString(16));
					associator.setAssociator_Address(rs.getString(17));
					associator.setAssociator_Zipcode(rs.getString(18));
					associator.setAssociator_Note(rs.getString(19));
					associatorService.saveObj(associator);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
