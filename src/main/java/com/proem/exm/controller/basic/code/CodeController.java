package com.proem.exm.controller.basic.code;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdi.ctp.utils.common.StringUtils;
import com.cisdi.ctp.utils.common.UuidUtils;
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.basic.branch.Branch;
import com.proem.exm.entity.basic.code.Code;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.service.basic.code.CodeService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.Result;
import com.proem.exm.utils.Result.Status;

/**
 * @author DeFei 分店管理
 */

@Controller
@RequestMapping("code")
public class CodeController extends BaseController {

	@Autowired
	CodeService codeService;
	@InitBinder("code")
	public void initUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("code.");
	}

	/**
	 * 分店初始化页面
	 * 
	 * @param request
	 * @param response
	 * @param model 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "init")
	public ModelAndView index(HttpServletRequest request,
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
			 sonName="数据字典";
		 }else{
		sonName= new String(sonName.getBytes("iso8859-1"),"utf-8"); }
		String title="  "+faName+" > "+fasonName+" > "+sonName+"";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("basic/code/code_list");
		return view;
	}

	/**
	 * 新增分店页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "add")
	public ModelAndView addCode(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = createIframeViewIncludeMap(
				"basic/code/code_add", initLoadPage());
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
	public ModelAndView editCode(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		Code bv = (Code) codeService.getObjById(id, "Code");
		Code parent = (Code) codeService.getObjById(bv.getParent(), "Code");
		model.addAttribute("code", bv);
		model.addAttribute("parent", parent);
		ModelAndView view = createIframeViewIncludeMap("basic/code/code_edit", initLoadPage());
		return view;
	}
	
	/**
	 * 初始化字典表
	 * @param codeType
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/initCode",method = RequestMethod.GET)
	public Result initCode(String codeType)
	{
		Result result = codeService.initCode(codeType);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/initCodeList", produces = "application/json")
	public List initCodeList(String codeType)
	{
		List  result = codeService.findByType(codeType);
		return result;
	}

	/**  
	 * 数据字典---验证
	 *
	 * @author PSL
	 * @version 1.0  
	 */
	@ResponseBody
	@RequestMapping(value="/checkDictionary", method = RequestMethod.POST)
	public Result checkDictionary(@ModelAttribute Code code) {
		Result result = null;
		try {
			result = codeService.checkDictionary(code);
		} catch (Exception e) {
			return new Result(Status.ERROR,e.getMessage());
		}
		return result;
	}
	
	/**  
	 * 数据字典---保存或更新
	 *
	 * @author PSL
	 * @version 1.0  
	 */
	@ResponseBody
	@RequestMapping(value="/saveOrUpdateDictionary", method = RequestMethod.POST)
	public Result saveOrUpdateDictionary(@ModelAttribute Code code,HttpServletRequest request) {
		Result result = new Result();
		try {
			codeService.saveOrUpdateDictionary(request,code);
		} catch (Exception e) {
			return new Result(Status.ERROR,e.getMessage());
		}
		result.setStatus(Status.OK);
		return result;
	}
	
	
	/**  
	 * 数据字典---删除
	 *
	 * @author PSL
	 * @version 1.0  
	 */
	@ResponseBody
	@RequestMapping(value="/deleteDictionary", method = RequestMethod.GET)
	public Result deleteDictionary(@ModelAttribute Code code) {
		Result result = new Result();
		try {
			// 子节点
			codeService.deleteSelfAndchild(code.getId());
		} catch (Exception e) {
			return new Result(Status.ERROR,e.getMessage());
		}
		result.setStatus(Status.OK);
		return result;
	}
	
	
	/**  
	 * 所属上级
	 *
	 * @author PSL  
	 * @version 1.0    
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map initLoadPage(){
		Map returnMap = new HashMap();
		// 所属上级
		List<Code> dictionaryList = codeService.getAllParent();
		returnMap.put("dictionaryList", dictionaryList);
		return returnMap;
	}

	/**
	 * 列表展示
	 * 
	 * @param branchsView
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listJson", produces = "application/json")
	@ResponseBody
	public Object listBranchsJson(@ModelAttribute Code code,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			if("0".equals(code.getId())){
				dataGrid = codeService.getPagedDataGridObj(page, code);
				List<Map<String,Object>> list=dataGrid.getRows();
				if(list!=null && list.size()>0){
					for(int i=0;i<list.size();i++){
						list.get(i).put("state", "closed");
					}
					dataGrid.setRows(list);
				}
				return dataGrid;
				}else{
					String id=code.getId();
					List<Map<String,Object>> children=codeService.getListByParent(id);
					if(children!=null && children.size()>0){
						for(int i=0;i<children.size();i++){
								children.get(i).put("state", "open");
							
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
	 * 新增数据
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult save(@ModelAttribute Code code,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String id = UuidUtils.getUUID();
			code.setId(id);
			// String userId=branchsView.getBranch_person_name();
			// ZcUserInfo userInfo=(ZcUserInfo)
			// zcUserInfoService.getObjById(userId,"ZcUserInfo");
			// branch.setCustomer(userInfo);
			// 负责人暂未处理
			codeService.saveObj(code);
			logManageService.insertLog(request, "添加了一条字典信息", "字典管理");
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult update(@ModelAttribute Branch branch,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
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
	 * 方法说明：(公司管理删除)
	 * 
	 * @param 参数名称
	 *            ：参数含义
	 * @return 返回值类型
	 * @Exception 异常对象
	 * 
	 * @author:PSL
	 * @date:2015年6月5日
	 */
	@RequestMapping(value = "/deleteBranch", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult deleteBranch(HttpServletRequest request) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String[] id = ids.split(",");
		if (id != null && id.length > 0) {
			for (int i = 0; i < id.length; i++) {
				try {
					String Id = id[i];
				} catch (Exception e) {
					e.printStackTrace();
					ajaxResult = new AjaxResult(AjaxResult.DELETE,
							AjaxResult.FAIL, AjaxResult.INFO);
					return ajaxResult;
				}

			}
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
			return ajaxResult;
		}
		ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
				AjaxResult.INFO);
		return ajaxResult;
	}

	// 删除
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult delete(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			if(id != null || id != ""){
				codeService.deleteObjById(id,Code.class.getName());
				logManageService.insertLog(request, "删除了选中数据值", "数据字典");
				ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
						AjaxResult.INFO);
			}
		} catch (Exception e) {
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
			return ajaxResult;
		}
		return ajaxResult;
	}


}
