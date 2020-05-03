package com.drugmanager.bean;

public class Inventory {

	private Integer id;// 库存ID
	private Integer drugid; // 药品ID
	private Integer inventorySum;// 库存数量
	private String inventoryName;// 仓库名
	private String createtime;// 创建时间
	private Integer status;// 状态
	private Integer uid; // 用户id

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDrugid() {
		return drugid;
	}

	public void setDrugid(Integer drugid) {
		this.drugid = drugid;
	}

	public Integer getInventorySum() {
		return inventorySum;
	}

	public void setInventorySum(Integer inventorySum) {
		this.inventorySum = inventorySum;
	}

	public String getInventoryName() {
		return inventoryName;
	}

	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}
