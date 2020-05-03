package com.drugmanager.vo;

import com.drugmanager.bean.DrugCategory;
import com.drugmanager.bean.DrugInfo;

public class DrugInfoVo extends DrugInfo {
     private DrugCategory drugCategory;

	public DrugCategory getDrugCategory() {
		return drugCategory;
	}

	public void setDrugCategory(DrugCategory drugCategory) {
		this.drugCategory = drugCategory;
	}
     
     
}
