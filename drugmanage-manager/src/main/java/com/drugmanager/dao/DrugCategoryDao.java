package com.drugmanager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.drugmanager.bean.DrugCategory;

public interface DrugCategoryDao {
    @Select("select * from drugcategory")
    List<DrugCategory> queryAll();

	List<DrugCategory> pageQuery(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);
   
	@Insert("insert into drugcategory(name) values (#{name})")
	void insertDrugCategory(DrugCategory drugCategory);
    
	@Select("select * from drugcategory where dcategoryid = #{id}")
	DrugCategory querySupplierById(String id);

	@Update("update drugcategory set name = #{name} where dcategoryid =#{dcategoryid} ")
	void updateDrugCategory(DrugCategory drugCategory);

	@Delete("delete from drugcategory where dcategoryid = #{id}")
	void deleteDrugCategoryById(String id);

	void deleteDrugCategoryBouth(Map<String, Object> map);

}
