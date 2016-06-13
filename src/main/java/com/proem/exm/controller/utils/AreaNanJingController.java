package com.proem.exm.controller.utils;

import java.io.UnsupportedEncodingException;
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
import com.proem.exm.entity.utils.AreaNanJing;
import com.proem.exm.entity.utils.AreaNanJingService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

@Controller
@RequestMapping("areaNanJing")
public class AreaNanJingController extends BaseController {
	@Autowired
	AreaNanJingService areaNanJingService;
	
	@InitBinder("areaNanJing")
	public void associator(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("areaNanJing.");
	}
	
	/**
	 * 新增展示页面
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
			 sonName="区域管理";
		 }else{
		sonName= new String(sonName.getBytes("iso8859-1"),"utf-8"); }
		String title="  "+faName+" > "+fasonName+" > "+sonName+"";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("basic/areananjing/areananjing_list");
		return view;
		}
	
	/**
	 * 新增 区域管理页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addAreaNanJing")
	public ModelAndView addAreaNanJing(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		//从请求中获取'id'的值
		String id = request.getParameter("id");
		//通过ID找到网页上的值并且存到对象中
		AreaNanJing areaNanJing = (AreaNanJing) areaNanJingService.getObjById(id, "AreaNanJing");
		//将对象取别名方便后续网页中EL表达式来取值
		model.addAttribute("AreaNanJing", areaNanJing);
		ModelAndView view = createIframeView(
				"basic/areananjing/areananjing_add");
		return view;
	}
	
	/**
	 *  编辑会员信息页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "editAreaNanJing")
	public ModelAndView editAreaNanJing(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		AreaNanJing areaNanJing = (AreaNanJing) areaNanJingService.getObjById(id, "AreaNanJing");
		model.addAttribute("AreaNanJing", areaNanJing);
		ModelAndView view = createIframeView(
				"basic/areananjing/areananjing_edit");
		return view;
	}

	/**
	 * 区域基本信息列表/可按条件查询
	 * @param areaNanJing
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listAreaNanJingJson", produces = "application/json")
	@ResponseBody
	public Object listAreaNanJingJson(@ModelAttribute AreaNanJing areaNanJing,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			if("0".equals(areaNanJing.getId())){
				dataGrid = areaNanJingService.getPagedDataGridObj(page, areaNanJing);
				List<Map<String,Object>> list = dataGrid.getRows();
				if(list !=null&&list.size()>0){
					for (int i = 0; i < list.size(); i++) {
						list.get(i).put("state", "closed");
					}
					dataGrid.setRows(list);
				}
				return dataGrid;
			}else{
				String id = areaNanJing.getId();
				List<Map<String,Object>> children = areaNanJingService.getListByParent(id);
				if(children!=null&&children.size()>0){
					for (int i = 0; i < children.size(); i++) {
						if("1".equals(children.get(i).get("LEVELTYPE"))){
							children.get(i).put("state", "closed");
						}else if("2".equals(children.get(i).get("LEVELTYPE"))){
							children.get(i).put("state", "closed");
						}else{
							children.get(i).put("state", "open");
						}
					}
				}
				return  children;
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 *  新增区域信息
	 * @param areaNanJing
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult save(@ModelAttribute AreaNanJing areaNanJing,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String id = UuidUtils.getUUID();			
			areaNanJing.setId(id);
			String parentId = areaNanJing.getParentId();
			AreaNanJing areaNanJingParent = (AreaNanJing) areaNanJingService.getObjById(parentId, "AreaNanJing");
			if(areaNanJingParent!=null){
			if("2".equals(areaNanJingParent.getLevelType())){
				areaNanJing.setLevelType("3");
			}else if("0".equals(areaNanJingParent.getParentId())){
				areaNanJing.setLevelType("1");
			}else{
				areaNanJing.setLevelType("2");
			}
			}
			areaNanJingService.saveObj(areaNanJing);
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
	 * @param areaNanJing
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult update(@ModelAttribute AreaNanJing areaNanJing,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String parentId = areaNanJing.getParentId();
			AreaNanJing areaNanJingParent = (AreaNanJing) areaNanJingService.getObjById(parentId, "AreaNanJing");
			if(areaNanJingParent!=null){
			if("2".equals(areaNanJingParent.getLevelType())){
				areaNanJing.setLevelType("3");
			}else if("0".equals(areaNanJingParent.getParentId())){
				areaNanJing.setLevelType("1");
			}else{
				areaNanJing.setLevelType("2");
			}
			}
			areaNanJingService.updateObj(areaNanJing);
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
	public AjaxResult deleteAreaNanJing(HttpServletRequest request) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String[] id = ids.split(",");
		if (id != null && id.length > 0) {
			for (int i = 0; i < id.length; i++) {
				try {
					String Id = id[i];
					areaNanJingService.deleteObjById(Id, "AreaNanJing");
				} catch (Exception e) {
					e.printStackTrace();
					ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
							AjaxResult.INFO);
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
	
	/**
	 * 编辑页面展示父级菜单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getTreeData")
	@ResponseBody
	public List<Map<String,Object>> getTreeData(HttpServletRequest request,HttpServletResponse response){
		return areaNanJingService.getTreeData();
	}

}
