package com.drugmanager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugmanager.bean.Supplier;
import com.drugmanager.dao.SupplierDao;
import com.drugmanager.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

	 @Autowired
	 SupplierDao supplierDao;

	public List<Supplier> pageQuery(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return supplierDao.pageQuery(map);
	}

	public int pageQueryCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return supplierDao.pageQueryCount(map);
	}

	public void insertSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		supplierDao.insertSupplier(supplier);
	}

	public Supplier querySupplierById(String id) {
		// TODO Auto-generated method stub
		return supplierDao.querySupplierById(id);
	}

	public void updateSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		supplierDao.updateSupplier(supplier);
	}

	public void deleteSupplierById(String id) {
		// TODO Auto-generated method stub
		supplierDao.deleteSupplierById(id);
	}

	public void deleteSupplierBouth(Map<String, Object> map) {
		// TODO Auto-generated method stub
		supplierDao.deleteSupplierBouth(map);
	}

	public List<Supplier> queryAll() {
		// TODO Auto-generated method stub
		return supplierDao.queryAll();
	}
}
