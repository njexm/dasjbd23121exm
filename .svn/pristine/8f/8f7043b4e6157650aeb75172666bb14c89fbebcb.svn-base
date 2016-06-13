package com.proem.exm.controller.system;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

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
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.system.Notice;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.service.system.NoticeService;
import com.proem.exm.service.system.ZcUserInfoService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 公告管理
 * 
 * @author psl
 * 
 * @com proem
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {

	@Autowired
	NoticeService noticeService;
	@Autowired
	ZcUserInfoService userInfoService;

	@InitBinder("notice")
	public void initRole(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("notice.");
	}

	/**
	 * 初始化跳转页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/init")
	public ModelAndView init(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
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
			sonName = "公告管理";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("system/notice/notice_list");
		return view;
	}

	/**
	 * 新增公告页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "add")
	public ModelAndView addUsePage(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = createIframeView("system/notice/notice_add");
		return view;
	}

	/**
	 * 编辑
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "edit")
	public ModelAndView editUsePage(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		Notice notice = (Notice) noticeService.getObjById(id, "Notice");
		String userId = notice.getUser().getId();
		ZcUserInfo userInfo = userInfoService.getUserInfoById(userId);
		model.addAttribute("notice", notice);
		model.addAttribute("userInfo", userInfo);
		ModelAndView view = createIframeView("system/notice/notice_edit");
		return view;
	}

	@RequestMapping(value = "openDetail")
	public ModelAndView openDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		Notice notice = (Notice) noticeService.getObjById(id, "Notice");
		String userId = notice.getUser().getId();
		ZcUserInfo userInfo = userInfoService.getUserInfoById(userId);
		model.addAttribute("notice", notice);
		model.addAttribute("userInfo", userInfo);
		ModelAndView view = createIframeView("system/notice/notice_detail");
		return view;
	}

	/**
	 * 供应商基本信息列表展示
	 * 
	 * @param branchsView
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listJson", produces = "application/json")
	@ResponseBody
	public DataGrid listBranchsJson(@ModelAttribute Notice notice,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = noticeService.getPagedDataGridObj(page, notice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 新增数据
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult save(@ModelAttribute Notice notice,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String roleId = String.valueOf(UUID.randomUUID()).replace("-", "");
			notice.setId(roleId);
			CtpUser user = (CtpUser) request.getSession().getAttribute("user");
			notice.setUser(user);
			notice.setDelflag("0");
			noticeService.saveObj(notice);
			logManageService.insertLog(request, "添加了一条公告信息", "系统管理");
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
	 * 修改数据
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult updateUserInfo(@ModelAttribute Notice notice,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			CtpUser user = (CtpUser) request.getSession().getAttribute("user");
			notice.setUser(user);
			notice.setDelflag("0");
			noticeService.updateObj(notice);
			logManageService.insertLog(request, "修改了公告信息", "系统管理");
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	/**
	 * 删除数据
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteInfo(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		AjaxResult ajaxResult = null;
		try {

			// ctpUser.setPassword(MD5Util.stringToMD5("888888"));
			// zcUserInfo.getZcZoning().setId(zcZoning.getId());
			// zcUserInfo.getCtpUser().setId(ctpUser.getId());
			noticeService.deleteObjById(id, "Notice");
			logManageService.insertLog(request, "删除了公告信息", "系统管理");
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
		result = getNoticeList(result);
		return result;
	}

	/**
	 * 获取采购管理中的条数
	 * 
	 * @param roleCode
	 * @return
	 */
	private String getNoticeList(String result) {
		// 得到公告列表
		List<Notice> NoticeList = noticeService.getListByObj(Notice.class,
				"delflag='0'");
		if (NoticeList != null && NoticeList.size() > 0) {
			for (int i = 0; i < NoticeList.size(); i++) {
				Notice notice = NoticeList.get(i);
				if (StringUtils.isBlank(result)) {
					result = "&nbsp;&nbsp;&nbsp;· <a href='#' onclick=noticeDetail('"
							+ notice.getId()
							+ "') ><span style='color:blue'>"
							+ notice.getTitle() + "</span></a> </br>";
				} else {
					result += "&nbsp;&nbsp;&nbsp;· <a href='#' onclick=noticeDetail('"
							+ notice.getId()
							+ "') ><span style='color:blue'>"
							+ notice.getTitle() + "</span></a> </br>";
				}
			}
		} else {
			return "暂无通知";
		}
		return result;
	}

	/**
	 * 删除选中的公告信息
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteNotice", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteNotice(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				noticeService.deleteObjById(ids[i], Notice.class.getName());
			}
			logManageService.insertLog(request, "删除了公告信息", "系统管理");
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
	 * 置为无效
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "setThrow", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult setThrow(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String noticeId = request.getParameter("id");
			Notice notice = (Notice) noticeService.getObjById(noticeId,
					"Notice");
			notice.setDelflag("1");
			noticeService.updateObj(notice);
			logManageService.insertLog(request, "将一条公告信息置为无效", "系统管理");
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

}
