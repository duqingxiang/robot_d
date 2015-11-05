package com.demo.util;

public class PageUtil {
	
	private int page;
	private int pageLength;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageLength() {
		return pageLength;
	}
	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
	}
	
	public int getPageIndex(){
		int p = (page-1)*pageLength;
		return p;
	}

}
