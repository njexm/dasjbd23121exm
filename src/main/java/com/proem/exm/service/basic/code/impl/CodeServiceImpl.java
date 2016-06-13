package com.proem.exm.service.basic.code.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisdi.ctp.utils.common.UuidUtils;
import com.proem.exm.dao.basic.code.CodeDao;
import com.proem.exm.entity.basic.code.Code;
import com.proem.exm.service.basic.code.CodeService;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.system.LogManageService;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.Result;
import com.proem.exm.utils.Result.Status;
import com.proem.exm.utils.StringUtil;

@Service("codeService")
public class CodeServiceImpl extends BaseServiceImpl implements CodeService{
	private static Logger logger = Logger.getLogger(CodeServiceImpl.class);
	@Autowired CodeDao codeDao;
	@Autowired
	LogManageService logManageService;

	private String joinConditionStr(Object obj) {
		Code bv = (Code) obj;
		String conditions = "";
		if (StringUtil.validate(bv.getId())) {
		if("0".equals(bv.getId())){
			if(com.cisdi.ctp.utils.common.StringUtils.isBlank(conditions)){
				conditions += "  a.parent = '' or a.parent is NULL";
			}else{
				conditions += " and a.parent = '' or a.parent is NULL";
			}
		}
		}
		if (StringUtil.validate(bv.getCodeName())) {
			if(com.cisdi.ctp.utils.common.StringUtils.isBlank(conditions)){
				conditions += "a.codename = '" + bv.getCodeName()
						+ "'";
			}else{
			conditions += " and a.codename = '" + bv.getCodeName()
					+ "'";
			}
		}
		if (StringUtil.validate(bv.getCodeType())) {
			if(com.cisdi.ctp.utils.common.StringUtils.isBlank(conditions)){
				conditions += "  a.codetype = '" + bv.getCodeType()
						+ "'";
			}else{
				conditions += " and a.codetype = '" + bv.getCodeType()
						+ "'";
			}
			
		}
		if (StringUtil.validate(bv.getParent())) {
			if(com.cisdi.ctp.utils.common.StringUtils.isBlank(conditions)){
				conditions += "  a.parent = '" + bv.getParent()
						+ "'";
			}else{
				conditions += " and a.parent = '" + bv.getParent()
						+ "'";
			}
			
		}
		if (StringUtil.validate(bv.getCodeValue())) {
			if(com.cisdi.ctp.utils.common.StringUtils.isBlank(conditions)){
				conditions += "  a.codevalue = '" + bv.getCodeValue()
						+ "'";
			}else{
				conditions += " and a.codevalue = '" + bv.getCodeValue()
						+ "'";
			}
			
		}
		return conditions;
	}
	
