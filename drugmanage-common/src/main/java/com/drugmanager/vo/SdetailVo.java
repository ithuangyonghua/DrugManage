package com.drugmanager.vo;

import com.drugmanager.bean.DrugInfo;
import com.drugmanager.bean.Sdetail;

public class SdetailVo extends Sdetail{
	/*private DrugInfo drugInfo;

	public DrugInfo getDrugInfo() {
		return drugInfo;
	}

	public void setDrugInfo(DrugInfo drugInfo) {
		this.drugInfo = drugInfo;
	}*/
	
	private String drugname;//ҩƷ����
	private Float price;//�۸�
	public String getDrugname() {
		return drugname;
	}
	public void setDrugname(String drugname) {
		this.drugname = drugname;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	
}
