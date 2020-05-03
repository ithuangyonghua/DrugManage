package com.drugmanager.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import com.drugmanager.bean.Inventory;
import com.drugmanager.vo.InventoryVo;

@Service
public interface InventoryService {

	List<InventoryVo> pageQuery(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);

	void insertInventory(Inventory inventory);

	void updateInventory(Inventory inventory);

	Inventory queryInventoryById(String id);

	void deleteInventoryById(String id);

	void deleteInventoryBouth(Map<String, Object> map);
	
	void deleteInventoryByDrugId(String id);
	
	void deleteInventoryBouthDrugId(Map<String, Object> map);

	List<Inventory> queryAll();
    
	
	void updateSumByDrugId(Integer id, Integer newSum);
}
