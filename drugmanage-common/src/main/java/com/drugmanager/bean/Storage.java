package com.drugmanager.bean;

public class Storage {
	private String id;//���
	private String createTime;//����ʱ��
	private String loginacct;//�����û�
	private Integer status;//���״̬
	private Integer sid;//��Ӧ��Id
	private Integer monystatus;//����״̬
	private Integer monery;//ʵ��֧�����

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
