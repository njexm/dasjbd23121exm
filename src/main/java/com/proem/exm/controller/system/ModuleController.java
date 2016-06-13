package com.proem.exm.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdi.ctp.model.gen.Module;
import com.cisdi.ctp.utils.common.StringUtils;
import com.cisdi.ctp.utils.common.UuidUtils;
import com.proem.exm.controller.BaseController;
import com.proem.exm.service.system.LogManageService;
import com.proem.exm.service.system.ModuleService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 菜单Controller
 */
@Controller
@RequestMapping("module")
public class ModuleController extends BaseController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private LogManageService logManageService;
	@InitBinder("module")
	public void initModule(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("module.");
	}
	/**
	 * 跳转到菜单列表页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/init")
	public ModelAndView init(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = createIframeView("system/module/module_list");
		return view;
	}
	
	@RequestMapping(value="list", produces="application/json")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	public Object list(@ModelAttribute Module module,HttpServletRequest request, HttpServletResponse response, Page page) throws Exception{
		Map map = new HashMap();
		List list = new ArrayList();
		String condition = "1=1";
		String moduleName = request.getParameter("moduleName");
//		String moduleType = request.getParameter("moduleType");
//		String parentId=request.getParameter("id");
		if(moduleName != "" && moduleName != null){
			condition += "and moduleName like '%"+moduleName+"%'";
		}
		if (!StringUtils.isBlank(module.getModuleName())) {
			condition += "and moduleName like '%"+module.getModuleName()+"%'";
		}
		if (!StringUtils.isBlank(module.getModuleType())) {
			condition += "and moduleType like '%"+module.getModuleName()+"%'";
		}
		if("0".equals(module.getId())){
			if (!StringUtils.isBlank(module.getId())) {
			condition += "and parentId = 'ROOT'";
				}
			page.setOrder(" order by updateTime DESC");
			page.setCondition(condition);
			DataGrid dataGrid = moduleService.getPagedDataGridObj(page,"");
			List<Map<String, Object>> rowList=dataGrid.getRows()==null?list:dataGrid.getRows();
			if(rowList!=null && rowList.size()>0){
				for(int i=0;i<rowList.size();i++){
					String id=rowList.get(i).get("ID").toString();
					List<Map<String, Object>> childList = moduleService.getModuleListByParent(id);
					if(childList!=null && childList.size()>0){
						for(int a=0;a<childList.size();a++){
							String childId=childList.get(a).get("ID").toString();
							List childchildList = moduleService.getModuleListByParent(childId);
							childList.get(a).put("children", childchildList);
						}
					}
					rowList.get(i).put("children", childList);
				}
			}
			map.put("rows", rowList);
			map.put("total", dataGrid.getTotal());
			return map;
		}else{
			List childList = moduleService.getModuleListByParent(module.getId());
			return childList;
		}
		
	}
	
	/**
	@RequestMapping(value="ztreelist",produces="application/json")
	@ResponseBody
	public List<Module> ztreelist(HttpServletRequest request,HttpServletResponse response){
		List<Module> list = moduleService.getModuleList();
		return list;
	}
	*/
	
	@RequestMapping(value="detail")
	public ModelAndView detail(HttpServletRequest request,HttpServletResponse response,String id){
		ModelAndView view = createIframeView("system/module/module_detail");
		Module module = null;
		if(!StringUtils.isBlank(id)){
			module = (Module) moduleService.getObjById(id, Module.class.getName());
		}
		view.addObject("module",module);
		return view;
	}
	
	//打开编辑页面
	@RequestMapping(value="edit")
	public ModelAndView edit(HttpServletRequest request,HttpServletResponse response,String id){
		Module module = null;
		module = (Module) moduleService.getObjById(id, Module.class.getName());
		ModelAndView view = createIframeView("system/module/module_edit");
		view.addObject("module",module);
		return view;
	}
	
	// 通过ID查询单条数据
	@RequestMapping(value = "queryById", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Module queryById(String id) {
		// 保存数据
		Module module = null;
		try {
			module = (Module) moduleService.getObjById(id, Module.class.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return module;
	}
	
	// 保存数据
	@RequestMapping(value = "save", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public AjaxResult save(HttpServletRequest request, HttpServletResponse response, Module module) {
		AjaxResult ajaxResult = null;
		try {
			String id = module.getId();
			if(!StringUtils.isBlank(module.getParentID())){
			}else{
				module.setParentID("ROOT");
			}
			if(!StringUtils.isBlank(id)){
				module.setModuleType("MODULE");
				moduleService.updateObj(module);
				logManageService.insertLog(request, "修改了一条模块信息", "模块管理");
			}else{
				id = UuidUtils.getUUID();
				module.setId(id);
				module.setModuleType("MODULE");
				moduleService.saveObj(module);
				logManageService.insertLog(request, "添加了一条模块信息", "模块管理");
			}
//			moduleService.saveObj(module);
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS, AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL, AjaxResult.INFO);
		}
		return ajaxResult;
	}

	// 删除
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public AjaxResult delete(HttpServletRequest request,HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			for(int i = 0;i < ids.length;i++){
				moduleService.deleteObjById(ids[i], Module.class.getName());	
			}
			logManageService.insertLog(request, "删除了模块信息", "模块管理");
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS, AjaxResult.INFO);	
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL, AjaxResult.INFO);
		}
		return ajaxResult;
	}
	
	@RequestMapping(value = "getRootModuleTreeData")
	@ResponseBody
	public List<Map<String,Object>> getRootModuleTreeData(HttpServletRequest request,HttpServletResponse response){
		return moduleService.getRootModuleTreeData();
	}
	
	@RequestMapping(value = "getModuleTreeData")
	@ResponseBody
	public List<Map<String,Object>> getModuleTreeData(HttpServletRequest request,HttpServletResponse response){
		return moduleService.getModuleTreeData();
	}
	@RequestMapping(value = "getModuleTreeDataList")
	@ResponseBody
	public List<Map<String,Object>> getModuleTreeDataList(HttpServletRequest request,HttpServletResponse response){
		return moduleService.getModuleTreeDataList();
	}
	
}
