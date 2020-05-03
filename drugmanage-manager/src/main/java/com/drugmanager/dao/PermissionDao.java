package com.drugmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.drugmanager.bean.Permission;
import com.drugmanager.bean.User;

public interface PermissionDao {

	@Select("select * from permission where pid IS NULL")
	Permission queryRoot();

	@Select("select * from permission where pid = #{id}")
	List<Permission> queryChildren(Integer id);

	@Select("select * from permission")
	List<Permission> queryAll();

	@Insert("insert into permission(name,pid,url) values (#{name},#{pid},#{url})")
	void insertPermission(Permission permission);

	@Select("select * from permission where id =#{id}")
	Permission queryPermissionById(Integer id);

	@Update("update permission set name = #{name},url=#{url} where id =#{id}")
	void updatePermission(Permission permission);

	@Delete("delete from permission where id = #{id}")
	void deletePermission(Permission permission);

	@Select("select pid from role_permission where rid = #{rid} ")
	List<Integer> queryPermissionidByRid(Integer rid);
    
	List<Permission> queryPermissionByUser(User dbuser);

}
