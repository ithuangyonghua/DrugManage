package com.drugmanager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.drugmanager.bean.Supplier;

public interface SupplierDao {

	List<Supplier> pageQuery(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);
	
	@Insert("insert into supplier(suppliername,phone,address,fzr,detailid) values(#{suppliername},#{phone},#{address},#{fzr},#{detailid})")
	void insertSupplier(Supplier supplier);
     
    @Select("select * from supplier where supplierid =#{id}")
	Supplier querySupplierById(String id);
    
    @Update("update supplier set suppliername=#{suppliername},phone=#{phone},address=#{address},fzr=#{fzr} where supplierid = #{supplierid}")
	void updateSupplier(Supplier supplier);

    @Delete("delete from supplier where supplierid =#{id}")
	void deleteSupplierById(String id);

	void deleteSupplierBouth(Map<String, Object> map);
   
	@Select("select * from  supplier")
	List<Supplier> queryAll();

}
