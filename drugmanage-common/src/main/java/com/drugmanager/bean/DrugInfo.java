package com.drugmanager.bean;

public class DrugInfo {
	private Integer drugid;//ҩƷ���
	private String drugname;//ҩƷ����
	private Integer drugcategory;//ҩƷ���
	private Float price;//�۸�
	private String yt;//ҩƷ��;

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
