package com.drugmanager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugmanager.bean.DrugCategory;
import com.drugmanager.dao.DrugCategoryDao;
import com.drugmanager.service.DrugCategoryService;

@Service
public class DrugCategoryimpl implements DrugCategoryService {
   @Autowired
   DrugCategoryDao drugCategoryDao;
	
	public List<DrugCategory> pageQuery(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return drugCategoryDao.pageQuery(map);
	}

	public int pageQueryCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return drugCategoryDao.pageQueryCount(map);
	}

	public void insertDrugCategory(DrugCategory drugCategory) {
		// TODO Auto-generated method stub
		drugCategoryDao.insertDrugCategory(drugCategory);
	}

	public DrugCategory querySupplierById(String id) {
		// TODO Auto-generated method stub
		return drugCategoryDao.querySupplierById(id);
	}

	public void updateDrugCategory(DrugCategory drugCategory) {
		// TODO Auto-generated method stub
		drugCategoryDao.updateDrugCategory(drugCategory);
	}

	public void deleteDrugCategoryById(String id) {
		// TODO Auto-generated method stub
		drugCategoryDao.deleteDrugCategoryById(id);
	}

	public void deleteDrugCategoryBouth(Map<String, Object> map) {
		// TODO Auto-generated method stub
		drugCategoryDao.deleteDrugCategoryBouth(map); 
	}

	public List<DrugCategory> queryAll() {
		// TODO Auto-generated method stub
		return drugCategoryDao.queryAll();
	}

}
