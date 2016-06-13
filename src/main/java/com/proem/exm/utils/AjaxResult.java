package com.proem.exm.utils;
/**
 * Ajax请求返回结果
 * @author Shihao
 */
public class AjaxResult {
	public final static String SAVE = "保存";
	public final static String UPDATE = "更新";
	public final static String DELETE = "删除";
	public final static String SELECT = "查询";
	
	public final static String SUCCESS = "成功";
	public final static String FAIL = "失败";
	
	public final static String INFO = "info";
	public final static String ERROR = "error";
	public final static String QUESTION = "question";
	public final static String WARNING = "warning";
	
	public AjaxResult(String result){
		if(SUCCESS.equals(result)){
			this.result = true;
		}else if(FAIL.equals(result)){
			this.result = false;
		}else{
			this.result = false;
		}
	}
	
	public AjaxResult(String action,String result,String icon){
		this.title = "提示";
		this.icon = icon;
		if(SUCCESS.equals(result)){
			this.result = true;
		}else if(FAIL.equals(result)){
			this.result = false;
		}else{
			this.result = false;
		}
		this.message = action + result;
	}

	public AjaxResult(String action,String result,String icon,String moreMessage){
		this.title = "提示";
		this.icon = icon;
		if(SUCCESS.equals(result)){
			this.result = true;
		}else if(FAIL.equals(result)){
			this.result = false;
		}else{
			this.result = false;
		}
		if(StringUtil.validate(moreMessage)){
			this.message = action + result + "," + moreMessage;
		}else{
			this.message = action + result;
		}
	}
	
	private boolean result;
	private String title;
	private String icon;
	private String message;
	
	public boolean getResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
