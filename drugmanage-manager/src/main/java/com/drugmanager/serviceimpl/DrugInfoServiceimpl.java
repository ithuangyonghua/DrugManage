package com.drugmanager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugmanager.bean.DrugInfo;
import com.drugmanager.bean.Inventory;
import com.drugmanager.dao.DrugInfoDao;
import com.drugmanager.service.DrugInfoService;
import com.drugmanager.vo.DrugInfoVo;

@Service
public class DrugInfoServiceimpl implements DrugInfoService {
     @Autowired
     DrugInfoDao drugInfoDao;

	public List<DrugInfoVo> pageQuery(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return drugInfoDao.pageQuery(map);
	}

	public int pageQueryCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return drugInfoDao.pageQueryCount(map);
	}

	public Integer insertDrugInfo(DrugInfo drugInfo) {
		// TODO Auto-generated method stub
		return drugInfoDao.insertDrugInfo(drugInfo);
	}

	public DrugInfo queryDrugById(String id) {
		// TODO Auto-generated method stub
		return drugInfoDao.queryDrugById(id);
	}

	public void updateDrugInfo(DrugInfo drugInfo) {
		// TODO Auto-generated method stub
		drugInfoDao.updateDrugInfo(drugInfo);
	}

	public void deleteDrugInfoById(String id) {
		// TODO Auto-generated method stub
		drugInfoDao.deleteDrugInfoById(id);
	}

	public void deleteDrugInfoBouth(Map<String, Object> map) {
		// TODO Auto-generated method stub
		drugInfoDao.deleteDrugInfoBouth(map);
	}

	public List<DrugInfo> queryAll() {
		// TODO Auto-generated method stub
		return drugInfoDao.queryAll();
	}

	public List<DrugInfo> queryById(Integer id) {
		// TODO Auto-generated method stub
		return drugInfoDao.queryById(id);
	}


}
