package com.drugmanager.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.drugmanager.bean.Sdetail;
import com.drugmanager.vo.SdetailVo;

@Service
public interface SdetailService {

	void saveSdetails(List<Sdetail> sdetails);
   
	List<Sdetail> queryById(String id);
  
	void deleteSdetailById(String id);

	void deleteSdetaileBouth(Map<String, Object> map);

	List<SdetailVo> querySdetailVo(String id);

}
