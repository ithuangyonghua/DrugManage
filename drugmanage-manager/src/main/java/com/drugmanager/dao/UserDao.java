package com.drugmanager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.drugmanager.bean.User;

public interface UserDao {

	@Select("select * from user")
	List<User> queryAll();

	@Select("select * from user where loginacct = #{loginacct} and password = ${password}")
	User queryForLogin(User user);

	List<User> pageQueryData(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);

	@Insert("insert into user(loginacct,username,email,password,createtime) values (#{loginacct},#{username},#{email},#{password},#{createtime})")
	void insertUser(User user);

	@Select("select * from user where id = #{id}")
	User queryUserById(String id);

	void updateUser(User user);

	@Delete("delete from user where id =#{id}")
	void deleteUser(String id);

	void deleteBoathUser(Map<String,Object> map);

	void insertAssign(Map<String, Object> map);

	void deleteAssign(Map<String, Object> map);
    
	@Select("select rid from user_role where uid = #{id}")
	List<Integer> assignRoleById(String id);

}
