package com.drugmanager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.drugmanager.bean.DrugInfo;
import com.drugmanager.bean.Inventory;
import com.drugmanager.vo.DrugInfoVo;

public interface DrugInfoDao {

	List<DrugInfoVo> pageQuery(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);

	//@Insert("insert into druginfo(drugid,drugname,drugcategory,price,yt) values (#{drugid},#{drugname},#{drugcategory},#{price},#{yt})")
	Integer insertDrugInfo(DrugInfo drugInfo);

	@Select("select * from  druginfo where drugid = #{id}")
	DrugInfo queryDrugById(String id);
   
	@Update("update druginfo set drugname=#{drugname},drugcategory=#{drugcategory},price = #{price},yt=#{yt} where drugid =#{drugid}")
	void updateDrugInfo(DrugInfo drugInfo);
   
	@Delete("delete from druginfo where drugid = #{id}")
	void deleteDrugInfoById(String id);
   
	void deleteDrugInfoBouth(Map<String, Object> map);
 
	@Select("select * from druginfo where drugid not in (select drugid from  inventory)")
	List<DrugInfo> queryAll();

	@Select("select * from druginfo where drugcategory= #{id}")
	List<DrugInfo> queryById(Integer id);
    
}
