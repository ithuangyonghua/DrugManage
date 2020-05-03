package com.drugmanager.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.drugmanager.bean.DrugInfo;
import com.drugmanager.bean.Inventory;
import com.drugmanager.vo.DrugInfoVo;

@Service
public interface DrugInfoService {

	List<DrugInfoVo> pageQuery(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);

	Integer insertDrugInfo(DrugInfo drugInfo);

	DrugInfo queryDrugById(String id);

	void updateDrugInfo(DrugInfo drugInfo);

	void deleteDrugInfoById(String id);

	void deleteDrugInfoBouth(Map<String, Object> map);

	List<DrugInfo> queryAll();

	List<DrugInfo> queryById(Integer id);
}
