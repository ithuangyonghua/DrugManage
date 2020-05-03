package com.drugmanager.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.drugmanager.bean.Storage;
import com.drugmanager.vo.StorageVo;

@Service
public interface StorageService {

	Object saveStorage(StorageVo storageVo,HttpServletRequest request);

	List<Storage> pageQuery(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);

	Object updateStatusId(String id);

	Object deleteStorageById(String id);

	Object deleteStorageBouth(String [] id);

	Object updateMoneryStatusId(String id, Integer monery);
	

}
