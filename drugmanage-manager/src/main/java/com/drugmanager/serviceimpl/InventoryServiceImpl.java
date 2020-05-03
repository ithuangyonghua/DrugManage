package com.drugmanager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugmanager.bean.Inventory;
import com.drugmanager.dao.InventoryDao;
import com.drugmanager.service.InventoryService;
import com.drugmanager.vo.InventoryVo;

@Service
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	InventoryDao inventoryDao;

	public List<InventoryVo> pageQuery(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return inventoryDao.pageQuery(map);
	}

	public int pageQueryCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return inventoryDao.pageQueryCount(map);
	}

	public void insertInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		inventoryDao.insertInventory(inventory);
	}

	public void updateInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		inventoryDao.updateInventory(inventory);
	}

	public Inventory queryInventoryById(String id) {
		// TODO Auto-generated method stub
		return inventoryDao.queryInventoryById(id);
	}

	public void deleteInventoryById(String id) {
		// TODO Auto-generated method stub
		inventoryDao.deleteInventoryById(id);
	}

	public void deleteInventoryBouth(Map<String, Object> map) {
		// TODO Auto-generated method stub
		inventoryDao.deleteInventoryBouth(map);
	}

	public void deleteInventoryByDrugId(String id) {
		// TODO Auto-generated method stub
		inventoryDao.deleteInventoryByDrugId(id);
	}

	public void deleteInventoryBouthDrugId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		inventoryDao.deleteInventoryBouthDrugId(map);
		
	}

	public List<Inventory> queryAll() {
		// TODO Auto-generated method stub
		return inventoryDao.queryAll();
	}

	public void updateSumByDrugId(Integer id, Integer float1) {
		// TODO Auto-generated method stub
		inventoryDao.updateSumByDrugId(id,float1);
	}

}
