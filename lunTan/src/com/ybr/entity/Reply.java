package com.ybr.entity;

import java.util.Date;

public class Reply {
	private Integer id;
	private String replycontent;
	private Date replytime;
	private Integer tieziid;
	private Integer userid;
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReplycontent() {
		return replycontent;
	}
	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}
	public Date getReplytime() {
		return replytime;
	}
	public void setReplytime(Date replytime) {
		this.replytime = replytime;
	}
	public Integer getTieziid() {
		return tieziid;
	}
	public void setTieziid(Integer tieziid) {
		this.tieziid = tieziid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "Reply [id=" + id + ", replycontent=" + replycontent + ", replytime=" + replytime + ", tieziid="
				+ tieziid + ", userid=" + userid + "]";
	}
	
}
