package com.drugmanager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugmanager.bean.Sdetail;
import com.drugmanager.dao.SdetailDao;
import com.drugmanager.service.SdetailService;
import com.drugmanager.vo.SdetailVo;

@Service
public class SdetailServiceImpl implements SdetailService {
  
	@Autowired
	SdetailDao  sdetailDao;
	public void saveSdetails(List<Sdetail> sdetails) {
		// TODO Auto-generated method stub
		sdetailDao.saveSdetails(sdetails);
	}
	public List<Sdetail> queryById(String id) {
		// TODO Auto-generated method stub
		return sdetailDao.queryById(id);
	}
	public void deleteSdetailById(String id) {
		// TODO Auto-generated method stub
		sdetailDao.deleteSdetailById(id);
	}
	public void deleteSdetaileBouth(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sdetailDao.deleteSdetaileBouth(map);
	}
	public List<SdetailVo> querySdetailVo(String id) {
		// TODO Auto-generated method stub
		return sdetailDao.querySdetailVo(id);
	}


}
