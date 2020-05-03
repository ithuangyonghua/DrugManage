package com.drugmanager.vo;

import com.drugmanager.bean.DrugInfo;
import com.drugmanager.bean.Inventory;

public class InventoryVo extends Inventory {
	private DrugInfo drugInfo;

	public DrugInfo getDrugInfo() {
		return drugInfo;
	}

	public void setDrugInfo(DrugInfo drugInfo) {
		this.drugInfo = drugInfo;
	}

}
