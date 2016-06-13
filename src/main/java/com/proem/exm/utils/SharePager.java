package com.proem.exm.utils;

public class SharePager {
	private int totalRows;// 总行数
	private int rows = 10;// 每页显示行数
	private int page = 1;// 当前页号
	private int totalPages;// 总页数
	private int startRow;// 当前页在数据库中的起始行

	public SharePager() {

	}

	public SharePager(int totalRows) {
		this.totalRows = totalRows;
		totalPages = (int) Math.ceil((double) totalRows / (double) rows);
		startRow = 0;
	}

	public SharePager(int totalRows, int rows) {
		this.totalPages = totalRows;
		this.rows = rows;
		if (this.rows < 1) {
			this.rows = 1;
		} else if (rows > 20) {
			this.rows = 20;
		}
		totalPages = (int) Math.ceil((double) totalRows / (double) rows);
		page = 1;
		startRow = 0;
	}

	public void first() {
		this.page = 1;
		this.startRow = 0;
	}

	public void previous() {
		if (page == 1) {
			return;
		}
		page--;
		startRow = (page - 1) * rows;
	}

	public void next() {
		if (page < totalPages) {
			page++;
		}
		startRow = (page - 1) * rows;
	}

	public void last() {
		this.page = totalPages;
		if (page < 1) {
			page = 1;
		}
		this.startRow = (page - 1) * this.rows;
		totalPages = (int) Math.ceil((double) totalRows / (double) rows);
	}

	public void refresh(int page) {
		if (page < 0) {
			first();
		}
		if (page > totalPages) {
			last();
		} else {
			this.page = page;
			this.startRow = (page - 1) * this.rows;
		}
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}



}
