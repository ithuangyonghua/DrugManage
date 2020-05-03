package com.drugmanager.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.drugmanager.bean.DrugCategory;

@Service
public interface DrugCategoryService {

	List<DrugCategory> pageQuery(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);

	void insertDrugCategory(DrugCategory drugCategory);

	DrugCategory querySupplierById(String id);

	void updateDrugCategory(DrugCategory drugCategory);

	void deleteDrugCategoryById(String id);

	void deleteDrugCategoryBouth(Map<String, Object> map);
	
	List<DrugCategory> queryAll();

}
