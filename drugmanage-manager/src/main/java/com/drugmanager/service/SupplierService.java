package com.drugmanager.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.drugmanager.bean.Supplier;

@Service
public interface SupplierService {

	List<Supplier> pageQuery(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);

	void insertSupplier(Supplier supplier);

	Supplier querySupplierById(String id);

	void updateSupplier(Supplier supplier);

	void deleteSupplierById(String id);

	void deleteSupplierBouth(Map<String, Object> map);
  
	List<Supplier> queryAll();
}
