package com.proem.exm.utils;


/**
 * EasyUI 分页帮助类
 * 
 * @author Denny
 * 
 */
public class Page implements java.io.Serializable {

	private int page;// 当前页

	private int rows;// 每页显示记录数
	
	private String condition;// 查询条件
	
	private String order;//排序
	
	private String sql;//SQL语句

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
	public static String getPagedSQL(String sql,int page,int rows){
		if(page<1){
			page = 1;
		}
		if(rows<1){
			rows = 1;
		}
		int firstRow = (page - 1) * rows;
		int lastRow = page * rows;
		return "select b.* from ("
				 + "select rownum as rn,a.* from ("
				 + sql
				 + ") a where rownum <= " + lastRow
				 + ") b where rn >" + firstRow;
	}
	
	public static String getListCountSQL(String sql){
		return "select count(*) as count from (" + sql + ")";
	}

}
