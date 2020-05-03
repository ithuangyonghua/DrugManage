package com.drugmanager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.drugmanager.bean.Inventory;
import com.drugmanager.vo.InventoryVo;

public interface InventoryDao {

	List<InventoryVo> pageQuery(Map<String, Object> map);
     
	int pageQueryCount(Map<String, Object> map);

	@Insert("insert into inventory(drugid,inventorySum) values(#{drugid},#{inventorySum})")
	void insertInventory(Inventory inventory);
   
    @Update("update inventory set inventorySum = #{inventorySum} where id = #{id}")
	void updateInventory(Inventory inventory);
    
    @Select("select * from inventory where id = #{id}")
	Inventory queryInventoryById(String id);

    @Delete("delete from inventory where id = #{id} ")
	void deleteInventoryById(String id);

	void deleteInventoryBouth(Map<String, Object> map);
    
	@Delete("delete from inventory where drugid = #{id} ")
	void deleteInventoryByDrugId(String id);

	void deleteInventoryBouthDrugId(Map<String, Object> map);
    
	@Select("select * from inventory")
	List<Inventory> queryAll();

	@Update("update inventory  set inventorySum = #{float1} where id = #{id}")
	void updateSumByDrugId(@Param("id") Integer id,@Param("float1") Integer float1);

}
