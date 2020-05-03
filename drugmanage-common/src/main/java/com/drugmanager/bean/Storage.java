package com.drugmanager.bean;

public class Storage {
	private String id;//编号
	private String createTime;//创建时候
	private String loginacct;//操作用户
	private Integer status;//审核状态
	private Integer sid;//供应商Id
	private Integer monystatus;//结账状态
	private Integer monery;//实际支付金额

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLoginacct() {
		return loginacct;
	}

	public void setLoginacct(String loginacct) {
		this.loginacct = loginacct;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getMonystatus() {
		return monystatus;
	}

	public void setMonystatus(Integer monystatus) {
		this.monystatus = monystatus;
	}

	public Integer getMonery() {
		return monery;
	}

	public void setMonery(Integer monery) {
		this.monery = monery;
	}

}
