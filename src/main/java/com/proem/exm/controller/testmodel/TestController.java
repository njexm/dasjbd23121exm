package com.proem.exm.controller.testmodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.proem.exm.entity.TestTable;
import com.proem.exm.service.testmodel.TestService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

@Controller
//(模块名/类名)
@RequestMapping("testmodel/test")
public class TestController extends BaseController {

	@Autowired
	private TestService testService;

	// 初始化跳转页面
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView("system/user");
		return view;
	}

	// 分页查询页面
	@RequestMapping(value = "pageList", produces = "application/json")
	@ResponseBody
	public DataGrid list(HttpServletRequest request,
			HttpServletResponse response, Page page) {
		String condition = " 1=1 ";
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtil.validate(name)) {
			// condition += " and name like '%"+name+"%'";
			paramMap.put("name", name);
		}
		page.setCondition(condition);
		DataGrid dataGrid = testService.getPagedDataGridObj(page, paramMap);
		return dataGrid;
	}

	@RequestMapping(value = "allList", produces = "application/json")
	@ResponseBody
	public List list(HttpServletRequest request, HttpServletResponse response) {
		List list = testService.getObjList();
		return list;
	}

	// 保存数据
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult save(HttpServletRequest request,
			HttpServletResponse response, TestTable testTable) {
		AjaxResult ajaxResult = null;
		try {
			
			//test search
			Map<String, Object> list = testService.getObjById("1111"); 
			if(list != null && list.size() > 0){
				
			}
			
			String id = testTable.getId();
			if (StringUtil.validate(id)) {
				testTable.setId(id);
				testService.updateObj(testTable);
			} else {
				id = UuidUtils.getUUID();
				testTable.setId(id);
				testService.saveObj(testTable);
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

	// 修改数据
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult update(HttpServletRequest request,
			HttpServletResponse response, TestTable testTable) {
		AjaxResult ajaxResult = null;
		try {
			testService.updateObj(testTable);
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
			// testService.deleteObjById(id, TestTable.class.getName());
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
