package com.proem.exm.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;


/**   
 * 类描述：   行政区划代码转换（将代码值转换中文值显示）
 * 
 * 项目名称：common   
 * 类名称：ConvertArea   
 * 创建人：zhuxinglu   
 * 创建时间：2015年7月31日 上午11:58:45   
 * 修改人：zhuxinglu   
 * 修改时间：2015年7月31日 上午11:58:45   
 * 修改备注：   
 * @version    
 *    
 */
public class ConvertArea extends TagSupport{
	
	/**  
	 * 页面传入值   
	 */   
	protected String value;
	/**  
	 * 行政区划（name为键值）   
	 */   
	private static Map AREA_NAME_MAP = new HashMap();
	
	/**  
	 * 行政区划（code为键值）   
	 */   
	private  static Map AREA_CODE_MAP = new HashMap();
	/**  
	 * 页面传入类型（1：代码转中文   2：中文转代码）       
	 */   
	protected String type;
	
	
	@Override
	public int doEndTag() throws JspException {
		String returnValue = "";
		try {
			if (StringUtils.isNotEmpty(value) && StringUtils.isNotEmpty(type)) {
				if (type.equals("1")) {
					returnValue = String.valueOf(AREA_CODE_MAP
							.get(value));
				} else if (type.equals("2")) {
					returnValue = String.valueOf(AREA_NAME_MAP
							.get(value));
				}
			}
			pageContext.getOut().print(
					StringUtils.isNotEmpty(returnValue) ? returnValue : "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}

	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
