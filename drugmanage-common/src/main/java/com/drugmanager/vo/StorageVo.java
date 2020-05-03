package com.drugmanager.vo;

import java.util.List;

import com.drugmanager.bean.Sdetail;
import com.drugmanager.bean.Storage;

public class StorageVo extends Storage {
	private List<Sdetail> sdetails;

	public List<Sdetail> getSdetails() {
		return sdetails;
	}

	public void setSdetails(List<Sdetail> sdetails) {
		this.sdetails = sdetails;
	}

}
