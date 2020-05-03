package com.drugmanager.bean;

public class Sdetail {
	private String detailid;
	private Integer drugid;
	private Float nownum;
    
	public String getDetailid() {
		return detailid;
	}

	public void setDetailid(String detailid) {
		this.detailid = detailid;
	}

	public Integer getDrugid() {
		return drugid;
	}

	public void setDrugid(Integer drugid) {
		this.drugid = drugid;
	}

	public Float getNownum() {
		return nownum;
	}

	public void setNownum(Float nownum) {
		this.nownum = nownum;
	}

	@Override
	public String toString() {
		return "Sdetail [detailid=" + detailid + ", drugid=" + drugid + ", nownum=" + nownum + "]";
	}

}
