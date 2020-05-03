package com.drugmanager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.drugmanager.bean.Storage;
import com.drugmanager.vo.StorageVo;

public interface StorageDao {

	void saveStorage(Storage storage);

	List<Storage> pageQuery(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);
	
	@Update("update storage set status = 1 where id =#{id} ")
	void updateStatusId(String id);

	@Delete("delete from storage where  id =#{id}")
	void deleteStorageById(String id);

	void deleteStorageBouth(Map<String, Object> map);
    
	@Update("update storage set monystatus = 1 ,monery = #{monery} where  id =#{id}")
	void updateMoneryStatusId(@Param("id") String id,@Param("monery") Integer monery);


}
