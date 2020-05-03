package com.drugmanager.bean;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

public class Page<T> {
	private List<T> datas;
	private Integer pageno;// 当前页码
	private Integer totalno;// 总页码
	private Integer totalsize;// 总条数

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public Integer getPageno() {
		return pageno;
	}

	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}

	public Integer getTotalno() {
		return totalno;
	}

	public void setTotalno(Integer totalno) {
		this.totalno = totalno;
	}

	public Integer getTotalsize() {
		return totalsize;
	}

	public void setTotalsize(Integer totalsize) {
		this.totalsize = totalsize;
	}

}
