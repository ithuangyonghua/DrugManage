package com.drugmanager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.drugmanager.bean.Sdetail;
import com.drugmanager.vo.SdetailVo;

public interface SdetailDao {
    //Ä¬ÈÏÎªlist
	void saveSdetails(@Param("sdetails") List<Sdetail> sdetails);

	@Select("select * from sdetail where detailid = #{id}")
	List<Sdetail> queryById(String id);

	@Delete("delete from sdetail where detailid = #{id}")
	void deleteSdetailById(String id);
  
	void deleteSdetaileBouth(Map<String, Object> map);

	List<SdetailVo> querySdetailVo(String id);

}
