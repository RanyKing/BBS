package com.ybr.entity;

import java.util.List;

public class Page {
	private Integer pageNum;
	private Integer size;
	private Integer total;
	private Integer end;
	private List<Tiezi> list;
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public List<Tiezi> getList() {
		return list;
	}
	public void setList(List<Tiezi> list) {
		this.list = list;
	}
}
