package com.qzz.util;

import java.util.List;

public class PageUtil<T>{
	  private int pageNumber;  //总记录数
	  private int pageSum;   //总页数 
	  private int pageIndex;  //当前页码
	  private int pageSize;   //每页大小
	  private List<T> list;
	public PageUtil(int pageNumber, int pageSum, int pageIndex, int pageSize,
			List<T> list) {
		this.pageNumber = pageNumber;
		this.pageSum = pageSum;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.list = list;
	}
	public PageUtil() {
		super();
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSum() {
		return pageSum;
	}
	public void setPageSum(int pageSum) {
		this.pageSum = pageSum;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}  
	}
