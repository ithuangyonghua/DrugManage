package com.drugmanager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.drugmanager.bean.Role;

public interface RoleDao {

	List<Role> pageQuery(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);

	@Select("select * from role where id = #{id}")
	Role queryRoleById(String id);

	@Update("update role set name=#{name} where id =#{id}")
	void updateRole(Role role);

	@Insert("insert into role(name,createtime) values(#{name},#{createtime})")
	void insertRole(Role role);

	@Delete("delete from role where id=#{id}")
	void deleteRoleById(String id);

	void deleteRoleBouth(Map<String, Object> map);
   
	@Select("select * from role")
	List<Role> queryAll();

	void doAssign(Map<String, Object> map);

	void deleteAssign(Map<String, Object> map);

}
