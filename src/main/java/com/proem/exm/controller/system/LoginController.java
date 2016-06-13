package com.proem.exm.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdi.ctp.auth.po.Role;
import com.cisdi.ctp.auth.po.User2Role;
import com.cisdi.ctp.model.gen.Module;
import com.cisdi.ctp.utils.common.StringUtils;
import com.cisdi.ctp.web.security.service.SecurityService;
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.order.ZcOrderDigits;
import com.proem.exm.entity.purchase.PurchaseOrder;
import com.proem.exm.entity.purchase.PurchaseReceive;
import com.proem.exm.entity.purchase.PurchaseReturn;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.service.system.CtpUserService;
import com.proem.exm.service.system.ModuleService;
import com.proem.exm.service.system.ZcUserInfoService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.GetIpAddressUtil;
import com.proem.exm.utils.MD5Util;

@Controller
@RequestMapping(value = "login")
public class LoginController extends BaseController {
	@Autowired
	private CtpUserService userService;
	@Autowired
	private ZcUserInfoService userInfoService;
	@Autowired
	private ModuleService moduleService;
	@Resource
	private SecurityService securityService;

	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = this.createSingleView("login/login");
		view.addObject("resource_path", "common/resource.vm");
		view.addObject("init_path", "common/init.vm");
		return view;
	}

	/**
	 * 登录验证
	 * 
	 * @author ChanJhan
	 * @date 2015年7月16日
	 */
	@RequestMapping(value = "login")
	@ResponseBody
	public AjaxResult loginCheck(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, String name,
			String password) {
		String checked = request.getParameter("checked");
		List<ZcOrderDigits> list1 = userService.getListByObj(ZcOrderDigits.class, " id = '123456789' ");
		ZcOrderDigits zcOrderDigits = list1.get(0);
		String countOdsi = zcOrderDigits.getCount_odsi();
		String accuracy = zcOrderDigits.getMoneyAccuracy();
		Cookie cookieName = new Cookie("userName",name);
		Cookie cookiePwd = new Cookie("userPwd",password);
//		cookieName.setValue(countOdsi);
//		cookiePwd.setValue(accuracy);
		Cookie numAccuracy = new Cookie("countOdsi", countOdsi);
		Cookie moneyAccuracy1 = new Cookie("accuracy", accuracy);
		try {
			CtpUser user = (CtpUser) userService.getObjByName(name,CtpUser.class);
			
			if (user != null && !StringUtils.isBlank(user.getName())&& !StringUtils.isBlank(user.getPassword())) {
				System.out.print("密码：" + MD5Util.stringToMD5("888888"));
				password = MD5Util.stringToMD5(password);
				if (password.equals(user.getPassword())) {
					// 登录成功
					if ("true".equals(checked)) {//复选框是否被选中
						//设定有效时间 以秒(s)为单位,记住一周
						cookieName.setMaxAge(7*24*60*60);
						cookiePwd.setMaxAge(7*24*60*60);
						}else {
							cookieName.setMaxAge(0);
							cookiePwd.setMaxAge(0);
						}
					//设置Cookie路径和域名
					cookiePwd.setPath("/") ;
					cookieName.setPath("/") ;
					numAccuracy.setPath("/") ;
					moneyAccuracy1.setPath("/") ;
					response.addCookie(cookieName);
					response.addCookie(cookiePwd);
					response.addCookie(numAccuracy);
					response.addCookie(moneyAccuracy1);
					request.setAttribute("userName",name);
					request.setAttribute("userPwd",password);
					request.setAttribute("countOdsi",zcOrderDigits.getCount_odsi());
					request.setAttribute("moneyAccuracy",zcOrderDigits.getMoneyAccuracy());
					session.removeAttribute("user");
					session.removeAttribute("IP");
					session.removeAttribute("userInfo");
					session.removeAttribute("security");
					session.removeAttribute("securitySon");
					session.setAttribute("user", user);
					String userId = user.getId() == null ? "" : user.getId();
					ZcUserInfo userInfo = userInfoService.getUserInfoById(userId);
					session.setAttribute("userInfo", userInfo);
					String IP = GetIpAddressUtil.getRemortIP(request);
					session.setAttribute("IP", IP);
					securityService.securityInterface(user.getName(), request,response, session);
					// 加载菜单权限
					List<Module> list = moduleService.getModuleByUserId(user
							.getId());
					session.setAttribute("security", list);
					return new AjaxResult(AjaxResult.SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult(AjaxResult.FAIL);
	}

	@RequestMapping(value = "logout")
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("userInfo");
		session.removeAttribute("IP");
		session.removeAttribute("security");
		session.removeAttribute("securitySon");
		// Enumeration em = session.getAttributeNames();
		// while (em.hasMoreElements()) {
		// System.out.println("session attr has : "+em.nextElement().toString());
		// session.removeAttribute(em.nextElement().toString());
		// }
		session.invalidate();
		ModelAndView view = new ModelAndView("redirect:/login/index");
		return view;
	}

	@RequestMapping("updateLog")
	public ModelAndView updateLog(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = createIframeView("common/updateLog");
		return view;
	}
	

}
