package com.ybr.entity;

import java.util.Date;
import java.util.List;

public class Tiezi {
	private Integer id;
	private String title;
	private String content;
	private Date fabutime;
	private Integer userid;
	private Integer count;
	private Integer typeid;
	private User user;
	private Type type;
	private List<Reply> list;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getFabutime() {
		return fabutime;
	}
	public void setFabutime(Date fabutime) {
		this.fabutime = fabutime;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	
	public List<Reply> getList() {
		return list;
	}
	public void setList(List<Reply> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "Tiezi [id=" + id + ", title=" + title + ", content=" + content + ", fabutime=" + fabutime + ", userid="
				+ userid + ", count=" + count + ", typeid=" + typeid + "]";
	}
}