	private String joinCondition(Object obj) {
		Code bv = (Code) obj;
		String conditions = "";
		if (StringUtil.validate(bv.getId())) {
		if("0".equals(bv.getId())){
			if(com.cisdi.ctp.utils.common.StringUtils.isBlank(conditions)){
				conditions += "  parent = '' or parent is NULL";
			}else{
				conditions += " and parent = '' or parent is NULL";
			}
		}
		}
		if (StringUtil.validate(bv.getCodeName())) {
			if(com.cisdi.ctp.utils.common.StringUtils.isBlank(conditions)){
				conditions += "codename = '" + bv.getCodeName()
						+ "'";
			}else{
			conditions += " and codename = '" + bv.getCodeName()
					+ "'";
			}
		}
		if (StringUtil.validate(bv.getCodeType())) {
			if(com.cisdi.ctp.utils.common.StringUtils.isBlank(conditions)){
				conditions += "  codetype = '" + bv.getCodeType()
						+ "'";
			}else{
				conditions += " and codetype = '" + bv.getCodeType()
						+ "'";
			}
			
		}
		if (StringUtil.validate(bv.getParent())) {
			if(com.cisdi.ctp.utils.common.StringUtils.isBlank(conditions)){
				conditions += "  parent = '" + bv.getParent()
						+ "'";
			}else{
				conditions += " and parent = '" + bv.getParent()
						+ "'";
			}
			
		}
		if (StringUtil.validate(bv.getCodeValue())) {
			if(com.cisdi.ctp.utils.common.StringUtils.isBlank(conditions)){
				conditions += "  codevalue = '" + bv.getCodeValue()
						+ "'";
			}else{
				conditions += " and codevalue = '" + bv.getCodeValue()
						+ "'";
			}
			
		}
		return conditions;
	}
	
	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select a.* from zc_code a where 1=1 and";
		sql += joinConditionStr(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = codeDao.getObjPagedList(page);
		Long total = codeDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 验证数据字典重复性
	 * @param code
	 * @return
	 */
	public Result checkDictionary(Code code) {
		Result result = new Result();
		result.setStatus(Status.ERROR);
		if(StringUtils.isBlank(code.getParent())){
			// 一级字典，验证数据名称、数据类型是否存在
			// 验证数据名称
			Code tempCodeByName = new Code();
			tempCodeByName.setCodeName(code.getCodeName());
			if(StringUtils.isNotEmpty(code.getId())){
				tempCodeByName.setId(code.getId());
			}
			Long countByCodeName =this.getCountByObj(Code.class, joinCondition(tempCodeByName));
			if(countByCodeName > 0){
				result.setMessage("数据名称已存在,请重新输入");
				return result;
			} 
			// 验证数据类型  
			Code tempCodeByType = new Code();  
			tempCodeByType.setCodeType(code.getCodeType()); 
			if(StringUtils.isNotEmpty(code.getId())){
				tempCodeByType.setId(code.getId());
			}
			Long countByCodeType = this.getCountByObj(Code.class, joinCondition(tempCodeByType));
 			if(countByCodeType > 0){
				result.setMessage("数据类型已存在,请重新输入");
				return result;
			}
		}else{
			// 二级字典，验证数据名称、数值是否存在
			// 验证数据名称
			Code tempCodeByName = new Code();
			tempCodeByName.setParent(code.getParent());
			tempCodeByName.setCodeName(code.getCodeName());
			if(StringUtils.isNotEmpty(code.getId())){
				tempCodeByName.setId(code.getId());
			}
			Long countByCodeName = this.getCountByObj(Code.class, joinCondition(tempCodeByName));
			if(countByCodeName > 0){
				result.setMessage("数据名称已存在,请重新输入");
				return result;
			} 
			// 数值  
			Code tempCodeByType = new Code();
			tempCodeByType.setParent(code.getParent());
			tempCodeByType.setCodeName(code.getCodeName());
			tempCodeByType.setCodeValue(code.getCodeValue());
			if(StringUtils.isNotEmpty(code.getId())){
				tempCodeByType.setId(code.getId());
			}
			Long countByCodeType =  this.getCountByObj(Code.class, joinCondition(tempCodeByType));
 			if(countByCodeType > 0){
				result.setMessage("数值已存在,请重新输入");
				return result;
			}
		}
		result.setStatus(Status.OK);
		return result;
	}

	@Override
	public void saveOrUpdateDictionary(HttpServletRequest request,Code code) {
		if(StringUtils.isNotEmpty(code.getId())){
			// 更新
			code.setUpdateTime(new Date());
			this.updateObj(code);
			logManageService.insertLog(request, "更新了字典表", "数据字典");
		}else{
			// 新增
			String id=UuidUtils.getUUID();
			code.setId(id);
			this.saveObj(code);
			logManageService.insertLog(request, "插入了一条字典数据", "数据字典");
		}
		
//		Constant.CODE_MAP=initCodeMap();
		
	}
	

	@Override
	public void deleteSelfAndchild(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Code> getAllParent() {
		return this.getListByObj(Code.class, "codeValue IS NULL OR codeValue = ''");
	}
	/**
	 * 点击一级字典，查询出二级子字典
	 */
	public List<Code> findByParentId(String id) {
		return this.getListByObj(Code.class, "parent='"+id+"'");
	}
	public List<Map<String,Object>> findByType(String type) {
		String sql =" select  a.* from zc_code a where a.codeType='"+type+"'  and a.parent is not NULL order by a.codeValue asc" ;
		Page page = new Page();
		page.setPage(1);
		page.setRows(100000);
		page.setSql(sql);
		try {
			List<Map<String, Object>> rows = codeDao
					.getObjPagedList(page);
			return rows;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Map initCodeMap() {
		logger.debug("初始化字典表");
		List<Code> allParent = getAllParent();
		Map codeMap = new HashedMap();
	
		for(Code c:allParent) 
		{
			String codeName = c.getCodeType();
			Map childCode = new HashedMap();
			Map childMap = new HashedMap();
			List<Code> findByParentId = findByParentId(c.getId());
			if(findByParentId!=null && findByParentId.size()>0){
				for(int i=0;i<findByParentId.size();i++)
				{
					childCode = (Map) findByParentId.get(i);
					//childMap.put(childCode.get("codeName"), childCode.get("codeValue"));
					childMap.put(childCode.get("codeValue"), childCode.get("codeName"));
				}
			}
			
			codeMap.put(codeName, childMap);  
		}
		
		return codeMap;
	}
	
	@Override
	public List<Map<String, Object>> getListByParent(String parentId) {
		String sql =" select  a.* from zc_code a where a.parent='"+parentId+"' ORDER BY a.codeValue asc" ;
		Page page = new Page();
		page.setPage(1);
		page.setRows(100000);
		page.setSql(sql);
		try {
			List<Map<String, Object>> rows = codeDao
					.getObjPagedList(page);
			return rows;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Result initCode(String codeType) {
		List<?> list = null;
		if(StringUtils.isNotEmpty(codeType))
		{
			list = findByType(codeType);
		}
		return (null!=list&&list.size()>0)?new Result(Status.OK,list):new Result(Status.ERROR,"");
	}

}
