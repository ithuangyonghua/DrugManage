package com.drugmanager.bean;

public class DrugInfo {
	private Integer drugid;//药品编号
	private String drugname;//药品名称
	private Integer drugcategory;//药品类别
	private Float price;//价格
	private String yt;//药品用途

	public Integer getDrugid() {
		return drugid;
	}

	public void setDrugid(Integer drugid) {
		this.drugid = drugid;
	}

	public String getDrugname() {
		return drugname;
	}

	public void setDrugname(String drugname) {
		this.drugname = drugname;
	}

	public Integer getDrugcategory() {
		return drugcategory;
	}

	public void setDrugcategory(Integer drugcategory) {
		this.drugcategory = drugcategory;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getYt() {
		return yt;
	}

	public void setYt(String yt) {
		this.yt = yt;
	}

}
