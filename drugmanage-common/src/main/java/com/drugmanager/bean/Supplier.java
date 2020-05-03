package com.drugmanager.bean;

/**
 * 供货商
 * 
 * @author Hyh
 *
 */
public class Supplier {
	private Integer supplierid;//供货商编号
	private String suppliername;//供货商名称
	private String phone;//供货商联系电话
	private String address;//供货商地址
	private String fzr;//供货商负责人
	private String detailid;//供货商明细

	public Integer getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(Integer supplierid) {
		this.supplierid = supplierid;
	}

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFzr() {
		return fzr;
	}

	public void setFzr(String fzr) {
		this.fzr = fzr;
	}

	public String getDetailid() {
		return detailid;
	}

	public void setDetailid(String detailid) {
		this.detailid = detailid;
	}

}
